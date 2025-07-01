package imageprocessing.model.imagecommand;

import image.Image;
import image.Pixel;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Represents a Flip Horizontal command object. This class is an image command.
 */
public class FlipHorizontal implements ImageCommand {
  private final String originalName;
  private final String newName;

  /**
   * Constructs a Flip Horizontal command object.
   *
   * @param originalName The name of the original image
   * @param newName      The name of the modified image
   */
  public FlipHorizontal(String originalName, String newName) {
    this.originalName = originalName;
    this.newName = newName;
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
        pixels[row][width - col - 1] = imageCopy.getPixel(row, col);
      }
    }

    Image returnImage = imageCopy.makeImage(pixels, width, height);

    // add that copy with the new name
    model.addImage(returnImage, newName);
  }
}
