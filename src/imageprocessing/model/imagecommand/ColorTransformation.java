package imageprocessing.model.imagecommand;

import image.Image;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Represents a color transformation command object.
 */
public class ColorTransformation implements ImageCommand {

  private final String originalName;
  private final String newName;
  private final double[][] kernel;

  /**
   * Constructs a color transformation function object.
   * @param originalName name of the original image
   * @param newName name of the new image
   * @param kernel kernel of the transformation
   */
  public ColorTransformation(String originalName, String newName, double[][] kernel) {
    this.originalName = originalName;
    this.newName = newName;
    this.kernel = kernel;
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
        Pixel pixel = imageCopy.getPixel(row, col);
        double red = pixel.getRed() * kernel[0][0] +
                pixel.getGreen() * kernel[0][1] +
                pixel.getBlue() * kernel[0][2];
        double green = pixel.getRed() * kernel[1][0] +
                pixel.getGreen() * kernel[1][1] +
                pixel.getBlue() * kernel[1][2];
        double blue = pixel.getRed() * kernel[2][0] +
                pixel.getGreen() * kernel[2][1] +
                pixel.getBlue() * kernel[2][2];

        int r = (int) (red + 0.5);
        int g = (int) (green + 0.5);
        int b = (int) (blue + 0.5);
        pixels[row][col] = new RGBAPixel(255, r, g, b, 255);
      }
    }

    Image returnImage = imageCopy.makeImage(pixels, width, height);

    // add that copy with the new name
    model.addImage(returnImage, newName);
  }
}
