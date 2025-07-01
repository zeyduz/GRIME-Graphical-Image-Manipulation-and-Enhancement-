package imageprocessing.view;

import java.io.IOException;

/**
 * Represents the view interface for an Image Processions Application. This interface includes
 * two methods to render message and image list.
 */
public interface ImageProcessingApplicationView {

  /**
   * Render the given message to the screen.
   *
   * @param message The message to render.
   * @throws IOException if transmission of the message to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;

  /**
   * Render all the image names to the screen.
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderImageList() throws IOException;

}
