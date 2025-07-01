package imageprocessing.model.imagecommand;

import image.Image;

/**
 * Command object for getting the blue component of an image. This class is an image command.
 */
public class GreenComponent extends AbstractChannelComponent {

  /**
   * Constructs a Green Component command object.
   *
   * @param originalName the name of the original image
   * @param newName      the name of the modified image
   */
  public GreenComponent(String originalName, String newName) {
    super(originalName, newName);
  }

  @Override
  int getChannelValue(Image image, int row, int col) {
    return Math.min(image.getPixel(row, col).getGreen(), 255);
  }
}
