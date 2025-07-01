package imageprocessing.model.imagecommand;

import image.Image;
import image.ImageFactory;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Command object for loading an image. This class is an image command.
 */
public class LoadCommand implements ImageCommand {

  private final String imagePath;
  private final String name;

  /**
   * Constructor for LoadCommand.
   *
   * @param imagePath path of the image
   * @param name      name of the image
   */
  public LoadCommand(String imagePath, String name) {
    this.imagePath = imagePath;
    this.name = name;
  }

  /**
   * aExecute the command associated with {@code this} class on the given model.
   *
   * @param model The model to execute the command on.
   * @throws IllegalArgumentException if the image cannot be processed from the path.
   */
  @Override
  public void execute(ImageProcessingApplicationModel model) {
    Image image = ImageFactory.factoryBuildImage(imagePath);
    model.addImage(image, name);
  }
}
