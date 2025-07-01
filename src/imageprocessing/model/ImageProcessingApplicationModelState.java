package imageprocessing.model;

import image.Image;

/**
 * Represents a model state interface for an Image Processing Application. This interface includes
 * two methods that aids in retrieving important states of the model.
 */
public interface ImageProcessingApplicationModelState {

  /**
   * Return a copy of the {@code Image} that has the specified name.
   *
   * @param name The name of the image.
   * @return a deep copy of the image associated with the name
   * @throws IllegalArgumentException if the name doesn't belong to an image
   */
  Image getImageCopy(String name) throws IllegalArgumentException;

  /**
   * Return a list of all the names saved in {@code this} Image
   * Processing Application model in alphabetical order.
   *
   * @return A list of strings containing the image names.
   */
  String[] getImageNames();

  /**
   * Return the name of the latest image added to the model.
   *
   * @return A String that is the last name.
   */
  String getNewestImageName();

}
