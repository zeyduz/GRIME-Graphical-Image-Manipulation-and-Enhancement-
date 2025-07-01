package image;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Represents an image of the PNG file type.
 */
public class ImageAdaptTransparent extends ImageAdapt {

  /**
   * Make an Image that supports transparency.
   *
   * @param filepath the path of the given file.
   * @throws IllegalArgumentException if the filepath is invalid.
   */
  public ImageAdaptTransparent(String filepath) throws IllegalArgumentException {
    super(filepath);
  }

  // Create an image that supports transparency with the given image.
  protected ImageAdaptTransparent(BufferedImage image) {
    super(image);
  }


  @Override
  public Image makeImage(Pixel[][] pixels, int width, int height) throws IllegalArgumentException {
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Pixel currentPixel = pixels[row][col];
        int r = currentPixel.getRed();
        int g = currentPixel.getGreen();
        int b = currentPixel.getBlue();
        int a = currentPixel.getAlpha();
        int rgba = new Color(r, g, b, a).getRGB();
        newImage.setRGB(col, row, rgba);
      }
    }

    return new ImageAdaptTransparent(newImage);
  }

  @Override
  public Image makeCopy() {

    Pixel[][] copy = new Pixel[height][width];

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        Pixel pixel = getPixel(r, c);
        int red = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        int a = pixel.getAlpha();
        copy[r][c] = new RGBAPixel(255, red, g, b, a);
      }
    }

    return makeImage(copy, width, height);
  }

  @Override
  public BufferedImage asBufferedImage() {
    BufferedImage newBufferedImage = new BufferedImage(
            image.getWidth(),
            image.getHeight(),
            BufferedImage.TYPE_INT_ARGB);

    newBufferedImage.createGraphics()
            .drawImage(image,
                    0,
                    0,
                    Color.WHITE,
                    null);
    return newBufferedImage;
  }

}