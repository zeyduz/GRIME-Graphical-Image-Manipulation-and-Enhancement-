package imageprocessing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import image.PPMImage;
import image.Pixel;
import imageprocessing.model.ImageProcessingApplicationModel;
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
import imageprocessing.view.ImageProcessingApplicationViewExtension;

/**
 * A controller for an Image Processing Application with a GUI.
 */
public class ImageProcessingApplicationGUIController
        implements ImageProcessingApplicationController, ActionListener {

  private ImageProcessingApplicationModel model;
  private ImageProcessingApplicationViewExtension view;

  public ImageProcessingApplicationGUIController(ImageProcessingApplicationModel model,
                                                 ImageProcessingApplicationViewExtension view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void start() throws IllegalStateException {
    this.view.setButtonListeners(this);
    this.view.makeVisible();
    this.model.addImage(new PPMImage(new Pixel[][]{{}}), "downloadName");
  }

  private String processCommand(String input) {
    StringBuilder output = new StringBuilder();
    Scanner scanner = new Scanner(input);
    ImageCommand cmd = null;

    // As long as the scanner has more input...
    while (scanner.hasNext()) {
      // Decide witch command to run and do it
      String command = scanner.next();
      String[] names = new String[]{scanner.next(), scanner.next()};
      switch (command) {
        case "load":
          cmd = new LoadCommand(names[0], names[1]);
          break;
        case "save":
          cmd = new SaveCommand(names[0], names[1]);
          break;
        case "red-component":
          cmd = new RedComponent(names[0], names[1]);
          break;
        case "green-component":
          cmd = new GreenComponent(names[0], names[1]);
          break;
        case "blue-component":
          cmd = new BlueComponent(names[0], names[1]);
          break;
        case "intensity-component":
          cmd = new IntensityComponent(names[0], names[1]);
          break;
        case "luma-component":
          cmd = new LumaComponent(names[0], names[1]);
          break;
        case "value-component":
          cmd = new ValueComponent(names[0], names[1]);
          break;
        case "flip-horizontal":
          cmd = new FlipHorizontal(names[0], names[1]);
          break;
        case "flip-vertical":
          cmd = new FlipVertical(names[0], names[1]);
          break;
        case "brighten":
          int val = scanner.nextInt();
          cmd = new Brighten(names[0], names[1], val);
          break;
        case "darken":
          int darkenBy = scanner.nextInt();
          cmd = new Darken(names[0], names[1], darkenBy);
          break;
        case "blur":
          cmd = new GaussianBlur(names[0], names[1]);
          break;
        case "sharpen":
          cmd = new Sharpen(names[0], names[1]);
          break;
        case "greyscale":
          double[][] greyscaleTransformation
                  = new double[][]{new double[]{0.2126, 0.7152, 0.0722},
                    new double[]{0.2126, 0.7152, 0.0722},
                    new double[]{0.2126, 0.7152, 0.0722}};
          cmd = new ColorTransformation(names[0], names[1], greyscaleTransformation);
          break;
        case "sepia":
          double[][] sepiaTransformation
                  = new double[][]{new double[]{0.393, 0.769, 0.189},
                    new double[]{0.349, 0.686, 0.168},
                    new double[]{0.272, 0.534, 0.131}};
          cmd = new ColorTransformation(names[0], names[1], sepiaTransformation);
          break;
        default:
          output.append(String.format("Unknown command %s: ", input));
          break;
      }
      if (cmd != null) {
        cmd.execute(model);
        output.append(input);
      }
    }

    return output.toString();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand().toLowerCase(); // only the command name

    command = command + " " + model.getNewestImageName() + " newName";

    switch (command.split(" ")[0]) {
      case "brighten":
      case "darken":
        String result = view.getStringInput();
        command += " " + result;
        break;
      case "load":
        String newPath = view.fileChooserLoad();
        String[] commands = command.split(" ");
        command = commands[0] + " " + newPath + " " + commands[2];
        break;
      case "save":
        String newPathSave = view.fileChooserSave();
        String[] commandsSave = command.split(" ");
        command = commandsSave[0] + " " + newPathSave + " " + commandsSave[2];
        break;
      default:
        break;
    }

    try {
      processCommand(command);
      this.view.addImage(model, "newName");
    } catch (Exception ex) {
      view.displayError(ex.getMessage());
    }

    view.repaintView();
  }
}
