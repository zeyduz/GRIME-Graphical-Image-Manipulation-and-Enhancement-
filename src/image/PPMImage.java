package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Objects;


/**
 * Represents an image of the PPM file type.
 */
public class PPMImage extends AbstractImage {

  // INVARIANT: NEVER NULL
  private final Pixel[][] pixels;

  // INVARIANT: HEIGHT IS ALWAYS THE SAME AS THE # OF ROWS IN PIXELS
  private final int width;

  // INVARIANT: WIDTH IS ALWAYS THE SAME AS THE # OF COLUMNS IN PIXELS
  private final int height;


  /**
   * Construct a ppm image from a given filepath.
   *
   * @param filename The filepath of the ppm file.
   * @throws IllegalArgumentException If the file is not of the ppm type.
   */
  public PPMImage(String filename) throws IllegalArgumentException {
    this.pixels = ImageUtil.getPPMPixels(filename);
    this.width = ImageUtil.getPPMWidth(filename);
    this.height = ImageUtil.getPPMHeight(filename);
  }

  /**
   * Construct a ppm image from the required components of an image.
   *
   * @param pixels A matrix representation of the pixels of the image.
   * @param width  The width of the image.
   * @param height The height of the image.
   * @throws IllegalArgumentException if the pixels are null.
   */
  public PPMImage(Pixel[][] pixels, int width, int height)
          throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels cannot be null");
    }

    if (width != pixels[0].length || height != pixels.length) {
      throw new IllegalArgumentException("invalid width and height dimensions");
    }

    this.pixels = pixels;
    this.width = width;
    this.height = height;
  }

  /**
   * Construct a ppm image from the required components of an image.
   *
   * @param pixels A matrix representation of the pixels of the image.
   * @throws IllegalArgumentException if the pixels are null.
   */
  public PPMImage(Pixel[][] pixels) {
    this(pixels, pixels[0].length, pixels.length);
  }

  @Override
  public Image makeImage(Pixel[][] pixels, int width, int height) throws IllegalArgumentException {
    return new PPMImage(pixels, width, height);
  }

  @Override
  public Image makeImage(String filepath) {
    return new PPMImage(filepath);
  }

  @Override
  public Image makeCopy() {
    Pixel[][] copy = new Pixel[height][width];

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        Pixel pixel = pixels[r][c];
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
  public Pixel getPixel(int row, int col) throws IllegalArgumentException {
    try {
      Pixel pixel = pixels[row][col];
      int max = pixel.getMaxValue();
      int red = pixel.getRed();
      int green = pixel.getGreen();
      int blue = pixel.getBlue();
      return new RGBAPixel(255, red, green, blue, 255);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid row and column index");
    }
  }

  @Override
  public BufferedImage asBufferedImage() {
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
    return newImage;
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
  public int getValue(int row, int col) throws IllegalArgumentException {
    try {
      Pixel pixel = pixels[row][col];
      return Math.max(pixel.getRed(), Math.max(pixel.getGreen(), pixel.getBlue()));
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid row and column index");
    }
  }

  @Override
  public double getIntensity(int row, int col) throws IllegalArgumentException {
    try {
      Pixel pixel = pixels[row][col];
      return (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3.0;
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid row and column index");
    }
  }

  @Override
  public double getLuma(int row, int col) throws IllegalArgumentException {
    try {
      Pixel pixel = pixels[row][col];
      return pixel.getRed() * 0.2126
              + pixel.getBlue() * 0.7152
              + pixel.getGreen() * 0.0722;
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid row and column index");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Image)) {
      return false;
    }

    Image image = (Image) o;

    if (width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (!pixels[row][col].equals(image.getPixel(row, col))) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.deepHashCode(pixels), width, height);
  }
}
