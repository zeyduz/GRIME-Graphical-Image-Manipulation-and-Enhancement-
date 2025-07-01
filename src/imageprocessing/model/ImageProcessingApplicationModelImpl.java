package imageprocessing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import image.Image;

/**
 * Represents an implementation of an Image Processing Application Model. This class implements the
 * ImageProcessingApplicationModel interface.
 */
public class ImageProcessingApplicationModelImpl implements ImageProcessingApplicationModel {

  // INVARIANT: IMAGES NOR NAMES ARE NEVER NULL
  private final Map<String, Image> images;

  private final List<String> names;

  /**
   * Constructor for ImageProcessingApplicationModelImpl.
   */
  public ImageProcessingApplicationModelImpl() {
    images = new HashMap<String, Image>();
    names = new ArrayList<>();
  }

  @Override
  public void addImage(Image image, String name) throws IllegalArgumentException {
    if (image == null || name == null) {
      throw new IllegalArgumentException("Image or name cannot be null");
    }
    this.images.put(name, image);
    this.names.add(name);
  }

  @Override
  public Image getImageCopy(String name) throws IllegalArgumentException {
    Image reference = images.get(name);

    if (reference == null) {
      throw new IllegalArgumentException("No Image has that Name");
    }

    return reference.makeCopy();
  }

  @Override
  public String[] getImageNames() {
    String[] names = new String[images.size()];
    int index = 0;
    for (String name : images.keySet()) {
      names[index] = name;
      index++;
    }
    return names;
  }

  @Override
  public String getNewestImageName() {
    return names.get(names.size() - 1);
  }
}
