package imageprocessing.model.imagecommand;

import image.Image;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Represents a Gaussian Blur command.
 */
public class GaussianBlur implements ImageCommand {


  private final String originalName;
  private final String newName;

  private final double[][] gaussianFilter = new double[3][3];

  /**
   * Constructs a Flip Horizontal command object.
   *
   * @param originalName The name of the original image
   * @param newName      The name of the modified image
   *
   */
  public GaussianBlur(String originalName, String newName) {
    this.originalName = originalName;
    this.newName = newName;
    gaussianFilter[0][0] = 1.0 / 16.0;
    gaussianFilter[0][1] = 1.0 / 8.0;
    gaussianFilter[0][2] = 1.0 / 16.0;
    gaussianFilter[1][0] = 1.0 / 8.0;
    gaussianFilter[1][1] = 1.0 / 4.0;
    gaussianFilter[1][2] = 1.0 / 8.0;
    gaussianFilter[2][0] = 1.0 / 16.0;
    gaussianFilter[2][1] = 1.0 / 8.0;
    gaussianFilter[2][2] = 1.0 / 16.0;
  }

  @Override
  public void execute(ImageProcessingApplicationModel model) {
    // make an image copy
    Image imageCopy = model.getImageCopy(originalName);

    // make a new image with brightened values from the copy
    int width = imageCopy.getWidth();
    int height = imageCopy.getHeight();

    Pixel[][] pixels = new Pixel[height][width];
    Pixel[][] outputPixels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        pixels[row][col] = imageCopy.getPixel(row, col);
      }
    }

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Pixel current = applyGaussianFilter(row, col, pixels);
        outputPixels[row][col] = current;
      }
    }

    Image returnImage = imageCopy.makeImage(outputPixels, width, height);

    // add that copy with the new name
    model.addImage(returnImage, newName);
  }

  private Pixel applyGaussianFilter(int row, int col, Pixel[][] pixels) {

    double redSum = 0;
    double greenSum = 0;
    double blueSum = 0;

    for (int r = row - 1; r <= row + 1; r++) {
      for (int c = col - 1; c <= col + 1; c++) {
        if (validPosition(r, c, pixels.length, pixels[0].length)) {
          Pixel pixel = pixels[r][c];
          double multiple = gaussianFilter[row - r + 1][col - c + 1];
          redSum += pixel.getRed() * multiple;
          greenSum += pixel.getGreen() * multiple;
          blueSum += pixel.getBlue() * multiple;
        }
      }
    }

    int r = (int)(redSum + 0.5);
    int g = (int)(greenSum + 0.5);
    int b = (int)(blueSum + 0.5);

    return new RGBAPixel(255, r, g, b, 255);
  }

  // Determines if the given position is on the board
  private boolean validPosition(int row, int col, int height, int width) {
    return row >= 0 && row < height && col >= 0 && col < width;
  }
}
