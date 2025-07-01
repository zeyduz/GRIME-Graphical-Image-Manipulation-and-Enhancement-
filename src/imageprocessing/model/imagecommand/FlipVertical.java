package imageprocessing.model.imagecommand;

import image.Image;
import image.Pixel;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Represents a Flip Vertical command object. This class is an image command.
 */
public class FlipVertical implements ImageCommand {

  private final String originalName;
  private final String newName;

  /**
   * Constructs a FLip Vertical command object.
   *
   * @param originalName name of the original image
   * @param newName      name of the modified image
   */
  public FlipVertical(String originalName, String newName) {
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
        pixels[height - row - 1][col] = imageCopy.getPixel(row, col);
      }
    }

    Image returnImage = imageCopy.makeImage(pixels, width, height);

    // add that copy with the new name
    model.addImage(returnImage, newName);
  }
}
