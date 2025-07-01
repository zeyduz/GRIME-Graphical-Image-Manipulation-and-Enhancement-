package imageprocessing.view;

import java.awt.event.ActionListener;

import imageprocessing.model.ImageProcessingApplicationModelState;

/**
 * Extension of the view that allows for GUI Interaction.
 */
public interface ImageProcessingApplicationViewExtension extends ImageProcessingApplicationView {

  /**
   * Makes the view visible on the screen.
   */
  void makeVisible();

  /**
   * Set the button listeners to the given listener.
   *
   * @param actionEvent The given action listener.
   */
  void setButtonListeners(ActionListener actionEvent);

  /**
   * Display the image with the given name. Do nothing if that name does not
   * exist in the model.
   *
   * @param model The model holding the image.
   * @param imageName The name of the image.
   */
  void addImage(ImageProcessingApplicationModelState model, String imageName);

  /**
   * Displays the given error.
   * @param error The contents of the error message.
   */
  void displayError(String error);

  /**
   * Tell the view to refresh its contents.
   */
  void repaintView();

  /**
   * Get an input string from the view.
   *
   * @return the input given by the user.
   */
  String getStringInput();

  /**
   * Get a file path using a file chooser.
   *
   * @return The absolute file path.
   */
  String fileChooserLoad() throws IllegalStateException;

  /**
   * Get a file path using a file chooser.
   *
   * @return The absolute file path.
   */
  String fileChooserSave() throws IllegalStateException;
}
