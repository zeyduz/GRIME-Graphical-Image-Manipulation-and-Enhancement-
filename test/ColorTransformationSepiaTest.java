import org.junit.Test;

import image.Image;
import image.PPMImage;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.imagecommand.ColorTransformation;
import imageprocessing.model.imagecommand.ImageCommand;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test for Color-transformation sepia.
 */
public class ColorTransformationSepiaTest {

  ImageProcessingApplicationModel model;

  private void setupSepia() {
    this.model = new ImageProcessingApplicationModelImpl();
    this.model.addImage(new PPMImage("src/images/example1.ppm"), "example1");
    this.model.addImage(new PPMImage("src/images/example2.ppm"), "example2");
  }

  @Test
  public void testExample1Sepia() {
    setupSepia();
    // Create and Execute the Command
    ImageCommand cmd = new ColorTransformation("example1", "example1-new",
            new double[][]{
              new double[]{0.393, 0.769, 0.189},
              new double[]{0.349, 0.686, 0.168},
              new double[]{0.272, 0.534, 0.131}});
    cmd.execute(model);

    // Test that the Command Added the New Image to the Model With The Correct Properties
    // by comparing it to an expected image

    // Actual
    Image greyscale = model.getImageCopy("example1-new");

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 100, 89, 69, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 148, 132, 103, 255);
    expected[1][1] = new RGBAPixel(255, 19, 17, 13, 255);
    expected[1][2] = new RGBAPixel(255, 27, 24, 19, 255);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(expected[i][j], greyscale.getPixel(i, j));
      }
    }
  }

  @Test
  public void testExample2Sepia() {
    setupSepia();
    // Create and Execute the Command
    ImageCommand cmd = new ColorTransformation("example2", "example2-new",
            new double[][]{
              new double[]{0.393, 0.769, 0.189},
              new double[]{0.349, 0.686, 0.168},
              new double[]{0.272, 0.534, 0.131}});
    cmd.execute(model);

    // Test that the Command Added the New Image to the Model With The Correct Properties
    // by comparing it to an expected image

    // Actual
    Image greyscale = model.getImageCopy("example2-new");

    // Expected
    Pixel[][] expected = new RGBAPixel[3][2];
    expected[0][0] = new RGBAPixel(255, 255, 255, 239, 255);
    expected[0][1] = new RGBAPixel(255, 196, 175, 136, 255);
    expected[1][0] = new RGBAPixel(255, 135, 120, 94, 255);
    expected[1][1] = new RGBAPixel(255, 244, 218, 170, 255);
    expected[2][0] = new RGBAPixel(255, 148, 132, 103, 255);
    expected[2][1] = new RGBAPixel(255, 19, 17, 13, 255);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(expected[i][j], greyscale.getPixel(i, j));
      }
    }
  }

}
