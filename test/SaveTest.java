import org.junit.Before;
import org.junit.Test;

import java.io.File;

import image.Image;
import image.ImageAdapt;
import image.ImageAdaptTransparent;
import image.PPMImage;
import imageprocessing.model.imagecommand.ImageCommand;
import imageprocessing.model.imagecommand.SaveCommand;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test the save command.
 */
public class SaveTest {

  ImageProcessingApplicationModel model;


  @Before
  public void setup() {
    this.model = new ImageProcessingApplicationModelImpl();
    this.model.addImage(new PPMImage("src/images/example1.ppm"), "example1");
    this.model.addImage(new PPMImage("src/images/example2.ppm"), "example2");
    this.model.addImage(
            new ImageAdaptTransparent("res/example1.png"), "example1-png");
    this.model.addImage(new ImageAdapt("res/example1.jpg"), "example1-jpg");
    this.model.addImage(new ImageAdapt("res/example1.bmp"), "example1-bmp");
  }

  @Test
  public void testSaveExample1CopyPPMasPPM() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.ppm", "example1");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.ppm");

    Image actual = new PPMImage("res/example1-copy.ppm");
    Image expected = model.getImageCopy("example1");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyPPMasPNG() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.png", "example1");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.png");

    Image actual = new ImageAdaptTransparent("res/example1-copy.png");
    Image expected = model.getImageCopy("example1");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyPPMasJPG() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.jpg", "example1");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.jpg");

    Image actual = new ImageAdapt("res/example1-copy.jpg");
    Image expected = model.getImageCopy("example1");

    assertEquals(actual.getWidth(), expected.getWidth());
    assertEquals(actual.getHeight(), expected.getHeight());

    file.delete();
  }

  @Test
  public void testSaveExample1CopyPPMasBMP() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.bmp", "example1");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.bmp");

    Image actual = new ImageAdapt("res/example1-copy.bmp");
    Image expected = model.getImageCopy("example1");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyPNGasPNG() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.png", "example1-png");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.png");

    Image actual = new ImageAdapt("res/example1-copy.png");
    Image expected = model.getImageCopy("example1-png");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyPNGasJPG() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.jpg", "example1-png");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.jpg");

    Image actual = new ImageAdapt("res/example1-copy.jpg");
    Image expected = model.getImageCopy("example1-png");

    assertEquals(actual.getWidth(), expected.getWidth());
    assertEquals(actual.getHeight(), expected.getHeight());

    file.delete();
  }

  @Test
  public void testSaveExample1CopyPNGasBMP() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.bmp", "example1-png");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.bmp");

    Image actual = new ImageAdapt("res/example1-copy.bmp");
    Image expected = model.getImageCopy("example1-png");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyPNGasPPM() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.ppm", "example1-png");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.ppm");

    Image actual = new PPMImage("res/example1-copy.ppm");
    Image expected = model.getImageCopy("example1-png");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyJPGasJPG() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.jpg", "example1-jpg");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.jpg");

    Image actual = new ImageAdapt("res/example1-copy.jpg");
    Image expected = model.getImageCopy("example1-jpg");

    assertEquals(actual.getWidth(), expected.getWidth());
    assertEquals(actual.getHeight(), expected.getHeight());

    file.delete();
  }

  @Test
  public void testSaveExample1CopyJPGasPNG() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.png", "example1-jpg");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.png");

    Image actual = new ImageAdaptTransparent("res/example1-copy.png");
    Image expected = model.getImageCopy("example1-jpg");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyJPGasBMP() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.bmp", "example1-jpg");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.bmp");

    Image actual = new ImageAdapt("res/example1-copy.bmp");
    Image expected = model.getImageCopy("example1-jpg");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyJPGasPPM() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.ppm", "example1-jpg");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.ppm");

    Image actual = new PPMImage("res/example1-copy.ppm");
    Image expected = model.getImageCopy("example1-jpg");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyBMPasBMP() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.bmp", "example1-bmp");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.bmp");

    Image actual = new ImageAdapt("res/example1-copy.bmp");
    Image expected = model.getImageCopy("example1-bmp");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyBMPasPNG() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.png", "example1-bmp");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.png");

    Image actual = new ImageAdaptTransparent("res/example1-copy.png");
    Image expected = model.getImageCopy("example1-bmp");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample1CopyBMPasJPG() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.jpg", "example1-bmp");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.jpg");

    Image actual = new ImageAdapt("res/example1-copy.jpg");
    Image expected = model.getImageCopy("example1-bmp");

    assertEquals(actual.getWidth(), expected.getWidth());
    assertEquals(actual.getHeight(), expected.getHeight());

    file.delete();
  }

  @Test
  public void testSaveExample1CopyBMPasPPM() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example1-copy.ppm", "example1-bmp");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example1-copy.ppm");

    Image actual = new PPMImage("res/example1-copy.ppm");
    Image expected = model.getImageCopy("example1-bmp");

    assertEquals(expected, actual);

    file.delete();
  }

  @Test
  public void testSaveExample2Copy() {
    // Create and execute the save command
    ImageCommand cmd = new SaveCommand("res/example2-copy.ppm", "example2");
    cmd.execute(model);

    // Ensure that the file was created and make sure it is the same as the original
    File file = new File("res/example2-copy.ppm");

    Image actual = new PPMImage("res/example2-copy.ppm");
    Image expected = model.getImageCopy("example2");

    assertEquals(expected, actual);

    file.delete();
  }

}
