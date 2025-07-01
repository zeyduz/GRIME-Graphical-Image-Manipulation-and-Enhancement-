import org.junit.Test;

import image.Image;
import image.ImageAdapt;
import image.RGBAPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test for Image Adapter.
 */
public class ImageAdaptTest {

  // ------ TEST CONSTRUCTORS ------ //

  @Test
  public void testImageAdaptConstructorPixels() {
    Image im = new ImageAdapt("res/example1.jpg");
    assertEquals(new RGBAPixel(255, 0, 0, 0, 255),
            im.getPixel(0, 0));
    assertEquals(new RGBAPixel(255, 255, 0, 0, 255),
            im.getPixel(0, 1));
    assertEquals(new RGBAPixel(255, 0, 0, 0, 255),
            im.getPixel(0, 2));
    assertEquals(new RGBAPixel(255, 255, 0, 255, 255),
            im.getPixel(1, 0));
    assertEquals(new RGBAPixel(255, 0, 0, 100, 255),
            im.getPixel(1, 1));
    assertEquals(new RGBAPixel(255, 20, 20, 20, 255),
            im.getPixel(1, 2));
  }

  @Test
  public void testImageAdaptConstructorJPG() {
    Image im = new ImageAdapt("res/manhattan.jpg");
    assertEquals(500, im.getWidth());
    assertEquals(200, im.getHeight());
  }

  @Test
  public void testImageAdaptConstructorBMP() {
    Image im = new ImageAdapt("res/manhattan.bmp");
    assertEquals(200, im.getWidth());
    assertEquals(80, im.getHeight());
  }

  @Test
  public void testImageAdaptMakeImageFileJPG() {
    Image im = new ImageAdapt("res/manhattan.jpg");
    Image im2 = im.makeImage("res/manhattan.jpg");
    assertEquals(im, im2);
  }

  @Test
  public void testImageAdaptMakeImageFileBMP() {
    Image im = new ImageAdapt("res/manhattan.bmp");
    Image im2 = im.makeImage("res/manhattan.bmp");
    assertEquals(im, im2);
  }

  // ------ TEST CONSTRUCTOR EXCEPTIONS ------ //

  @Test(expected = IllegalArgumentException.class)
  public void testImageAdaptBadFilePath() {
    Image im = new ImageAdapt("hey.there");
  }

  // ------ TEST MAKE COPY ------ //

  @Test
  public void testMakeCopyJPG() {
    Image im = new ImageAdapt("res/manhattan.jpg");
    Image im2 = im.makeCopy();
    assertEquals(im, im2);
  }

  @Test
  public void testMakeCopyBMP() {
    Image im = new ImageAdapt("res/manhattan.bmp");
    Image im2 = im.makeCopy();
    assertEquals(im, im2);
  }

  // ------ TEST GETTERS ------ //

  @Test
  public void testGetValue() {
    Image im = new ImageAdapt("res/example1.jpg");
    assertEquals(0, im.getValue(0, 0));
    assertEquals(255, im.getValue(0, 1));
    assertEquals(0, im.getValue(0, 2));
    assertEquals(255, im.getValue(1, 0));
    assertEquals(100, im.getValue(1, 1));
    assertEquals(20, im.getValue(1, 2));
  }

  @Test
  public void testGetIntensity() {
    Image im = new ImageAdapt("res/example1.bmp");
    assertEquals(0, im.getIntensity(0, 0), 0.01);
    assertEquals(85, im.getIntensity(0, 1), 0.01);
    assertEquals(0, im.getIntensity(0, 2), 0.01);
    assertEquals(170, im.getIntensity(1, 0), 0.01);
    assertEquals(100 / 3.0, im.getIntensity(1, 1), 0.01);
    assertEquals(20, im.getIntensity(1, 2), 0.01);
  }

  @Test
  public void testGetLuma() {
    Image im = new ImageAdapt("res/example1.bmp");
    assertEquals(0, im.getLuma(0, 0), 0.01);
    assertEquals(54.213, im.getLuma(0, 1), 0.01);
    assertEquals(0, im.getLuma(0, 2), 0.01);
    assertEquals(236.58899, im.getLuma(1, 0), 0.01);
    assertEquals(71.52, im.getLuma(1, 1), 0.01);
    assertEquals(20, im.getLuma(1, 2), 0.01);
  }

  @Test
  public void testEquals() {
    Image im1 = new ImageAdapt("res/example1.bmp");
    Image im2 = new ImageAdapt("res/example1.jpg");
    Image im3 = new ImageAdapt("res/manhattan.bmp");

    assertEquals(im1, im2);
    assertEquals(im2, im1);

    assertNotEquals(im1, im3);
    assertNotEquals(im3, im1);
    assertNotEquals(im2, im3);
    assertNotEquals(im3, im2);
  }

  @Test
  public void testHashCode() {
    assertEquals(1056, new ImageAdapt("res/example1.bmp").hashCode());
    assertEquals(1056, new ImageAdapt("res/example1.jpg").hashCode());
    assertEquals(16661, new ImageAdapt("res/manhattan.jpg").hashCode());
  }
}
