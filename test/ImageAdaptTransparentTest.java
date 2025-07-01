import org.junit.Test;

import image.Image;
import image.ImageAdapt;
import image.ImageAdaptTransparent;
import image.RGBAPixel;

import static org.junit.Assert.assertEquals;

/**
 * Test for Image Adapter that supports transparency.
 */
public class ImageAdaptTransparentTest {

  // ------ TEST CONSTRUCTORS ------ //

  @Test
  public void testImageAdaptTransparentConstructorEx1Pixels() {
    Image im = new ImageAdapt("res/example1.png");
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
  public void testImageAdaptTransparentConstructorPNG() {
    Image im = new ImageAdaptTransparent("res/manhattan.png");
    assertEquals(200, im.getWidth());
    assertEquals(80, im.getHeight());
  }

  @Test
  public void testImageAdaptMakeImagePNG() {
    Image im = new ImageAdaptTransparent("res/example1.png");
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


  // ------ TEST CONSTRUCTOR EXCEPTIONS ------ //

  @Test(expected = IllegalArgumentException.class)
  public void testImageAdaptTransparentException() {
    Image im = new ImageAdaptTransparent("bad path");
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

  @Test(expected = IllegalArgumentException.class)
  public void testImageAdaptTransparentException2() {
    Image im = new ImageAdaptTransparent("its.bad");
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

  // ------ TEST GETTERS ------ //

  @Test
  public void testMakeCopyExample1() {
    Image im = new ImageAdaptTransparent("res/example1.png");
    Image copy = im.makeCopy();
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

}
