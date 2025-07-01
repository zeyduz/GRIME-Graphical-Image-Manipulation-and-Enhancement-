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
 * Greyscale test class.
 */
public class ColorTransformationGreyscaleTest {

  ImageProcessingApplicationModel model;

  private void setupGreyscale() {
    this.model = new ImageProcessingApplicationModelImpl();
    this.model.addImage(new PPMImage("src/images/example1.ppm"), "example1");
    this.model.addImage(new PPMImage("src/images/example2.ppm"), "example2");
  }

  @Test
  public void testExample1Greyscale() {
    setupGreyscale();
    // Create and Execute the Command
    ImageCommand cmd = new ColorTransformation("example1", "example1-new",
            new double[][]{
              new double[]{0.2126, 0.7152, 0.0722},
              new double[]{0.2126, 0.7152, 0.0722},
              new double[]{0.2126, 0.7152, 0.0722}});
    cmd.execute(model);

    // Test that the Command Added the New Image to the Model With The Correct Properties
    // by comparing it to an expected image

    // Actual
    Image greyscale = model.getImageCopy("example1-new");

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 54, 54, 54, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 73, 73, 73, 255);
    expected[1][1] = new RGBAPixel(255, 7, 7, 7, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(expected[i][j], greyscale.getPixel(i, j));
      }
    }
  }

  @Test
  public void testExample2GreyScale() {
    setupGreyscale();
    // Create and Execute the Command
    ImageCommand cmd = new ColorTransformation("example2", "example2-new",
            new double[][]{
              new double[]{0.2126, 0.7152, 0.0722},
              new double[]{0.2126, 0.7152, 0.0722},
              new double[]{0.2126, 0.7152, 0.0722}});
    cmd.execute(model);

    // Test that the Command Added the New Image to the Model With The Correct Properties
    // by comparing it to an expected image

    // Actual
    Image greyscale = model.getImageCopy("example2-new");

    // Expected
    Pixel[][] expected = new RGBAPixel[3][2];
    expected[0][0] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[0][1] = new RGBAPixel(255, 182, 182, 182, 255);
    expected[1][0] = new RGBAPixel(255, 100, 100, 100, 255);
    expected[1][1] = new RGBAPixel(255, 201, 201, 201, 255);
    expected[2][0] = new RGBAPixel(255, 73, 73, 73, 255);
    expected[2][1] = new RGBAPixel(255, 7, 7, 7, 255);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(expected[i][j], greyscale.getPixel(i, j));
      }
    }
  }

}
