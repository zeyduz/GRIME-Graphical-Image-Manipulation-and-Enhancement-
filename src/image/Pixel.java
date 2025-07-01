package image;

/**
 * Represents a pixel on a screen. Has a red, green, blue, and alpha channel.
 * All the channels must have a max value.
 */
public interface Pixel {

  /**
   * Find the red component of {@code this} pixel.
   * @return The integer value of this pixel's red channel.
   */
  int getRed();

  /**
   * Find the green component of {@code this} pixel.
   * @return The integer value of this pixel's green channel.
   */
  int getGreen();

  /**
   * Find the blue component of {@code this} pixel.
   * @return The integer value of this pixel's blue channel.
   */
  int getBlue();

  /**
   * Find the alpha component of {@code this} pixel.
   * @return The integer value of this pixel's alpha channel.
   */
  int getAlpha();

  /**
   * Find the maximum value of {@code this} pixel.
   * @return The integer value of this pixel's max value.
   */
  int getMaxValue();


  /**
   * Get the intensity of this pixel.
   * @return The integer value of this pixel's intensity.
   */
  int getIntensity();
}
