import org.junit.Before;
import org.junit.Test;

import image.Image;
import image.PPMImage;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test the model.
 */
public class IPAModelTest {

  ImageProcessingApplicationModel model;

  @Before
  public void init() {
    model = new ImageProcessingApplicationModelImpl();
    model.addImage(new PPMImage("src/images/example1.ppm"), "ex1");
    model.addImage(new PPMImage("src/images/example2.ppm"), "ex2");
  }

  // -------------------- TEST GET IMAGE COPY --------------------//

  @Test
  public void testGetImageCopyExample1() {
    Image actual = model.getImageCopy("ex1");

    Pixel[][] actualPixels = new Pixel[2][3];
    actualPixels[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    actualPixels[0][1] = new RGBAPixel(255, 255, 0, 0, 255);
    actualPixels[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    actualPixels[1][0] = new RGBAPixel(255, 255, 0, 255, 255);
    actualPixels[1][1] = new RGBAPixel(255, 0, 0, 100, 255);
    actualPixels[1][2] = new RGBAPixel(255, 20, 20, 20, 255);
    Image expected = new PPMImage(actualPixels, 3, 2);

    assertEquals(expected, actual);
  }

  @Test
  public void testGetImageCopyExample2() {
    Image actual = model.getImageCopy("ex2");

    Pixel[][] actualPixels = new Pixel[3][2];
    actualPixels[0][0] = new RGBAPixel(255, 255, 255, 255, 255);
    actualPixels[0][1] = new RGBAPixel(255, 0, 255, 0, 255);
    actualPixels[1][0] = new RGBAPixel(255, 100, 100, 100, 255);
    actualPixels[1][1] = new RGBAPixel(255, 0, 255, 255, 255);
    actualPixels[2][0] = new RGBAPixel(255, 255, 0, 255, 255);
    actualPixels[2][1] = new RGBAPixel(255, 0, 0, 100, 255);
    Image expected = new PPMImage(actualPixels, 2, 3);

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageException() {
    model.getImageCopy("notAName");
  }

  // -------------------- TEST ADD IMAGE --------------------//

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullImage() {
    model.addImage(null, "nullImage");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageNullName() {
    model.addImage(new PPMImage("src/images/example1.ppm"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageBothNull() {
    model.addImage(null, null);
  }

  @Test
  public void testAddImageEx1() {
    // already added in @Before

    assertEquals(new PPMImage("src/images/example1.ppm"),
            model.getImageCopy("ex1"));
  }

  @Test
  public void testAddImageEx2() {
    // already added in @Before

    assertEquals(new PPMImage("src/images/example2.ppm"),
            model.getImageCopy("ex2"));
  }


  // -------------------- TEST GET IMAGE NAMES --------------------//

  @Test
  public void testGetNamesEmptyModel() {
    ImageProcessingApplicationModel model2 = new ImageProcessingApplicationModelImpl();
    String[] names = model2.getImageNames();

    assertArrayEquals(new String[]{}, names);
  }

  @Test
  public void testGetNamesInitModel() {
    String[] names = model.getImageNames();

    assertArrayEquals(new String[]{"ex2", "ex1"}, names);
  }

  @Test
  public void testGetNamesInitModelOtherDirection() {
    ImageProcessingApplicationModel model2 = new ImageProcessingApplicationModelImpl();
    model2.addImage(new PPMImage("src/images/example2.ppm"), "ex2");
    model2.addImage(new PPMImage("src/images/example1.ppm"), "ex1");
    String[] names = model2.getImageNames();

    assertArrayEquals(new String[]{"ex2", "ex1"}, names);
  }

  @Test
  public void testGetNamesInitModelDuplicateImages() {
    model.addImage(new PPMImage("src/images/example2.ppm"), "ex2");
    String[] names = model.getImageNames();

    assertArrayEquals(new String[]{"ex2", "ex1"}, names);
  }

  @Test
  public void testGetNamesInitModelDuplicateImagesDifferentNames() {
    model.addImage(new PPMImage("src/images/example2.ppm"), "ex3");
    String[] names = model.getImageNames();

    assertArrayEquals(new String[]{"ex3", "ex2", "ex1"}, names);
  }

}
