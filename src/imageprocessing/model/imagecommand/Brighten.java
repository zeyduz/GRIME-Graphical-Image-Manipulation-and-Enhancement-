package imageprocessing.model.imagecommand;

import image.Image;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Command object for brightening an image. This class is an image command.
 */
public class Brighten implements ImageCommand {

  private final String originalName;
  private final String newName;
  private final int value;

  /**
   * Constructor for a Brighten command object.
   *
   * @param originalName the name of the original image
   * @param newName      the name of the modified image
   * @param value        the amount to brighten by
   */
  public Brighten(String originalName, String newName, int value) {
    this.originalName = originalName;
    this.newName = newName;
    this.value = value;
  }

  @Override
  public void execute(ImageProcessingApplicationModel model) {
    // make an image copy
    Image imageCopy = model.getImageCopy(originalName);

    // make a new image with brightened values from the copy
    int width = imageCopy.getWidth();
    int height = imageCopy.getHeight();

    Pixel[][] pixels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = Math.min(imageCopy.getPixel(row, col).getRed() + value, 255);
        int g = Math.min(imageCopy.getPixel(row, col).getGreen() + value, 255);
        int b = Math.min(imageCopy.getPixel(row, col).getBlue() + value, 255);
        pixels[row][col] = new RGBAPixel(255, r, g, b, 255);
      }
    }

    Image returnImage = imageCopy.makeImage(pixels, width, height);

    // add that copy with the new name
    model.addImage(returnImage, newName);
  }

}
