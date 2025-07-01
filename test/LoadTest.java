import org.junit.Test;

import image.Image;
import image.PPMImage;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.imagecommand.ImageCommand;
import imageprocessing.model.imagecommand.LoadCommand;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Load command tests.
 */
public class LoadTest {

  private ImageProcessingApplicationModel model;

  private Image expected_image1;

  private void setupLoad() {
    this.model = new ImageProcessingApplicationModelImpl();
    this.model.addImage(new PPMImage("src/images/example1.ppm"), "example1");
    this.model.addImage(new PPMImage("src/images/example2.ppm"), "example2");

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 255, 0, 0, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 255, 0, 255, 255);
    expected[1][1] = new RGBAPixel(255, 0, 0, 100, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    expected_image1 = new PPMImage(expected, 3, 2);
  }

  @Test
  public void testExample1LoadPPM() {
    setupLoad();
    // Create and Execute the Command
    ImageCommand cmd = new LoadCommand("src/images/example1.ppm", "example1");
    cmd.execute(model);

    // Actual
    Image load = model.getImageCopy("example1");

    assertEquals(expected_image1, load);
  }

  @Test
  public void testExample1LoadPNG() {
    setupLoad();
    // Create and Execute the Command
    ImageCommand cmd = new LoadCommand("res/example1.png", "example1");
    cmd.execute(model);

    // Actual
    Image load = model.getImageCopy("example1");

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(load.getPixel(i, j), expected_image1.getPixel(i, j));
      }
    }
  }

  @Test
  public void testExample1LoadJPG() {
    setupLoad();
    // Create and Execute the Command
    ImageCommand cmd = new LoadCommand("res/example1.jpg", "example1");
    cmd.execute(model);

    // Actual
    Image load = model.getImageCopy("example1");

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(load.getPixel(i, j), expected_image1.getPixel(i, j));
      }
    }
  }

  @Test
  public void testExample1LoadBMP() {
    setupLoad();
    // Create and Execute the Command
    ImageCommand cmd = new LoadCommand("res/example1.bmp", "example1");
    cmd.execute(model);

    // Actual
    Image load = model.getImageCopy("example1");

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(load.getPixel(i, j), expected_image1.getPixel(i, j));
      }
    }
  }

  @Test
  public void testExample2LoadPPM() {
    setupLoad();
    // Create and Execute the Command
    ImageCommand cmd = new LoadCommand("src/images/example2.ppm", "example2");
    cmd.execute(model);

    // Test that the Command Added the New Image to the Model With The Correct Properties
    // by comparing it to an expected image

    // Actual
    Image load = model.getImageCopy("example2");

    // Expected
    Pixel[][] expected = new RGBAPixel[3][2];
    expected[0][0] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[0][1] = new RGBAPixel(255, 0, 255, 0, 255);
    expected[1][0] = new RGBAPixel(255, 100, 100, 100, 255);
    expected[1][1] = new RGBAPixel(255, 0, 255, 255, 255);
    expected[2][0] = new RGBAPixel(255, 255, 0, 255, 255);
    expected[2][1] = new RGBAPixel(255, 0, 0, 100, 255);

    Image expected_image = new PPMImage(expected, 2, 3);

    assertEquals(expected_image, load);
  }
}
