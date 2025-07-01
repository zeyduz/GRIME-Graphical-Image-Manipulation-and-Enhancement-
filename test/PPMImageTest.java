import org.junit.Test;

import java.io.File;

import image.PPMImage;
import image.Image;
import image.Pixel;
import image.RGBAPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * PPM image test class. Tests PPMImage methods and exceptions.
 */
public class PPMImageTest {

  // -------------------- TEST CONSTRUCTOR EXCEPTIONS --------------------//

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNotPPMFileJPG() {
    Image i = new PPMImage("src/images/Koala.jpg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNotPPMFilePNG() {
    Image i = new PPMImage("src/images/Koala.png");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorPixelsNull() {
    new PPMImage(null, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadHeight() {
    Pixel[][] pixels = new Pixel[1][1];
    pixels[0][0] = new RGBAPixel(255, 0, 0, 0, 1);
    new PPMImage(pixels, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadWidth() {
    Pixel[][] pixels = new Pixel[1][1];
    pixels[0][0] = new RGBAPixel(255, 0, 0, 0, 1);
    new PPMImage(pixels, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadWidthAndHeight() {
    Pixel[][] pixels = new Pixel[1][1];
    pixels[0][0] = new RGBAPixel(255, 0, 0, 0, 1);
    new PPMImage(pixels, 0, 10);
  }

  // -------------------- TEST CONSTRUCTORS --------------------//
  @Test
  public void testConstructorPathEx1() {
    Image i = new PPMImage("src/images/example1.ppm");

    assertEquals(i.getPixel(0, 0), new RGBAPixel(255, 0, 0, 0, 255));
    assertEquals(i.getPixel(0, 2), new RGBAPixel(255, 0, 0, 0, 255));
    assertEquals(i.getPixel(1, 0), new RGBAPixel(255, 255, 0, 255, 255));
    assertEquals(i.getPixel(1, 2), new RGBAPixel(255, 20, 20, 20, 255));
  }

  @Test
  public void testConstructorPathEx2() {
    Image i = new PPMImage("src/images/example2.ppm");

    assertEquals(i.getPixel(0, 0), new RGBAPixel(255, 255, 255, 255, 255));
    assertEquals(i.getPixel(1, 0), new RGBAPixel(255, 100, 100, 100, 255));
    assertEquals(i.getPixel(2, 0), new RGBAPixel(255, 255, 0, 255, 255));
  }

  @Test
  public void testConstructorArgs() {
    Pixel[][] pixels = new Pixel[2][2];
    pixels[0][0] = new RGBAPixel(255, 0, 1, 2, 255);
    pixels[0][1] = new RGBAPixel(255, 0, 0, 100, 255);
    pixels[1][0] = new RGBAPixel(255, 1, 2, 3, 255);
    pixels[1][1] = new RGBAPixel(255, 100, 200, 300, 255);
    int width = 2;
    int height = 2;

    Image i = new PPMImage(pixels, 2, 2);

    assertEquals(pixels[0][0], i.getPixel(0, 0));
    assertEquals(pixels[0][1], i.getPixel(0, 1));
    assertEquals(pixels[1][0], i.getPixel(1, 0));
    assertEquals(pixels[1][1], i.getPixel(1, 1));
    assertEquals(2, i.getWidth());
    assertEquals(2, i.getHeight());
  }

  // -------------------- TEST MAKE IMAGE --------------------//

  @Test
  public void testMakeImagePath1() {
    Image i = new PPMImage("src/images/example1.ppm").makeImage("src/images/example1.ppm");

    assertEquals(i.getPixel(0, 0), new RGBAPixel(255, 0, 0, 0, 255));
    assertEquals(i.getPixel(0, 2), new RGBAPixel(255, 0, 0, 0, 255));
    assertEquals(i.getPixel(1, 0), new RGBAPixel(255, 255, 0, 255, 255));
    assertEquals(i.getPixel(1, 2), new RGBAPixel(255, 20, 20, 20, 255));
  }

  @Test
  public void testMakeImagePath2() {
    Image i = new PPMImage("src/images/example2.ppm").makeImage("src/images/example2.ppm");

    assertEquals(i.getPixel(0, 0), new RGBAPixel(255, 255, 255, 255, 255));
    assertEquals(i.getPixel(1, 0), new RGBAPixel(255, 100, 100, 100, 255));
    assertEquals(i.getPixel(2, 0), new RGBAPixel(255, 255, 0, 255, 255));
  }

  @Test
  public void testMakeImageArgs() {
    Pixel[][] pixels = new Pixel[2][2];
    pixels[0][0] = new RGBAPixel(255, 0, 1, 2, 255);
    pixels[0][1] = new RGBAPixel(255, 0, 0, 100, 255);
    pixels[1][0] = new RGBAPixel(255, 1, 2, 3, 255);
    pixels[1][1] = new RGBAPixel(255, 100, 200, 300, 255);
    int width = 2;
    int height = 2;

    Image i = new PPMImage(pixels, 2, 2).makeImage(pixels, 2, 2);

    assertEquals(pixels[0][0], i.getPixel(0, 0));
    assertEquals(pixels[0][1], i.getPixel(0, 1));
    assertEquals(pixels[1][0], i.getPixel(1, 0));
    assertEquals(pixels[1][1], i.getPixel(1, 1));
    assertEquals(2, i.getWidth());
    assertEquals(2, i.getHeight());
  }

  // -------------------- TEST MAKE FILE --------------------//

  @Test(expected = IllegalStateException.class)
  public void testFileAlreadyExists() {
    new PPMImage("src/images/example1.ppm").makeFile("src/images/example1.ppm");
  }

  @Test
  public void testMakeFileExample1() {
    Image expected = new PPMImage("src/images/example1.ppm");
    expected.makeFile("res/example1-copy.ppm");

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.ppm");

    Image actual = new PPMImage("res/example1-copy.ppm");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testMakeFileExample2() {
    Image expected = new PPMImage("src/images/example2.ppm");
    expected.makeFile("res/example2-copy.ppm");

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example2-copy.ppm");

    Image actual = new PPMImage("res/example2-copy.ppm");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testMakeFileKoala() {
    Image expected = new PPMImage("src/images/koala.ppm");
    expected.makeFile("res/koala-copy.ppm");

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/koala-copy.ppm");

    Image actual = new PPMImage("res/koala-copy.ppm");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBadPixel() {
    Image ex1 = new PPMImage("src/images/example1.ppm");
    ex1.getPixel(-4, 2);
  }


  // -------------------- TEST GET VALUE --------------------//

  @Test
  public void testGetValue() {
    Image ex1 = new PPMImage("src/images/example1.ppm");
    Image ex2 = new PPMImage("src/images/example2.ppm");

    assertEquals(0, ex1.getValue(0, 0));
    assertEquals(255, ex1.getValue(0, 1));
    assertEquals(0, ex1.getValue(0, 2));
    assertEquals(255, ex1.getValue(1, 0));
    assertEquals(100, ex1.getValue(1, 1));
    assertEquals(20, ex1.getValue(1, 2));

    assertEquals(255, ex2.getValue(0, 0));
    assertEquals(255, ex2.getValue(0, 1));
    assertEquals(100, ex2.getValue(1, 0));
    assertEquals(255, ex2.getValue(1, 1));
    assertEquals(255, ex2.getValue(2, 0));
    assertEquals(100, ex2.getValue(2, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBadValue() {
    Image ex1 = new PPMImage("src/images/example1.ppm");
    ex1.getValue(-4, 2);
  }

  // -------------------- TEST GET INTENSITY --------------------//

  @Test
  public void testGetIntensity() {
    Image ex1 = new PPMImage("src/images/example1.ppm");
    Image ex2 = new PPMImage("src/images/example2.ppm");

    assertEquals(0.0, ex1.getIntensity(0, 0), 0.0001);
    assertEquals(255 / 3.0, ex1.getIntensity(0, 1), 0.0001);
    assertEquals(0.0, ex1.getIntensity(0, 2), 0.0001);
    assertEquals(255 * 2 / 3.0, ex1.getIntensity(1, 0), 0.0001);
    assertEquals(100 / 3.0, ex1.getIntensity(1, 1), 0.0001);
    assertEquals(20.0, ex1.getIntensity(1, 2), 0.0001);

    assertEquals(255.0, ex2.getIntensity(0, 0), 0.0001);
    assertEquals(85.0, ex2.getIntensity(0, 1), 0.0001);
    assertEquals(100.0, ex2.getIntensity(1, 0), 0.0001);
    assertEquals(170.0, ex2.getIntensity(1, 1), 0.0001);
    assertEquals(170.0, ex2.getIntensity(2, 0), 0.0001);
    assertEquals(100.0 / 3.0, ex2.getIntensity(2, 1), 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBadIntensity() {
    Image ex1 = new PPMImage("src/images/example1.ppm");
    ex1.getIntensity(-4, 2);
  }

  // -------------------- TEST GET LUMA --------------------//

  @Test
  public void testGetLuma() {
    Image ex1 = new PPMImage("src/images/example1.ppm");
    Image ex2 = new PPMImage("src/images/example2.ppm");

    assertEquals(0.0, ex1.getLuma(0, 0), 0.0001);
    assertEquals(54.213, ex1.getLuma(0, 1), 0.0001);
    assertEquals(0.0, ex1.getLuma(0, 2), 0.0001);
    assertEquals(236.58899, ex1.getLuma(1, 0), 0.0001);
    assertEquals(71.52, ex1.getLuma(1, 1), 0.0001);
    assertEquals(19.9999, ex1.getLuma(1, 2), 0.0001);

    assertEquals(255.0, ex2.getLuma(0, 0), 0.0001);
    assertEquals(18.411, ex2.getLuma(0, 1), 0.0001);
    assertEquals(100.0, ex2.getLuma(1, 0), 0.0001);
    assertEquals(200.786999, ex2.getLuma(1, 1), 0.0001);
    assertEquals(236.588999, ex2.getLuma(2, 0), 0.0001);
    assertEquals(71.52, ex2.getLuma(2, 1), 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBadLuma() {
    Image ex1 = new PPMImage("src/images/example1.ppm");
    ex1.getValue(-4, 2);
  }

  // -------------------- TEST EQUALS --------------------//

  @Test
  public void testEquals() {
    Image ex1_path = new PPMImage("src/images/example1.ppm");

    Pixel[][] ex1_pixels = new Pixel[2][3];
    ex1_pixels[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[0][1] = new RGBAPixel(255, 255, 0, 0, 255);
    ex1_pixels[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[1][0] = new RGBAPixel(255, 255, 0, 255, 255);
    ex1_pixels[1][1] = new RGBAPixel(255, 0, 0, 100, 255);
    ex1_pixels[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image ex1_args = new PPMImage(ex1_pixels, 3, 2);

    assertEquals(ex1_path, ex1_args);
  }

  @Test
  public void testEqualsExact() {
    Image ex1_path = new PPMImage("src/images/example1.ppm");

    assertEquals(ex1_path, ex1_path);
  }

  @Test
  public void testNotEqualBadPixel() {
    Image ex1_path = new PPMImage("src/images/example1.ppm");

    Pixel[][] ex1_pixels = new Pixel[2][3];
    ex1_pixels[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[0][1] = new RGBAPixel(255, 255, 0, 0, 255);
    ex1_pixels[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[1][0] = new RGBAPixel(255, 0, 0, 255, 255);
    ex1_pixels[1][1] = new RGBAPixel(255, 0, 0, 100, 255);
    ex1_pixels[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image ex1_args = new PPMImage(ex1_pixels, 3, 2);

    assertFalse(ex1_path.equals(ex1_args));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNotEqualBadWidth() {
    Image ex1_path = new PPMImage("src/images/example1.ppm");

    Pixel[][] ex1_pixels = new Pixel[2][3];
    ex1_pixels[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[0][1] = new RGBAPixel(255, 255, 0, 0, 255);
    ex1_pixels[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[1][0] = new RGBAPixel(255, 0, 0, 255, 255);
    ex1_pixels[1][1] = new RGBAPixel(255, 0, 0, 100, 255);
    ex1_pixels[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image ex1_args = new PPMImage(ex1_pixels, 2, 2);

    assertFalse(ex1_path.equals(ex1_args));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotEqualBadHeight() {
    Image ex1_path = new PPMImage("src/images/example1.ppm");

    Pixel[][] ex1_pixels = new Pixel[2][3];
    ex1_pixels[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[0][1] = new RGBAPixel(255, 255, 0, 0, 255);
    ex1_pixels[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[1][0] = new RGBAPixel(255, 0, 0, 255, 255);
    ex1_pixels[1][1] = new RGBAPixel(255, 0, 0, 100, 255);
    ex1_pixels[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image ex1_args = new PPMImage(ex1_pixels, 3, -1);
  }

  @Test
  public void testNotEqualDifferentImages() {
    Image ex1_path = new PPMImage("src/images/example1.ppm");
    Image ex2_path = new PPMImage("src/images/example2.ppm");

    assertNotEquals(ex1_path, ex2_path);
  }

  // -------------------- TEST HASHCODE --------------------//

  @Test
  public void testHashCode() {
    Image ex1_path = new PPMImage("src/images/example1.ppm");

    Pixel[][] ex1_pixels = new Pixel[2][3];
    ex1_pixels[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[0][1] = new RGBAPixel(255, 255, 0, 0, 255);
    ex1_pixels[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    ex1_pixels[1][0] = new RGBAPixel(255, 255, 0, 255, 255);
    ex1_pixels[1][1] = new RGBAPixel(255, 0, 0, 100, 255);
    ex1_pixels[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image ex1_args = new PPMImage(ex1_pixels, 3, 2);

    assertEquals(-154737902, ex1_path.hashCode());
    assertEquals(-154737902, ex1_args.hashCode());

    Image koala = new PPMImage("src/images/koala.ppm");

    assertEquals(-1337442625, koala.hashCode());
  }

}
