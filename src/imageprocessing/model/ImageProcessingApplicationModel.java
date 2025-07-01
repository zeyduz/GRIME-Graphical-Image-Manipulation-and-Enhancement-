package imageprocessing.model;

import image.Image;

/**
 * Represents a model interface for an Image Processing Application. This interface extends
 * another ImageProcessingApplicationModelState interface.
 */
public interface ImageProcessingApplicationModel extends ImageProcessingApplicationModelState {

  /**
   * Add an {@code Image} with the given name to identify it.
   *
   * @param image the {@code Image} to add.
   * @param name  the name to identify the {@code Image}
   * @throws IllegalArgumentException if the image or name is null
   */
  void addImage(Image image, String name) throws IllegalArgumentException;

}
