import org.junit.Test;

import image.Image;
import image.ImageAdapt;
import image.ImageFactory;
import image.PPMImage;

import static org.junit.Assert.assertEquals;

/**
 * Image factory test class. Tests image factory methods and exceptions.
 */
public class ImageFactoryTest {

  // -------------------- TEST EXCEPTIONS --------------------//

  @Test(expected = IllegalArgumentException.class)
  public void testUnsupportedImageType() {
    ImageFactory.factoryBuildImage("file.unsupported");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnsupportedImageTypeFake() {
    ImageFactory.factoryBuildImage("file.fakeImageType");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnsupportedImageTypeABC() {
    ImageFactory.factoryBuildImage("file.abc");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnsupportedImageTypeNothing() {
    ImageFactory.factoryBuildImage("file.");
  }

  // -------------------- TEST VALID INPUT --------------------//

  @Test
  public void testValidPPM1() {
    Image image = ImageFactory.factoryBuildImage("src/images/Koala.ppm");
    assertEquals(new PPMImage("src/images/Koala.ppm"), image);
  }

  @Test
  public void testValidPPM2() {
    Image image = ImageFactory.factoryBuildImage("src/images/example1.ppm");
    assertEquals(new PPMImage("src/images/example1.ppm"), image);
  }

  @Test
  public void testValidPPM3() {
    Image image = ImageFactory.factoryBuildImage("src/images/example2.ppm");
    assertEquals(new PPMImage("src/images/example2.ppm"), image);
  }

  @Test
  public void testValidJPG() {
    Image image = ImageFactory.factoryBuildImage("res/manhattan.jpg");
    assertEquals(new ImageAdapt("res/manhattan.jpg"), image);
  }

  @Test
  public void testValidBMP() {
    Image image = ImageFactory.factoryBuildImage("res/manhattan.bmp");
    assertEquals(new ImageAdapt("res/manhattan.bmp"), image);
  }

  @Test
  public void testValidPNG() {
    Image image = ImageFactory.factoryBuildImage("res/manhattan.png");
    assertEquals(new ImageAdapt("res/manhattan.png"), image);
  }

}
