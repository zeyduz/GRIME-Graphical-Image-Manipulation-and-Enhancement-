package image;

/**
 * Represents a Factory that creates Images. These are based on different image parameters.
 */
public class ImageFactory {

  /**
   * Create an image of the correct type from the file at the given filepath.
   *
   * @param filepath The filepath to find an image at.
   * @return An image of the correct type
   * @throws IllegalArgumentException if the image type is not supported or bad filepath
   */
  public static Image factoryBuildImage(String filepath) throws IllegalArgumentException {

    String fileType = ImageUtil.getImageType(filepath);

    if (fileType.equals("ppm")) {
      return new PPMImage(filepath);
    }

    switch (fileType) {
      case "jpg":
      case "jpeg":
      case "bmp":
        return new ImageAdapt(filepath);
      case "png":
        return new ImageAdaptTransparent(filepath);
      default:
        throw new IllegalArgumentException("Image type unsupported");
    }
  }

}
