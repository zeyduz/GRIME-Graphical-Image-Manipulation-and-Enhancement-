import org.junit.Test;

import java.io.StringReader;

import imageprocessing.controller.ImageProcessingApplicationController;
import imageprocessing.controller.ImageProcessingApplicationTextController;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;
import imageprocessing.view.ImageProcessingApplicationTextView;
import imageprocessing.view.ImageProcessingApplicationView;

import static org.junit.Assert.assertEquals;

/**
 * IPA controller test class.
 */
public class IPAControllerTest {

  Appendable out1;
  ImageProcessingApplicationModel model1;
  ImageProcessingApplicationView view1;
  ImageProcessingApplicationController controller1;

  private void setupMock() {
    out1 = new StringBuilder();
    model1 = new IPAModelMock(out1);
    view1 = new ImageProcessingApplicationTextView(new StringBuilder(), model1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNullModel() {
    setupValid();
    new ImageProcessingApplicationTextController(null,
            new ImageProcessingApplicationTextView(model1),
            new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNullView() {
    setupValid();
    new ImageProcessingApplicationTextController(
            new ImageProcessingApplicationModelImpl(),
            null,
            new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNullReadable() {
    setupValid();
    new ImageProcessingApplicationTextController(
            new ImageProcessingApplicationModelImpl(),
            new ImageProcessingApplicationTextView(model1),
            null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNullModelAndView() {
    setupValid();
    new ImageProcessingApplicationTextController(
            null,
            null,
            new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNullReadableAndModel() {
    setupValid();
    new ImageProcessingApplicationTextController(
            null,
            new ImageProcessingApplicationTextView(model1),
            null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNullReadableAndView() {
    setupValid();
    new ImageProcessingApplicationTextController(
            new ImageProcessingApplicationModelImpl(),
            null,
            null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerNullAll() {
    setupValid();
    new ImageProcessingApplicationTextController(
            null,
            null,
            null);
  }

  @Test
  public void testValidInputViaMock() {
    setupMock();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example1.ppm ex1 q"));
    controller1.start();

    assertEquals("New name: ex1\n", out1.toString());
  }

  @Test
  public void testInValidInputViaMock() {
    setupMock();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("hi there q"));
    controller1.start();

    assertEquals("", out1.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void outOfInput() {
    setupMock();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader(""));
    controller1.start();
  }

  @Test(expected = IllegalStateException.class)
  public void outOfInputAfter1CommandName() {
    setupMock();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("brighten"));
    controller1.start();
  }

  @Test(expected = IllegalStateException.class)
  public void outOfInputAfter1CommandNameAndFileName() {
    setupMock();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("brighten ex1"));
    controller1.start();
  }

  @Test(expected = IllegalStateException.class)
  public void outOfInputAfter1CommandNameAndFileNames() {
    setupMock();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("brighten ex1 bright"));
    controller1.start();
  }

  private void setupValid() {
    out1 = new StringBuilder();
    model1 = new ImageProcessingApplicationModelImpl();
    view1 = new ImageProcessingApplicationTextView(out1, model1);
  }

  @Test
  public void testValidIPAUse() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example1.ppm ex1 " +
                    "brighten ex1 bright 10 q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex1", lines[2]);
    assertEquals("Brighten image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("bright", lines[5]);
    assertEquals("ex1", lines[6]);
  }

  @Test
  public void testValidIPAUse2() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2 " +
                    "flip-vertical ex2 ex2-v flip-horizontal ex2-v ex2-vh q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Vertically Flipped image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("ex2", lines[5]);
    assertEquals("ex2-v", lines[6]);
    assertEquals("Horizontally Flipped image created.", lines[7]);
    assertEquals("Image Names:", lines[8]);
    assertEquals("ex2-vh", lines[9]);
    assertEquals("ex2", lines[10]);
    assertEquals("ex2-v", lines[11]);

  }

  @Test
  public void testValidIPAUseRedComponentAndLuma() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2 " +
                    "red-component ex2 ex2-red luma-component ex2-red ex2-redluma q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Red-Component image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("ex2-red", lines[5]);
    assertEquals("ex2", lines[6]);
    assertEquals("Luma-Component image created.", lines[7]);
    assertEquals("Image Names:", lines[8]);
    assertEquals("ex2-red", lines[9]);
    assertEquals("ex2", lines[10]);
    assertEquals("ex2-redluma", lines[11]);
  }

  @Test
  public void testValidIPAUseGreenAndBlue() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2 " +
                    "green-component ex2 ex2-green blue-component" +
                    " ex2 ex2-blue q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Green-Component image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("ex2-green", lines[5]);
    assertEquals("ex2", lines[6]);
    assertEquals("Blue-Component image created.", lines[7]);
    assertEquals("Image Names:", lines[8]);
    assertEquals("ex2-blue", lines[9]);
    assertEquals("ex2-green", lines[10]);
    assertEquals("ex2", lines[11]);
  }

  @Test
  public void testValidIPAUseIntensityAndValue() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2 " +
                    "intensity-component ex2 ex2-int value-component" +
                    " ex2 ex2-value q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Intensity-Component image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("ex2-int", lines[5]);
    assertEquals("ex2", lines[6]);
    assertEquals("Value-Component image created.", lines[7]);
    assertEquals("Image Names:", lines[8]);
    assertEquals("ex2-int", lines[9]);
    assertEquals("ex2-value", lines[10]);
    assertEquals("ex2", lines[11]);
  }

  @Test
  public void testValidIPAUseDarken() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2 " +
                    "darken ex2 dark -20 quit"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Darkened image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("dark", lines[5]);
    assertEquals("ex2", lines[6]);
  }

  @Test
  public void testValidIPAUseGreyscale() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example1.ppm ex2 " +
                    "greyscale ex2 grey-ex2 q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Greyscale image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("grey-ex2", lines[5]);
    assertEquals("ex2", lines[6]);
  }

  @Test
  public void testValidIPAUseSepia() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example1.ppm ex2 " +
                    "sepia ex2 sepia-ex2 q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Sepia image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("sepia-ex2", lines[5]);
    assertEquals("ex2", lines[6]);
  }

  @Test
  public void testValidIPAUseBlur() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example1.ppm ex2 " +
                    "blur ex2 blurry q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Gaussian Blurred image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("blurry", lines[5]);
    assertEquals("ex2", lines[6]);
  }

  @Test
  public void testValidIPAUseSharpen() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example1.ppm ex2 " +
                    "sharpen ex2 sharp q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Sharpened image created.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("ex2", lines[5]);
    assertEquals("sharp", lines[6]);
  }

  @Test
  public void testInvalidFilePath() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2 ex2 quit quit"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Invalid Input. Start again.", lines[1]);
    assertEquals("No Images Loaded Yet.", lines[2]);
    assertEquals("Invalid Input. Start again.", lines[3]);
    assertEquals("No Images Loaded Yet.", lines[4]);
  }

  @Test
  public void testInvalidName() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2" +
                    " red-component ex1 ex2-red quit quit"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Invalid Input. Start again.", lines[3]);
    assertEquals("Image Names:", lines[4]);
    assertEquals("ex2", lines[5]);
    assertEquals("Invalid Input. Start again.", lines[6]);
    assertEquals("Image Names:", lines[7]);
    assertEquals("ex2", lines[8]);
  }

  @Test
  public void testInvalidBrightenValue() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2" +
                    " brighten ex2 bright lol q q  qq"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Brighten expects a integer value.", lines[3]);
  }

  @Test
  public void testInvalidDarkenValue() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2" +
                    " darken ex2 bright lol q q  qq"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Image Names:", lines[1]);
    assertEquals("ex2", lines[2]);
    assertEquals("Darken expects a integer value.", lines[3]);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidBrightenNoMoreInput() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2" +
                    " brighten ex2 bright"));

    controller1.start();
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidDarkenNoMoreInput() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("load src/images/example2.ppm ex2" +
                    " darken ex2 bright"));

    controller1.start();
  }

  @Test
  public void testInvalidCommand() {
    setupValid();
    controller1 = new ImageProcessingApplicationTextController(
            model1,
            view1,
            new StringReader("invalid src/images/example2-ppm ex2 q q"));

    controller1.start();

    String[] lines = out1.toString().split("\n");

    assertEquals("No Images Loaded Yet.", lines[0]);
    assertEquals("Invalid Input. Start again.", lines[1]);
  }

}
