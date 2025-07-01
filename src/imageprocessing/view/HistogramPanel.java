package imageprocessing.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import image.Image;
import image.Pixel;
import imageprocessing.model.ImageProcessingApplicationModelState;

/**
 * Represents a histogram of a specific model.
 */
public class HistogramPanel extends JPanel {

  // INVARIANT: model is not null
  private final ImageProcessingApplicationModelState model;

  /**
   * Construct a histogram panel based on the latest image in the model.
   *
   * @param model The model to be used
   * @throws IllegalArgumentException If the model is null
   */
  public HistogramPanel(ImageProcessingApplicationModelState model)
          throws IllegalArgumentException {
    super();
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.model = model;
    this.makeHistogram();
  }

  /**
   * Updates the histogram.
   */
  public void updateHistogram() {
    makeHistogram();
    repaint();
  }

  // makes the histogram
  private void makeHistogram() {

    // each bar is 3 pixels wide
    // no spacing between the bars
    this.setPreferredSize(new Dimension(768, 400));
    this.setLayout(new GridLayout(1, 256, 0, 0));

    for (int i = 0; i < 256; i++) {
      try {
        this.remove(0);
      } catch (ArrayIndexOutOfBoundsException e) {
        break;
      }
    }

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
    for (int i = 0; i < 256; i++) {
      redFrequencies[i] = (int) Math.round(redFrequencies[i] * scalingFactor);
      greenFrequencies[i] = (int) Math.round(greenFrequencies[i] * scalingFactor);
      blueFrequencies[i] = (int) Math.round(blueFrequencies[i] * scalingFactor);
      intensityFrequencies[i] = (int) Math.round(intensityFrequencies[i] * scalingFactor);
    }

    for (int i = 0; i < 256; i++) {
      HistogramBar bar = new HistogramBar(redFrequencies[i], greenFrequencies[i],
              blueFrequencies[i], intensityFrequencies[i]);
      this.add(bar, i);
    }
  }
}

