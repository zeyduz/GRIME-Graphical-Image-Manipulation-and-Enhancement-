import image.Image;
import image.Pixel;
import imageprocessing.model.ImageProcessingApplicationModelState;

/**
 * Mock histogram test.
 */
public class MockHistogram {

  // INVARIANT: model is never null
  private ImageProcessingApplicationModelState model;

  /**
   * Construct a mock histogram.
   *
   * @param red red output log
   * @param green green output log
   * @param blue blue output log
   * @param intensity intensity output log
   * @param model     model to get data from.
   * @throws IllegalArgumentException if either arguments are null.
   */
  public MockHistogram(int[] red, int[] green, int[] blue, int[] intensity,
                       ImageProcessingApplicationModelState model) throws IllegalArgumentException {
    this.model = model;
    makeHistogram(red, green, blue, intensity);
  }

  // makes the histogram
  private void makeHistogram(int[] red, int[] green, int[] blue, int[] intensity) {
    // First, get the frequencies of each of the channels' values' frequencies
    int[] redFrequencies = new int[256];
    int[] greenFrequencies = new int[256];
    int[] blueFrequencies = new int[256];
    int[] intensityFrequencies = new int[256];
    Image latestImage = model.getImageCopy(model.getNewestImageName());
    for (int row = 0; row < latestImage.getHeight(); row++) {
      for (int col = 0; col < latestImage.getWidth(); col++) {
        Pixel pixel = latestImage.getPixel(row, col);
        redFrequencies[pixel.getRed()]++;
        greenFrequencies[pixel.getGreen()]++;
        blueFrequencies[pixel.getBlue()]++;
        intensityFrequencies[pixel.getIntensity()]++;
      }
    }

    /*
    We want to normalize the frequencies so that they fit into a specific panel size.

    To do this, we find:
      total panel height
      __________________
      max value in freq.

    This gives us a scaling factor that we will multiply each of the frequencies by.
     */
    int max = redFrequencies[0];
    for (int i = 0; i < 256; i++) {
      if (redFrequencies[i] > max) {
        max = redFrequencies[i];
      }
      if (greenFrequencies[i] > max) {
        max = greenFrequencies[i];
      }
      if (blueFrequencies[i] > max) {
        max = blueFrequencies[i];
      }
      if (blueFrequencies[i] > max) {
        max = blueFrequencies[i];
      }
    }

    // Calculate the scaling factor
    double scalingFactor = 500.0 / (double) max;

    // Scale the frequencies by that factor and round.
    // this is the final output for the mock
    for (int i = 0; i < 256; i++) {
      red[i] = (int) Math.round(redFrequencies[i] * scalingFactor);
      green[i] = (int) Math.round(greenFrequencies[i] * scalingFactor);
      blue[i] = (int) Math.round(blueFrequencies[i] * scalingFactor);
      intensity[i] = (int) Math.round(intensityFrequencies[i] * scalingFactor);
    }

  }
}
