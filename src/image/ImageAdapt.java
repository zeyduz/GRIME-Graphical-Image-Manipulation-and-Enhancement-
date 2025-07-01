package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import javax.imageio.ImageIO;

/**
 * Represents an Image of popular formats.
 */
public class ImageAdapt extends AbstractImage {

  protected final BufferedImage image;
  protected final int width;
  protected final int height;

  /**
   * Constructs an Image.
   * @param filepath The path of the file.
   * @throws IllegalArgumentException if the filepath is bad.
   */
  public ImageAdapt(String filepath) throws IllegalArgumentException {
    Path source = Paths.get(filepath);
    try {
      image = ImageIO.read(source.toFile());
      width = image.getWidth();
      height = image.getHeight();
    } catch (IOException e) {
      throw new IllegalArgumentException("bad file path");
    }
  }

  // Makes an image given a buffered image.
  protected ImageAdapt(BufferedImage image) {
    this.image = image;
    this.width = image.getWidth();
    this.height = image.getHeight();
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
        int rgb = new Color(r, g, b).getRGB();
        newImage.setRGB(col, row, rgb);
      }
    }

    return new ImageAdaptTransparent(newImage);
  }

  @Override
  public Image makeImage(String imagePath) {
    return new ImageAdapt(imagePath);
  }

  @Override
  public Image makeCopy() {
    BufferedImage newBufferedImage = asBufferedImage();
    return new ImageAdapt(newBufferedImage);
  }

  @Override
  public Pixel getPixel(int row, int col) throws IllegalArgumentException {
    int bitColor = image.getRGB(col, row); // col is x, row is y
    int blue = bitColor & 0xff;
    int green = (bitColor & 0xff00) >> 8;
    int red = (bitColor & 0xff0000) >> 16;
    int alpha = (bitColor & 0xff000000) >>> 24;
    return new RGBAPixel(255, red, green, blue, alpha);
  }

  @Override
  public BufferedImage asBufferedImage() {
    BufferedImage newBufferedImage = new BufferedImage(
            image.getWidth(),
            image.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    newBufferedImage.createGraphics()
            .drawImage(image,
                    0,
                    0,
                    Color.WHITE,
                    null);
    return newBufferedImage;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getValue(int row, int col) {
    Pixel pixel = getPixel(row, col);
    return Math.max(pixel.getRed(), Math.max(pixel.getGreen(), pixel.getBlue()));
  }

  @Override
  public double getIntensity(int row, int col) {
    Pixel pixel = getPixel(row, col);
    return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3.0;
  }

  @Override
  public double getLuma(int row, int col) {
    Pixel pixel = getPixel(row, col);
    return pixel.getRed() * 0.2126
            + pixel.getBlue() * 0.7152
            + pixel.getGreen() * 0.0722;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Image)) {
      return false;
    }

    Image other = (Image) o;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (!getPixel(row, col).equals(other.getPixel(row, col))) {
          return false;
        }
      }
    }

    return this.width == other.getWidth() &&
            this.height == other.getHeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }
}