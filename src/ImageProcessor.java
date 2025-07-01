import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import image.PPMImage;
import image.Pixel;
import imageprocessing.controller.ImageProcessingApplicationController;
import imageprocessing.controller.ImageProcessingApplicationGUIController;
import imageprocessing.controller.ImageProcessingApplicationTextController;
import imageprocessing.model.ImageProcessingApplicationModelImpl;
import imageprocessing.view.ImageProcessingApplicationGUIView;
import imageprocessing.view.ImageProcessingApplicationTextView;

/**
 * Main class for the image processor. Handles using the program.
 */
public class ImageProcessor {

  /**
   * Main method for using the image processor. Creates all components of MVC and utilizes
   * them to run the program.
   *
   * @param args The arguments to the program.
   */
  public static void main(String[] args) {

    ImageProcessingApplicationModelImpl model = new ImageProcessingApplicationModelImpl();
    model.addImage(new PPMImage(new Pixel[][]{{}}), "startupImage");

    if (args.length != 0 && args[0].equals("-file")) {
      File file = new File(args[1]);
      try {
        Readable fileReader = new FileReader(file);

        ImageProcessingApplicationController controller =
                new ImageProcessingApplicationTextController(
                        model,
                        new ImageProcessingApplicationTextView(model),
                        fileReader);
        controller.start();
      } catch (IOException e) {
        throw new IllegalArgumentException("File error");
      }
    } else if (args.length != 0 && args[0].equals("-text")) {
      ImageProcessingApplicationController controller =
              new ImageProcessingApplicationTextController(
                      model,
                      new ImageProcessingApplicationTextView(model),
                      new InputStreamReader(System.in));
      controller.start();
    } else {
      ImageProcessingApplicationGUIController controller =
              new ImageProcessingApplicationGUIController(
                      model,
                      new ImageProcessingApplicationGUIView(model));
      controller.start();
    }
  }
}