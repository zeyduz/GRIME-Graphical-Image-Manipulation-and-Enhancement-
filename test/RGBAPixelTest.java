import org.junit.Before;
import org.junit.Test;

import image.RGBAPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * RGBAPixel test class. Tests RGBAPixel methods and exceptions.
 */
public class RGBAPixelTest {
  RGBAPixel pixel;
  RGBAPixel pixel1;
  RGBAPixel pixel2;
  RGBAPixel pixel3;
  RGBAPixel pixel4;
  RGBAPixel pixel5;
  RGBAPixel pixelC;
  RGBAPixel pixel1C;
  RGBAPixel pixel2C;
  RGBAPixel pixel3C;
  RGBAPixel pixel4C;
  RGBAPixel pixel5C;

  @Before
  public void init() {
    pixel = new RGBAPixel(255, 100, 200, 255, 1);
    pixel1 = new RGBAPixel(400, 250, 200, 300, 0);
    pixel2 = new RGBAPixel(10, 1, 2, 3, 100);
    pixel3 = new RGBAPixel(255, 20, 20, 20, -2);
    pixel4 = new RGBAPixel(255, -1, -1, -1, 259);
    pixel5 = new RGBAPixel(255, 300, 350, 500, -3);

    pixelC = new RGBAPixel(255, 100, 200, 255, 1);
    pixel1C = new RGBAPixel(400, 250, 200, 300, 0);
    pixel2C = new RGBAPixel(10, 1, 2, 3, 100);
    pixel3C = new RGBAPixel(255, 20, 20, 20, -2);
    pixel4C = new RGBAPixel(255, -1, -1, -1, 259);
    pixel5C = new RGBAPixel(255, 300, 350, 500, -3);

  }

  @Test
  public void testValidConstructor() {
    RGBAPixel pixel = new RGBAPixel(255, -1, 260, 255, 2);
    assertEquals(0, pixel.getRed());
    assertEquals(255, pixel.getGreen());
    assertEquals(255, pixel.getBlue());
    assertEquals(255, pixel.getMaxValue());
    assertEquals(2.0, pixel.getAlpha(), 0.0001);
  }

  @Test
  public void testGetRed() {
    assertEquals(100, pixel.getRed());
    assertEquals(250, pixel1.getRed());
    assertEquals(1, pixel2.getRed());
    assertEquals(20, pixel3.getRed());
    // autocorrects
    assertEquals(0, pixel4.getRed());
    // autocorrects
    assertEquals(255, pixel5.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(200, pixel.getGreen());
    assertEquals(200, pixel1.getGreen());
    assertEquals(2, pixel2.getGreen());
    assertEquals(20, pixel3.getGreen());
    // autocorrects
    assertEquals(0, pixel4.getGreen());
    // autocorrects
    assertEquals(255, pixel5.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(255, pixel.getBlue());
    assertEquals(255, pixel1.getBlue());
    assertEquals(3, pixel2.getBlue());
    assertEquals(20, pixel3.getBlue());
    // autocorrects
    assertEquals(0, pixel4.getBlue());
    // autocorrects
    assertEquals(255, pixel5.getBlue());
  }

  @Test
  public void testGetAlpha() {
    assertEquals(1.0, pixel.getAlpha(), 0.001);
    assertEquals(0.0, pixel1.getAlpha(), 0.001);
    // autocorrects
    assertEquals(10.0, pixel2.getAlpha(), 0.001);
    assertEquals(0, pixel3.getAlpha(), 0.001);
    assertEquals(255, pixel4.getAlpha(), 0.001);
    assertEquals(0, pixel5.getAlpha(), 0.001);
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(255, pixel.getMaxValue());
    assertEquals(255, pixel1.getMaxValue());
    assertEquals(3, pixel2.getMaxValue());
    assertEquals(20, pixel3.getMaxValue());
    // autocorrects
    assertEquals(0, pixel4.getMaxValue());
    assertEquals(255, pixel5.getMaxValue());
  }

  @Test
  public void testEquals() {
    assertEquals(pixel, pixelC);
    assertEquals(pixel1C, pixel1);
    assertEquals(pixel2C, pixel2);
    assertEquals(pixel3C, pixel3);
    assertEquals(pixel4C, pixel4);
    assertEquals(pixel5C, pixel5);
    assertEquals(pixel, pixel);
    RGBAPixel pixel6 = pixel;
    assertEquals(pixelC, pixel6);

    assertNotEquals(pixel5C, pixel);
    assertNotEquals(pixel5C, pixel4);
    assertNotEquals(pixel5C, pixel3C);

    assertNotEquals(pixel, new StringBuilder());
  }

  @Test
  public void testHashCode() {
    assertEquals(4102727, pixel.hashCode());
    assertEquals(4102727, pixelC.hashCode());
    assertEquals(8571376, pixel1.hashCode());
    assertEquals(955337, pixel2.hashCode());
    assertEquals(1539181, pixel3.hashCode());
    assertEquals(923776, pixel4.hashCode());
    assertEquals(8773186, pixel5.hashCode());

  }

}
