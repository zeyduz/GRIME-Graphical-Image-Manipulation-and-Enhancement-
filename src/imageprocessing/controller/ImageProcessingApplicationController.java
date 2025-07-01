package imageprocessing.controller;

/**
 * Represents an Image Processing Application Controller. Works to handle input
 * from the user and delegate tasks to the model and view.
 */
public interface ImageProcessingApplicationController {

  /**
   * Start the image processing application to allow user interaction.
   *
   * @throws IllegalStateException if the controller is unable to successfully
   *                               read input or transmit output
   */
  void start() throws IllegalStateException;
}
