import org.junit.Test;

import image.Image;
import image.PPMImage;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.imagecommand.BlueComponent;
import imageprocessing.model.imagecommand.ImageCommand;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for BlueComponent.
 */
public class BluComponentTest {

  ImageProcessingApplicationModel model;

  private void setupblue() {
    this.model = new ImageProcessingApplicationModelImpl();
    this.model.addImage(new PPMImage("src/images/example1.ppm"), "example1");
    this.model.addImage(new PPMImage("src/images/example2.ppm"), "example2");
  }

  @Test
  public void testExample1BlueComponent() {
    setupblue();
    // Create and Execute the Command
    ImageCommand cmd = new BlueComponent("example1", "example1-blue");
    cmd.execute(model);

    // Test that the Command Added the New Image to the Model With The Correct Properties
    // by comparing it to an expected image

    // Actual
    Image blue = model.getImageCopy("example1-blue");

    // Expected
    Pixel[][] expected = new RGBAPixel[2][3];
    expected[0][0] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][1] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[0][2] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[1][1] = new RGBAPixel(255, 100, 100, 100, 255);
    expected[1][2] = new RGBAPixel(255, 20, 20, 20, 255);

    Image expected_image = new PPMImage(expected, 3, 2);

    assertEquals(expected_image, blue);
  }

  @Test
  public void testExample2BlueComponent() {
    setupblue();
    // Create and Execute the Command
    ImageCommand cmd = new BlueComponent("example2", "example2-blue");
    cmd.execute(model);

    // Test that the Command Added the New Image to the Model With The Correct Properties
    // by comparing it to an expected image

    // Actual
    Image blue = model.getImageCopy("example2-blue");

    // Expected
    Pixel[][] expected = new RGBAPixel[3][2];
    expected[0][0] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[0][1] = new RGBAPixel(255, 0, 0, 0, 255);
    expected[1][0] = new RGBAPixel(255, 100, 100, 100, 255);
    expected[1][1] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[2][0] = new RGBAPixel(255, 255, 255, 255, 255);
    expected[2][1] = new RGBAPixel(255, 100, 100, 100, 255);

    Image expected_image = new PPMImage(expected, 2, 3);

    assertEquals(expected_image, blue);
  }

}
