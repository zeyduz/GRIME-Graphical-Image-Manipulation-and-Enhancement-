package image;

import java.util.Objects;

/**
 * Represents a pixel with specific RGB and Alpha channels.
 */
public class RGBAPixel implements Pixel {

  // INVARIANTS: RED, GREEN, BLUE, AND ALPHA ARE ALL NON NEGATIVE
  int red;
  int green;
  int blue;
  int alpha;

  /**
   * Construct a pixel with the specified channels and components.
   *
   * @param max The maximum value any channel can be
   * @param r   The red channel
   * @param g   The green channel
   * @param b   The blue channel
   * @param a   The alpha channel
   */
  public RGBAPixel(int max, int r, int g, int b, int a) {

    if (max > 255) {
      max = 255;
    }

    if (r > max) {
      this.red = max;
    } else {
      this.red = Math.max(r, 0);
    }

    if (g > max) {
      this.green = max;
    } else {
      this.green = Math.max(g, 0);
    }

    if (b > max) {
      this.blue = max;
    } else {
      this.blue = Math.max(b, 0);
    }

    if (a > max) {
      this.alpha = max;
    } else {
      this.alpha = Math.max(a, 0);
    }
  }

  @Override
  public int getRed() {
    return red;
  }

  @Override
  public int getGreen() {
    return green;
  }

  @Override
  public int getBlue() {
    return blue;
  }

  @Override
  public int getAlpha() {
    return alpha;
  }

  @Override
  public int getMaxValue() {
    return Math.max(red, Math.max(green, blue));
  }

  @Override
  public int getIntensity() {
    return (int) Math.round((red + green + blue) / 3.0);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o instanceof RGBAPixel) {
      Pixel other = (RGBAPixel) o;
      return red == other.getRed() &&
              blue == other.getBlue() &&
              green == other.getGreen() &&
              alpha == other.getAlpha();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue, alpha);
  }
}
