package imageprocessing.model.imagecommand;

import image.Image;

/**
 * Represents a Red Component command object. This class is an image command.
 */
public class RedComponent extends AbstractChannelComponent {


  /**
   * Construct a Red Component command object.
   *
   * @param originalName the name of the original image.
   * @param newName      the name of the modified image.
   */
  public RedComponent(String originalName, String newName) {
    super(originalName, newName);
  }

  @Override
  int getChannelValue(Image image, int row, int col) {
    return Math.min(image.getPixel(row, col).getRed(), 255);
  }
}
