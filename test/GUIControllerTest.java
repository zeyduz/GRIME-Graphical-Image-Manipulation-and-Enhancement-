import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import image.Image;
import image.ImageFactory;
import image.PPMImage;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.controller.ImageProcessingApplicationGUIController;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;
import imageprocessing.view.ImageProcessingApplicationViewExtension;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the GUI Controller.
 */
public class GUIControllerTest {
  private ImageProcessingApplicationViewExtension view;
  private ImageProcessingApplicationModel model;
  private ImageProcessingApplicationGUIController controller;

  @Before
  public void init() {
    view = new MockGUIView();
    model = new ImageProcessingApplicationModelImpl();
    model.addImage(ImageFactory.factoryBuildImage("src/images/example1.ppm"), "ex1");
    controller = new ImageProcessingApplicationGUIController(model, view);
  }

  @Test
  public void testLoadListener() {
    // Check that the image was loaded
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testLoadListenerExample2() {
    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "load");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Check the post conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example2.ppm"),
            model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testBrightenListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "brighten");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Check the post conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testDarkenListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "darken");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Check the post conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testRedCompListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "red-component");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[1][1] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testGreenCompListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "green-component");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][1] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testBlueCompListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "blue-component");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[1][1] = new RGBAPixel(255, 100, 100, 100, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testIntensityCompListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "intensity-component");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 85, 85, 85, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 170, 170, 170, 255);
    expected[1][1] = new RGBAPixel(255, 33, 33, 33, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testLumaCompListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "luma-component");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 54, 54, 54, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 237, 237, 237, 255);
    expected[1][1] = new RGBAPixel(255, 72, 72, 72, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testValueCompListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "value-component");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[1][1] = new RGBAPixel(255, 100, 100, 100, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testHorizontalFliListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "flip-horizontal");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);


    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 255, 0, 0, 255);
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][2] = new RGBAPixel(255, 255, 0, 255, 255);
    expected[1][1] = new RGBAPixel(255, 0, 0, 100, 255);
    expected[1][0] = new RGBAPixel(255, 20, 20, 20, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testVerticalFlipListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "flip-vertical");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);


    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 255, 0, 255, 255);
    expected[0][1] = new RGBAPixel(255, 0, 0, 100, 255);
    expected[0][2] = new RGBAPixel(255, 20, 20, 20, 255);
    expected[1][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][1] = new RGBAPixel(255, 255, 0, 0, 255);
    expected[1][2] = new RGBAPixel(255, 0, 0, 0, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testBlurListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "blur");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 64, 0, 38, 255);
    expected[0][1] = new RGBAPixel(255, 81, 1, 30, 255);
    expected[0][2] = new RGBAPixel(255, 34, 3, 9, 255);
    expected[1][0] = new RGBAPixel(255, 80, 0, 76, 255);
    expected[1][1] = new RGBAPixel(255, 66, 3, 59, 255);
    expected[1][2] = new RGBAPixel(255, 21, 5, 18, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testSharpenListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "sharpen");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 125, 0, 86, 255);
    expected[0][1] = new RGBAPixel(255, 255, 5, 94, 255);
    expected[0][2] = new RGBAPixel(255, 37, 5, 0, 255);
    expected[1][0] = new RGBAPixel(255, 255, 0, 255, 255);
    expected[1][1] = new RGBAPixel(255, 133, 5, 169, 255);
    expected[1][2] = new RGBAPixel(255, 52, 20, 13, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testGreyscaleListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "greyscale");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 54, 54, 54, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 73, 73, 73, 255);
    expected[1][1] = new RGBAPixel(255, 7, 7, 7, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

  @Test
  public void testSepiaListener() {
    // Check the initial conditions
    assertEquals(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            model.getImageCopy(model.getNewestImageName()));

    // Make a mock action event
    ActionEvent e = new ActionEvent(view, 0, "sepia");
    // Give that event to the listener (controller in this case)
    controller.actionPerformed(e);

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 100, 89, 69, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 148, 132, 103, 255);
    expected[1][1] = new RGBAPixel(255, 19, 17, 13, 255);
    expected[1][2] = new RGBAPixel(255, 27, 24, 19, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    // Check the post conditions
    assertEquals(expected_image, model.getImageCopy(model.getNewestImageName()));
  }

}
