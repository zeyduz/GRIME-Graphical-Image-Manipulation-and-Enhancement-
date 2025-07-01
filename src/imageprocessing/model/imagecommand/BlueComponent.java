package imageprocessing.model.imagecommand;

import image.Image;

/**
 * Command object for getting the blue component of an image. This class is an image command.
 */
public class BlueComponent extends AbstractChannelComponent {

  /**
   * Constructor for a Blue Component command object.
   *
   * @param originalName The original name of the image.
   * @param newName      The new name for the modified image.
   */
  public BlueComponent(String originalName, String newName) {
    super(originalName, newName);
  }

  @Override
  int getChannelValue(Image image, int row, int col) {
    return Math.min(image.getPixel(row, col).getBlue(), 255);
  }
}
