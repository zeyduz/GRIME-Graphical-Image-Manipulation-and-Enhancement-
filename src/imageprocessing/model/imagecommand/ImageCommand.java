package imageprocessing.model.imagecommand;

import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Represents an Image Command to be executed on a model. Utilizes the command design pattern.
 */
public interface ImageCommand {

  /**
   * Execute the command associated with {@code this} class on the given model.
   *
   * @param model The model to execute the command on.
   */
  void execute(ImageProcessingApplicationModel model);

}