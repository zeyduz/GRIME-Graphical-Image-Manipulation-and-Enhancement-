import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import image.PPMImage;
import imageprocessing.model.ImageProcessingApplicationModel;
import imageprocessing.model.ImageProcessingApplicationModelImpl;
import imageprocessing.model.ImageProcessingApplicationModelState;
import imageprocessing.view.ImageProcessingApplicationTextView;
import imageprocessing.view.ImageProcessingApplicationView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the IPA text view.
 */
public class ImageProcessingApplicationTextViewTest {

  ImageProcessingApplicationModelState model;
  ImageProcessingApplicationView view;
  Appendable corruptAppendable;

  @Before
  public void setup() {
    model = new ImageProcessingApplicationModelImpl();
    view = new ImageProcessingApplicationTextView(model);
    corruptAppendable = new CorruptMockAppendable();
  }

  // -------------------- TEST CONSTRUCTOR EXCEPTIONS --------------------//

  @Test(expected = IllegalArgumentException.class)
  public void testSingleArgumentConstructorException() {
    this.view = new ImageProcessingApplicationTextView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNullModel() {
    this.view = new ImageProcessingApplicationTextView(new StringBuilder(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNullAppendable() {
    this.view = new ImageProcessingApplicationTextView(null, model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNullEverything() {
    this.view = new ImageProcessingApplicationTextView(null, null);
  }

  // -------------------- TEST RENDER MESSAGE --------------------//

  @Test
  public void testRenderMessageSingleLine() {
    Appendable out = new StringBuilder();
    this.view = new ImageProcessingApplicationTextView(out, model);

    try {
      this.view.renderMessage("hi there");
    } catch (IOException e) {
      fail();
    }

    assertEquals("hi there", out.toString());
  }

  @Test
  public void testRenderMessageTwoLines() {
    Appendable out = new StringBuilder();
    this.view = new ImageProcessingApplicationTextView(out, model);

    try {
      this.view.renderMessage("hi there\nline numero dos");
    } catch (IOException e) {
      fail();
    }

    assertEquals("hi there\nline numero dos", out.toString());
  }

  @Test
  public void testRenderMessageNoContent() {
    Appendable out = new StringBuilder();
    this.view = new ImageProcessingApplicationTextView(out, model);

    try {
      this.view.renderMessage("");
    } catch (IOException e) {
      fail();
    }

    assertEquals("", out.toString());
  }

  // -------------------- TEST RENDER MESSAGE EXCEPTION --------------------//
  @Test(expected = IOException.class)
  public void testRenderMessageIOException() throws IOException {
    this.view = new ImageProcessingApplicationTextView(corruptAppendable, model);
    this.view.renderMessage("corrupt lol");
  }

  // -------------------- TEST RENDER LIST --------------------//
  @Test
  public void renderEmptyList() {
    Appendable out = new StringBuilder();
    this.view = new ImageProcessingApplicationTextView(out, model);

    try {
      this.view.renderImageList();
    } catch (IOException e) {
      fail();
    }

    assertEquals("No Images Loaded Yet.", out.toString());
  }

  @Test
  public void renderListOf2() {
    Appendable out = new StringBuilder();
    this.view = new ImageProcessingApplicationTextView(out, model);

    // load two images

    ((ImageProcessingApplicationModel) model).addImage(
            new PPMImage("src/images/example1.ppm"), "example1");
    ((ImageProcessingApplicationModel) model).addImage(
            new PPMImage("src/images/example2.ppm"), "example2");

    try {
      this.view.renderImageList();
    } catch (IOException e) {
      fail();
    }

    assertEquals("Image Names:\nexample2\nexample1", out.toString());
  }

  // -------------------- TEST RENDER LIST EXCEPTION --------------------//
  @Test(expected = IOException.class)
  public void tstRenderListIOException() throws IOException {
    this.view = new ImageProcessingApplicationTextView(corruptAppendable, model);
    this.view.renderImageList();
  }

}
