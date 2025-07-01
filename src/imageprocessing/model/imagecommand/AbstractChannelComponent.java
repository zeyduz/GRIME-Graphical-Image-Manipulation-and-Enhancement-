package imageprocessing.model.imagecommand;

import image.Image;
import image.Pixel;
import image.RGBAPixel;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Abstract channel component command class.
 */
abstract class AbstractChannelComponent implements ImageCommand {

  protected final String originalName;
  protected final String newName;

  protected AbstractChannelComponent(String originalName, String newName) {
    this.originalName = originalName;
    this.newName = newName;
  }


  abstract int getChannelValue(Image image, int row, int col);

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
        int v = getChannelValue(imageCopy, row, col);
        pixels[row][col] = new RGBAPixel(255, v, v, v, 255);
      }
    }

    Image returnImage = imageCopy.makeImage(pixels, width, height);

    // add that copy with the new name
    model.addImage(returnImage, newName);
  }
}
