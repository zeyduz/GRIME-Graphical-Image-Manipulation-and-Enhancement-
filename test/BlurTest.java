import org.junit.Test;

import image.Image;
import image.PPMImage;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.imagecommand.GaussianBlur;
import imageprocessing.model.imagecommand.ImageCommand;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Blur command test.
 */
public class BlurTest {

  ImageProcessingApplicationModel model;

  private void setupBlur() {
    this.model = new ImageProcessingApplicationModelImpl();
    this.model.addImage(new PPMImage("src/images/example1.ppm"), "example1");
    this.model.addImage(new PPMImage("src/images/example2.ppm"), "example2");
  }

  @Test
  public void testExample1Blur() {
    setupBlur();
    // Create and Execute the Command
    ImageCommand cmd = new GaussianBlur("example1", "example1-new");
    cmd.execute(model);

    // Test that the Command Added the New Image to the Model With The Correct Properties
    // by comparing it to an expected image

    // Actual
    Image greyscale = model.getImageCopy("example1-new");

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 64, 0, 38, 255);
    expected[0][1] = new RGBAPixel(255, 81, 1, 30, 255);
    expected[0][2] = new RGBAPixel(255, 34, 3, 9, 255);
    expected[1][0] = new RGBAPixel(255, 80, 0, 76, 255);
    expected[1][1] = new RGBAPixel(255, 66, 3, 59, 255);
    expected[1][2] = new RGBAPixel(255, 21, 5, 18, 255);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(expected[i][j], greyscale.getPixel(i, j));
      }
    }
  }

  @Test
  public void testExample2Blur() {
    setupBlur();
    // Create and Execute the Command
    ImageCommand cmd = new GaussianBlur("example2", "example2-new");
    cmd.execute(model);

    // Test that the Command Added the New Image to the Model With The Correct Properties
    // by comparing it to an expected image

    // Actual
    Image greyscale = model.getImageCopy("example2-new");

    // Expected
    Pixel[][] expected = new RGBAPixel[3][2];
    expected[0][0] = new RGBAPixel(255, 76, 124, 92, 255);
    expected[0][1] = new RGBAPixel(255, 38, 134, 70, 255);
    expected[1][0] = new RGBAPixel(255, 89, 105, 127, 255);
    expected[1][1] = new RGBAPixel(255, 44, 124, 121, 255);
    expected[2][0] = new RGBAPixel(255, 76, 28, 105, 255);
    expected[2][1] = new RGBAPixel(255, 38, 38, 95, 255);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(expected[i][j], greyscale.getPixel(i, j));
      }
    }
  }

}
