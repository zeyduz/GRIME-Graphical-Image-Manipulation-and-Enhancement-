package imageprocessing.view;

import java.io.IOException;

import imageprocessing.model.ImageProcessingApplicationModelState;

/**
 * Class that represents an Image Processing Application Text View. This class aids in visualizing.
 */
public class ImageProcessingApplicationTextView implements ImageProcessingApplicationView {

  // INVARIANTS: OUT AND MODEL ARE NEVER NULL
  Appendable out;
  ImageProcessingApplicationModelState model;

  /**
   * Constructor for Image Processing Text View that takes in a model.
   *
   * @param model the given model
   * @throws IllegalArgumentException if the given model is null
   */
  public ImageProcessingApplicationTextView(ImageProcessingApplicationModelState model)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.out = System.out;
    this.model = model;
  }

  /**
   * Constructor for Image Processing Text View that takes in an appendable and a model.
   *
   * @param out   the appendable object
   * @param model the given model
   * @throws IllegalArgumentException if the given model or appendable object is null
   */
  public ImageProcessingApplicationTextView(Appendable out,
                                            ImageProcessingApplicationModelState model)
          throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("Appendable cannot be null");
    }

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.out = out;
    this.model = model;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }

  @Override
  public void renderImageList() throws IOException {
    // Get all the image names
    String[] names = model.getImageNames();

    // If there are no images loaded yet
    if (names.length == 0) {
      out.append("No Images Loaded Yet.");
      return;
    }

    // Otherwise enumerate the names
    out.append("Image Names:\n");
    for (int i = 0; i < names.length; i++) {
      if (i != names.length - 1) {
        out.append(names[i]).append("\n");
      } else {
        out.append(names[i]);
      }
    }
  }
}
