package imageprocessing.controller;

import java.io.IOException;
import java.util.Scanner;

import imageprocessing.model.imagecommand.BlueComponent;
import imageprocessing.model.imagecommand.Brighten;
import imageprocessing.model.imagecommand.ColorTransformation;
import imageprocessing.model.imagecommand.Darken;
import imageprocessing.model.imagecommand.FlipHorizontal;
import imageprocessing.model.imagecommand.FlipVertical;
import imageprocessing.model.imagecommand.GaussianBlur;
import imageprocessing.model.imagecommand.GreenComponent;
import imageprocessing.model.imagecommand.ImageCommand;
import imageprocessing.model.imagecommand.IntensityComponent;
import imageprocessing.model.imagecommand.LoadCommand;
import imageprocessing.model.imagecommand.LumaComponent;
import imageprocessing.model.imagecommand.RedComponent;
import imageprocessing.model.imagecommand.SaveCommand;
import imageprocessing.model.imagecommand.Sharpen;
import imageprocessing.model.imagecommand.ValueComponent;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.view.ImageProcessingApplicationView;

/**
 * Represents a controller class for Image Processing Application Text. This class implements the
 * interface for ImageProcessingApplicationController.
 */
public class ImageProcessingApplicationTextController implements
        ImageProcessingApplicationController {


  // ALL FIELDS ARE ALWAYS NON NULL
  private final ImageProcessingApplicationModel model;
  private final ImageProcessingApplicationView view;
  private final Readable in;

  /**
   * Constructor for ImageProcessingApplicationTextController.
   *
   * @param model the given model
   * @param view  the given view
   * @param in    the given readable object
   * @throws IllegalArgumentException if model, view, or in are null
   */
  public ImageProcessingApplicationTextController(ImageProcessingApplicationModel model,
                                                  ImageProcessingApplicationView view,
                                                  Readable in) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }

    if (in == null) {
      throw new IllegalArgumentException("Readable in cannot be null");
    }

    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public void start() throws IllegalStateException {
    // Scanner used for reading input from Readable
    Scanner scanner = new Scanner(this.in);

    // boolean to determine if the program was quit by the player
    boolean programQuit = false;

    // While the program hasn't been quit yet
    while (!programQuit) {

      // Render all the images that are loaded in the program
      renderImageListAux();
      renderMessageAux("\n");

      // As long as the scanner has more input...
      if (scanner.hasNext()) {
        // Read the input
        String input = scanner.next();
        // If the input is q or quit, quit the program
        if (input.equalsIgnoreCase("q")
                || input.equalsIgnoreCase("quit")) {
          programQuit = true;
          continue;
        }
        // Set up the command for the command design pattern
        ImageCommand cmd = null;
        // Set up the input for the command arguments
        String[] names;
        try {
          // Get the valid arguments
          names = getOldAndNewName(scanner);
        } catch (IllegalArgumentException e) {
          renderMessageAux("Invalid Input. Start again.\n");
          continue;
        }

        // Decide witch command to run and do it
        switch (input) {
          case "load":
            cmd = new LoadCommand(names[0], names[1]);
            break;
          case "save":
            cmd = new SaveCommand(names[0], names[1]);
            break;
          case "red-component":
            cmd = new RedComponent(names[0], names[1]);
            renderMessageAux("Red-Component image created.\n");
            break;
          case "green-component":
            cmd = new GreenComponent(names[0], names[1]);
            renderMessageAux("Green-Component image created.\n");
            break;
          case "blue-component":
            cmd = new BlueComponent(names[0], names[1]);
            renderMessageAux("Blue-Component image created.\n");
            break;
          case "intensity-component":
            cmd = new IntensityComponent(names[0], names[1]);
            renderMessageAux("Intensity-Component image created.\n");
            break;
          case "luma-component":
            cmd = new LumaComponent(names[0], names[1]);
            renderMessageAux("Luma-Component image created.\n");
            break;
          case "value-component":
            cmd = new ValueComponent(names[0], names[1]);
            renderMessageAux("Value-Component image created.\n");
            break;
          case "flip-horizontal":
            cmd = new FlipHorizontal(names[0], names[1]);
            renderMessageAux("Horizontally Flipped image created.\n");
            break;
          case "flip-vertical":
            cmd = new FlipVertical(names[0], names[1]);
            renderMessageAux("Vertically Flipped image created.\n");
            break;
          case "brighten":
            int val;
            if (scanner.hasNextInt()) {
              val = scanner.nextInt();
              cmd = new Brighten(names[0], names[1], val);
              renderMessageAux("Brighten image created.\n");
            } else if (scanner.hasNext()) {
              renderMessageAux("Brighten expects a integer value.\n");
              scanner.next();
            } else {
              throw new IllegalStateException("ran out of input");
            }
            break;
          case "darken":
            int darkenBy;
            if (scanner.hasNextInt()) {
              darkenBy = scanner.nextInt();
              cmd = new Darken(names[0], names[1], darkenBy);
              renderMessageAux("Darkened image created.\n");
            } else if (scanner.hasNext()) {
              renderMessageAux("Darken expects a integer value.\n");
              scanner.next();
            } else {
              throw new IllegalStateException("ran out of input");
            }
            break;
          case "blur":
            cmd = new GaussianBlur(names[0], names[1]);
            renderMessageAux("Gaussian Blurred image created.\n");
            break;
          case "sharpen":
            cmd = new Sharpen(names[0], names[1]);
            renderMessageAux("Sharpened image created.\n");
            break;
          case "greyscale":
            double[][] greyscaleTransformation
                    = new double[][]{new double[]{0.2126, 0.7152, 0.0722},
                      new double[]{0.2126, 0.7152, 0.0722},
                      new double[]{0.2126, 0.7152, 0.0722}};
            cmd = new ColorTransformation(names[0], names[1], greyscaleTransformation);
            renderMessageAux("Greyscale image created.\n");
            break;
          case "sepia":
            double[][] sepiaTransformation
                    = new double[][]{new double[]{0.393, 0.769, 0.189},
                      new double[]{0.349, 0.686, 0.168},
                      new double[]{0.272, 0.534, 0.131}};
            cmd = new ColorTransformation(names[0], names[1], sepiaTransformation);
            renderMessageAux("Sepia image created.\n");
            break;
          case "quit":
          case "q":
            programQuit = true;
            break;
          default:
            renderMessageAux("Invalid input. Re-enter.\n");
        }
        if (cmd != null) {
          try {
            cmd.execute(model);
          } catch (IllegalArgumentException e) {
            renderMessageAux("Invalid file path argument.\n");
          } catch (IllegalStateException e) {
            renderMessageAux("Error with file creation.\n");
          }

        }
      } else {
        throw new IllegalStateException("ran out of input");
      }

    }
  }

  /**
   * Helper method to find the old name of an image and the name that the edited image will be
   * referred to.
   *
   * @param scanner the given scanner
   * @return a list of names
   * @throws IllegalStateException if the scanner runs out inputs
   */
  private String[] getOldAndNewName(Scanner scanner)
          throws IllegalStateException, IllegalArgumentException {
    // Make the return array
    String[] out = new String[2];

    // If the scanner has a next, read that input
    if (scanner.hasNext()) {
      out[0] = scanner.next();
    } else {
      throw new IllegalStateException("ran out of input");
    }

    // Check if the old name is in the model's names
    String[] names = model.getImageNames();
    boolean nameInNames = false;
    for (String name : names) {
      if (name.equals(out[0])) {
        nameInNames = true;
        break;
      }
    }

    // If the "old name" is a file, let it be entered.
    if (out[0].lastIndexOf('.') != -1) {
      nameInNames = true;
    }

    // If it is not, throw an exception
    if (!nameInNames) {
      throw new IllegalArgumentException("old name is not in the IPA");
    }

    if (scanner.hasNext()) {
      out[1] = scanner.next();
    } else {
      throw new IllegalStateException("ran out of input");
    }

    return out;
  }

  // Helper for rendering messages.
  private void renderMessageAux(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("render message failed");
    }
  }

  // Helper for rendering the list of images.
  private void renderImageListAux() throws IllegalStateException {
    try {
      view.renderImageList();
    } catch (IOException e) {
      throw new IllegalStateException("render image list failed");
    }
  }

}
