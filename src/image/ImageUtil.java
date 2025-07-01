package image;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * This class contains utility methods to read a PPM image from file
 * and simply print its contents. Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Find the width of the supplied PPM file.
   * @param filename The filepath to find the file at.
   * @return The width of the ppm file at the supplied path.
   * @throws IllegalArgumentException if the filetype is invalid
   */
  public static int getPPMWidth(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("invalid file type, not PPM");
    }
    return sc.nextInt(); // return the width
  }

  /**
   * Find the height of the supplied PPM file.
   * @param filename The filepath to find the file at.
   * @return The height of the ppm file at the supplied path.
   * @throws IllegalArgumentException if the filetype is invalid
   */
  public static int getPPMHeight(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("invalid file type, not PPM");
    }
    sc.nextInt(); // ignore the width
    return sc.nextInt(); // return the height
  }

  /**
   * Find the pixels of the supplied PPM file as a 2D-array of pixels.
   * @param filename The filepath to find the file at.
   * @return The pixels of the supplied PPM file as a 2D-array of pixels.
   * @throws IllegalArgumentException if the filetype is invalid
   */
  public static Pixel[][] getPPMPixels(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("invalid file type, not PPM");
    }
    int width = sc.nextInt(); // get the width
    int height = sc.nextInt(); // get the height
    int max = sc.nextInt(); // get the max value

    Pixel[][] output = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        output[row][col] = new RGBAPixel(max, r, g, b, 255);
      }
    }

    return output;
  }

  /**
   * Find the file type of the file at the supplied file path.
   * @param filename The name of the file.
   * @return A string representation of the file type of the file
   * @throws IllegalArgumentException if the file path is not valid
   */
  public static String getImageType(String filename) throws IllegalArgumentException {
    int index = filename.lastIndexOf('.');
    if (index > 0) {
      return filename.substring(index + 1);
    }
    throw new IllegalArgumentException("Bad file path");
  }

}

