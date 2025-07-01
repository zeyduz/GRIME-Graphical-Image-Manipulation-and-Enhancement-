import org.junit.Test;

import image.ImageUtil;
import image.Pixel;
import image.RGBAPixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Image util test class. Tests image util methods and exceptions.
 */
public class ImageUtilTest {

  @Test
  public void testGetPPMWidth() {
    int actual = ImageUtil.getPPMWidth("src/images/Koala.ppm");
    assertEquals(300, actual);

    actual = ImageUtil.getPPMWidth("src/images/example1.ppm");
    assertEquals(3, actual);

    actual = ImageUtil.getPPMWidth("src/images/example2.ppm");
    assertEquals(2, actual);

  }

  @Test
  public void testGetPPMHeight() {
    int actual = ImageUtil.getPPMHeight("src/images/Koala.ppm");
    assertEquals(225, actual);

    actual = ImageUtil.getPPMHeight("src/images/example1.ppm");
    assertEquals(2, actual);

    actual = ImageUtil.getPPMHeight("src/images/example2.ppm");
    assertEquals(3, actual);

  }

  @Test
  public void testGetPPMPixelsEx1() {
    Pixel[][] actual = ImageUtil.getPPMPixels("src/images/example1.ppm");
    Pixel[][] expected = new Pixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 255, 0, 0, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 255, 0, 255, 255);
    expected[1][1] = new RGBAPixel(255, 0, 0, 100, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    assertArrayEquals(expected, actual);

    actual = ImageUtil.getPPMPixels("src/images/example2.ppm");
    expected = new Pixel[3][2];
    expected[0][0] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[0][1] = new RGBAPixel(255, 0, 255, 0, 255);
    expected[1][0] = new RGBAPixel(255, 100, 100, 100, 255);
    expected[1][1] = new RGBAPixel(255, 0, 255, 255, 255);
    expected[2][0] = new RGBAPixel(255, 255, 0, 255, 255);
    expected[2][1] = new RGBAPixel(255, 0, 0, 100, 255);

    assertArrayEquals(expected, actual);
  }

  @Test
  public void testGetImageType() {
    assertEquals("ppm", ImageUtil.getImageType("src/images/Koala.ppm"));
    assertEquals("ppm", ImageUtil.getImageType("src/images/example1.ppm"));
    assertEquals("ppm", ImageUtil.getImageType("src/images/example2.ppm"));
    assertEquals("ppm", ImageUtil.getImageType("src/images/grapes.ppm"));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetTypeBadPath1() {
    ImageUtil.getImageType("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetTypeBadPath2() {
    ImageUtil.getImageType("not a file");
  }

}