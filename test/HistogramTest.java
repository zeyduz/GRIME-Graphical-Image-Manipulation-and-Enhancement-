import org.junit.Before;
import org.junit.Test;

import image.ImageFactory;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;

import static org.junit.Assert.assertArrayEquals;

/**
 * Test for the Histogram.
 */
public class HistogramTest {

  private int[] red;
  private int[] green;
  private int[] blue;
  private int[] intensity;
  private ImageProcessingApplicationModel model;

  @Before
  public void init() {
    red = new int[256];
    green = new int[256];
    blue = new int[256];
    intensity = new int[256];
    model = new ImageProcessingApplicationModelImpl();
    model.addImage(ImageFactory.factoryBuildImage("src/images/example1.ppm"),
            "image1");
  }

  @Test
  public void testHistogramExample1() {
    // Initialize the values
    MockHistogram mockHistogram = new MockHistogram(red, green, blue, intensity, model);

    // expected values for the arrays
    int[] expectedRed = new int[256];
    expectedRed[0] = 300;
    expectedRed[20] = 100;
    expectedRed[255] = 200;

    assertArrayEquals(expectedRed, red);

    // expected values for the arrays
    int[] expectedGreen = new int[256];
    expectedGreen[0] = 500;
    expectedGreen[20] = 100;

    assertArrayEquals(expectedGreen, green);

    // expected values for the arrays
    int[] expectedBlue = new int[256];
    expectedBlue[0] = 300;
    expectedBlue[20] = 100;
    expectedBlue[100] = 100;
    expectedBlue[255] = 100;

    assertArrayEquals(expectedBlue, blue);

    // expected values for the arrays
    int[] expectedIntensity = new int[256];
    expectedIntensity[0] = 200;
    expectedIntensity[20] = 100;
    expectedIntensity[33] = 100;
    expectedIntensity[85] = 100;
    expectedIntensity[170] = 100;

    assertArrayEquals(expectedIntensity, intensity);
  }

}
