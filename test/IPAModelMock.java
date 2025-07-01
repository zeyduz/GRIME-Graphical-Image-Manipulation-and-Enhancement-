import java.io.IOException;

import image.Image;
import image.PPMImage;
import imageprocessing.model.ImageProcessingApplicationModel;

/**
 * Mock for IPA Model.
 */
public class IPAModelMock implements ImageProcessingApplicationModel {

  Appendable out;


  public IPAModelMock(Appendable out) {
    this.out = out;
  }

  @Override
  public void addImage(Image image, String name) {
    try {
      out.append("New name: " + name + "\n");
    } catch (IOException ignored) {

    }
  }

  @Override
  public Image getImageCopy(String name) {
    try {
      out.append("Original name: " + name + "\n");
    } catch (IOException ignored) {

    }

    return new PPMImage("src/images/example1.ppm");
  }

  @Override
  public String[] getImageNames() {
    return new String[0];
  }

  @Override
  public String getNewestImageName() {
    return "a name";
  }
}
