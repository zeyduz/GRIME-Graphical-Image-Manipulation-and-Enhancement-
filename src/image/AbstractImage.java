package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

abstract class AbstractImage implements Image {


  @Override
  public void makeFile(String imagePath)
          throws IllegalArgumentException, IllegalStateException {
    String filetype = ImageUtil.getImageType(imagePath);
    switch (filetype) {
      case "ppm":
        makePPMFile(imagePath);
        return;
      case "jpg":
      case "jpeg":
      case "bmp":
        BufferedImage asBufferedImage = asBufferedImage();
        BufferedImage newBufferedImage = new BufferedImage(
                asBufferedImage.getWidth(),
                asBufferedImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        instantiateFile(asBufferedImage, newBufferedImage, imagePath);
        return;
      case "png":
        BufferedImage asTransparentBufferedImage = asBufferedImage();
        BufferedImage newTransparentBufferedImage = new BufferedImage(
                asTransparentBufferedImage.getWidth(),
                asTransparentBufferedImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        instantiateFile(asTransparentBufferedImage, newTransparentBufferedImage, imagePath);
        return;
      default:
        throw new IllegalArgumentException("Bad image path");
    }
  }

  // Instantiates the file.
  private void instantiateFile(BufferedImage asBufferedImage, BufferedImage newBufferedImage,
                               String imagePath) {
    newBufferedImage.createGraphics()
            .drawImage(asBufferedImage,
                    0,
                    0,
                    Color.white,
                    null);
    File output = new File(imagePath);
    try {
      if (newBufferedImage.getType() == BufferedImage.TYPE_INT_ARGB) {
        ImageIO.write(asBufferedImage, ImageUtil.getImageType(imagePath), output);
      } else {
        ImageIO.write(newBufferedImage, ImageUtil.getImageType(imagePath), output);
      }
    } catch (IOException e) {
      throw new IllegalStateException("File creation failed");
    }
  }

  // Makes the PPM image with the given path.
  private void makePPMFile(String imagePath) throws IllegalStateException {
    // Make a file object to write to
    File file = new File(imagePath);
    try {
      // Try to create a new file
      if (file.createNewFile()) {
        // this is necessary because it checks if the file doesn't exist already.
      } else {
        throw new IllegalStateException("File already exists.");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Error with file creation.");
    }

    // Add the information to the file
    try {
      FileWriter writer = new FileWriter(imagePath);
      int width = this.getWidth();
      int height = this.getHeight();
      int maxVal = 255;
      writer.write("P3\n" +
              width + "\n" +
              height + "\n" +
              maxVal + "\n");

      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          Pixel pixel = this.getPixel(r, c);
          writer.write(pixel.getRed() + "\n" +
                  pixel.getGreen() + "\n" +
                  pixel.getBlue() + "\n");
        }
      }
      writer.close();
    } catch (IOException e) {
      throw new IllegalStateException("File writing failed");
    }
  }

}