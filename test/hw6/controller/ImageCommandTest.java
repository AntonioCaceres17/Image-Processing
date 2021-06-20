package hw6.controller;

import hw6.model.IMultiLayerImageModel;
import hw6.model.MockMultiLayerImage;
import hw6.model.MultiLayerImage;
import java.io.StringReader;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test class for our controller. This test class contains unit tests to see if the controller
 * properly reads in the input and passes the correct data to the given model. It tests this
 * using a mock MultiLayerImage model.
 */
public class ImageCommandTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    Readable rd = new StringReader("import src/Acadia.JPG");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController controller = new ImageCommand(null, rd, ap);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    Readable rd = new StringReader("import src/Acadia.JPG");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController controller = new ImageCommand(model, null, ap);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    Readable rd = new StringReader("import src/Acadia.JPG");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController controller = new ImageCommand(model, rd, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommand() {
    Readable rd = new StringReader("port src/Acadia.JPG");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
  }

  @Test
  public void testImportJPG() {
    Readable rd = new StringReader("import src/Acadia.JPG");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals(1, model.numLayers());
  }

  @Test
  public void testImportPPM() {
    Readable rd = new StringReader("import src/Acadia.JPG");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals(1, model.numLayers());
  }

  @Test
  public void testCommentAtFirstChar() {
    Readable rd = new StringReader("#import src/Acadia.JPG");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("", ap.toString());
  }

  @Test
  public void testAdd2Layers() {
    Readable rd = new StringReader("import src/Acadia.JPG\n"
        + "import src/Acadia.ppm\n");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("Add layer: src/Acadia.JPG\n"
        + "Add layer: src/Acadia.ppm\n", ap.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnfinishedImportCommand() {
    Readable rd = new StringReader("import #nothing here");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
  }

  @Test
  public void testSwap() {
    Readable rd = new StringReader("swap 1 2 #test the image names");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("swap layers 0 and 1\n", ap.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSwapFirstLayerInvalid() {
    Readable rd = new StringReader("swap q 2 #test the image names");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSwapSecondLayerInvalid() {
    Readable rd = new StringReader("swap 1 q #test the image names");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
  }

  @Test
  public void testToggleCommand() {
    Readable rd = new StringReader("toggle");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("toggle visibility\n", ap.toString());
  }

  @Test
  public void testSetCurrent() {
    Readable rd = new StringReader("current 1");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("set current to: 0\n", ap.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentInvalidLayerInput() {
    Readable rd = new StringReader("current hello");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
  }

  @Test
  public void testApplyBlur() {
    Readable rd = new StringReader("blur");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("apply function: BlurImage\n", ap.toString());
  }

  @Test
  public void testApplySharpen() {
    Readable rd = new StringReader("sharpen");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("apply function: SharpenImage\n", ap.toString());
  }

  @Test
  public void testApplySepia() {
    Readable rd = new StringReader("sepia");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("apply function: Sepia\n", ap.toString());
  }

  @Test
  public void testApplyGreyScale() {
    Readable rd = new StringReader("grayscale");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("apply function: Monochrome\n", ap.toString());
  }

  //TODO: test export
}
