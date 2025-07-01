package imageprocessing.model.imagecommand;

import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Command object for darkening an image. This class is an image command.
 */
public class Darken implements ImageCommand {

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
  public Darken(String originalName, String newName, int value) {
    this.originalName = originalName;
    this.newName = newName;
    this.value = value;
  }

  @Override
  public void execute(ImageProcessingApplicationModel model) {
    ImageCommand cmd = new Brighten(originalName, newName, -1 * value);
    cmd.execute(model);
  }
}
