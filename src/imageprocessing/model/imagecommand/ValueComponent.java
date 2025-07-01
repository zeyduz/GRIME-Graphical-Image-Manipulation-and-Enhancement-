package imageprocessing.model.imagecommand;

import image.Image;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Represents a Value Component command object. This class is an image command.
 */
public class ValueComponent implements ImageCommand {

  private final String originalName;
  private final String newName;

  /**
   * Constructs a Value component command object.
   *
   * @param originalName the name of the original image
   * @param newName      the name of the modified image
   */
  public ValueComponent(String originalName, String newName) {
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
        int val = imageCopy.getValue(row, col);
        pixels[row][col] = new RGBAPixel(255, val, val, val, 255);
      }
    }

    Image returnImage = imageCopy.makeImage(pixels, width, height);

    // add that copy with the new name
    model.addImage(returnImage, newName);
  }
}
