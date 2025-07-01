import java.awt.event.ActionListener;

import imageprocessing.model.ImageProcessingApplicationModelState;
import imageprocessing.view.ImageProcessingApplicationViewExtension;

/**
 * Mock GUI View for testing.
 */
public class MockGUIView implements ImageProcessingApplicationViewExtension {

  @Override
  public void renderMessage(String message) {
    message += "sup";
  }

  @Override
  public void renderImageList() {
    return;
  }

  @Override
  public void makeVisible() {
    return;
  }

  @Override
  public void setButtonListeners(ActionListener actionEvent) {
    return;
  }

  @Override
  public void addImage(ImageProcessingApplicationModelState model, String imageName) {
    return;
  }

  @Override
  public void displayError(String error) {
    return;
  }

  @Override
  public void repaintView() {
    return;
  }

  @Override
  public String getStringInput() {
    return "0";
  }

  @Override
  public String fileChooserLoad() throws IllegalStateException {
    return "src/images/example2.ppm";
  }

  @Override
  public String fileChooserSave() throws IllegalStateException {
    return "example1-copy.ppm";
  }
}
