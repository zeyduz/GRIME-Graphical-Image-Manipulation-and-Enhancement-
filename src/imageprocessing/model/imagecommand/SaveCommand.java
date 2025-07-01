package imageprocessing.model.imagecommand;

import image.Image;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Represents a Save Component command object. This class is an image command.
 */
public class SaveCommand implements ImageCommand {

  private final String imagePath;
  private final String name;

  /**
   * Constructor for SaveCommand.
   *
   * @param imagePath path of the image
   * @param name      name of the image
   */
  public SaveCommand(String imagePath, String name) {
    this.imagePath = imagePath;
    this.name = name;
  }

  /**
   * Execute the command associated with {@code this} class on the given model.
   *
   * @param model The model to execute the command on.
   * @throws IllegalArgumentException if no image is associated with the given name or
   *                                  if the file path is invalid.
   */
  @Override
  public void execute(ImageProcessingApplicationModel model)
          throws IllegalArgumentException {
    Image image = model.getImageCopy(name);
    image.makeFile(imagePath);
  }

}
