package image;

import java.awt.image.BufferedImage;

/**
 * Represents an Image. An Image is an assortment of pixels
 */
public interface Image {

  /**
   * Factory method to make an image with the given arguments.
   *
   * @param pixels Pixels of the image
   * @param width  Width of the image
   * @param height Height of the image
   * @return An image with the given properties
   * @throws IllegalArgumentException if the pixels are null
   */
  Image makeImage(Pixel[][] pixels, int width, int height) throws IllegalArgumentException;

  /**
   * Factory method to make an image with the given arguments.
   *
   * @param imagePath The image path of the image.x
   * @return The {@code Image} representation of the image at the given path
   */
  Image makeImage(String imagePath);

  /**
   * Save the image to the specified file path.
   *
   * @param imagePath The location to save the new file.
   * @throws IllegalArgumentException If the file already exists
   * @throws IllegalStateException    If there is an error with file creation
   */
  void makeFile(String imagePath) throws IllegalArgumentException, IllegalStateException;

  /**
   * Make a copy of {@code this} image.
   *
   * @return a deep copy of {@code this} image.
   */
  Image makeCopy();

  /**
   * Find the pixel at the specified row and column index.
   *
   * @param row The row index of the pixel.
   * @param col The column index of the pixel.
   * @return The color representation of the pixel.
   * @throws IllegalArgumentException if the position is not on the image.
   */
  Pixel getPixel(int row, int col) throws IllegalArgumentException;

  /**
   * Get {@code this} image as a buffered image.
   *
   * @return return a {@code BufferedImage} equivalent to {@code this} image
   */
  BufferedImage asBufferedImage();

  /**
   * Find the width of {@code this} image.
   *
   * @return the amount of pixels that span {@code this} image's width
   */
  int getWidth();

  /**
   * Find the height of {@code this} image.
   *
   * @return the amount of pixels that span {@code this} image's height
   */
  int getHeight();

  /**
   * Find the maximum value of the specified pixel.
   * * @param row The row index of the pixel.
   * * @param col The column index of the pixel.
   *
   * @return the max value of the specified pixel
   * @throws IllegalArgumentException if the pixel is not in the bounds of the image.
   */
  int getValue(int row, int col) throws IllegalArgumentException;

  /**
   * Find the intensity of the pixel at the given position.
   *
   * @param row The row index of the pixel.
   * @param col The column index of the pixel.
   * @return The intensity of the pixel at the specified position.
   * @throws IllegalArgumentException if the pixel is not in the bounds of the image.
   */
  double getIntensity(int row, int col) throws IllegalArgumentException;

  /**
   * Find the luma of the pixel at the given position.
   *
   * @param row The row index of the pixel.
   * @param col The column index of the pixel.
   * @return The luma of the pixel at the specified position.
   * @throws IllegalArgumentException if the pixel is not in the bounds of the image.
   */
  double getLuma(int row, int col) throws IllegalArgumentException;

}
