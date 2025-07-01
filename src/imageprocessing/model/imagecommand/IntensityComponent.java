package imageprocessing.model.imagecommand;

import image.Image;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Command object for getting the Intensity Component of an image. This class is an image command.
 */
public class IntensityComponent implements ImageCommand {

  private final String originalName;
  private final String newName;

  /**
   * Constructs an Intensity Component command object.
   *
   * @param originalName name of the original image
   * @param newName      name of the modified image
   */
  public IntensityComponent(String originalName, String newName) {
    this.originalName = originalName;
    this.newName = newName;
  }

  @Override
  public void execute(ImageProcessingApplicationModel model) {
    // make an image copy
    Image imageCopy = model.getImageCopy(originalName);

    // make a new image with brightened values from the copy
    int width = imageCopy.getWidth();
    int height = imageCopy.getHeight();

    Pixel[][] pixels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int intensity = (int) (imageCopy.getIntensity(row, col) + 0.5);
        pixels[row][col] = new RGBAPixel(255, intensity, intensity, intensity, 255);
      }
    }

    Image returnImage = imageCopy.makeImage(pixels, width, height);

    // add that copy with the new name
    model.addImage(returnImage, newName);
  }
}
