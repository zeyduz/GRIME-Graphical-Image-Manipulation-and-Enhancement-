package imageprocessing.model.imagecommand;

import image.Image;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Represents a sharpen command.
 */
public class Sharpen implements ImageCommand {

  private final String originalName;
  private final String newName;

  private final double[][] sharpenFilter = new double[5][5];

  /**
   * Constructs a Flip Horizontal command object.
   *
   * @param originalName The name of the original image
   * @param newName      The name of the modified image
   */
  public Sharpen(String originalName, String newName) {
    this.originalName = originalName;
    this.newName = newName;

    // Instantiate edges of filter
    for (int i = 0; i < 5; i++) {
      sharpenFilter[0][i] = - 1.0 / 8.0;
      sharpenFilter[4][i] = - 1.0 / 8.0;
      sharpenFilter[i][0] = - 1.0 / 8.0;
      sharpenFilter[i][4] = - 1.0 / 8.0;
    }

    sharpenFilter[1][1] = 1.0 / 4.0;
    sharpenFilter[1][2] = 1.0 / 4.0;
    sharpenFilter[1][3] = 1.0 / 4.0;
    sharpenFilter[2][1] = 1.0 / 4.0;
    sharpenFilter[2][2] = 1.0;
    sharpenFilter[2][3] = 1.0 / 4.0;
    sharpenFilter[3][1] = 1.0 / 4.0;
    sharpenFilter[3][2] = 1.0 / 4.0;
    sharpenFilter[3][3] = 1.0 / 4.0;
  }

  @Override
  public void execute(ImageProcessingApplicationModel model) {
    // make an image copy
    Image imageCopy = model.getImageCopy(originalName);

    // make a new image with brightened values from the copy
    int width = imageCopy.getWidth();
    int height = imageCopy.getHeight();

    Pixel[][] oldPixels = new Pixel[height][width];
    Pixel[][] newPixels = new Pixel[height][width];

    // Arrange the old pixels into a matrix
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        oldPixels[row][col] = imageCopy.getPixel(row, col);
      }
    }

    // Instantiate the new center pixels
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        newPixels[row][col] = applySharpen(row, col, oldPixels);
      }
    }

    Image returnImage = imageCopy.makeImage(newPixels, width, height);

    // add that copy with the new name
    model.addImage(returnImage, newName);
  }

  // Applies sharpen to the given pixel
  private Pixel applySharpen(int row, int col, Pixel[][] pixels) {

    double redSum = 0;
    double greenSum = 0;
    double blueSum = 0;

    for (int r = row - 2; r <= row + 2; r++) {
      for (int c = col - 2; c <= col + 2; c++) {
        if (validPosition(r, c, pixels.length, pixels[0].length)) {
          Pixel pixel = pixels[r][c];
          double multiple = sharpenFilter[row - r + 2][col - c + 2];
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
