package hw6.controller;

import hw6.model.IMultiLayerImageModel;
import hw6.model.MockMultiLayerImage;
import hw6.model.MultiLayerImage;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ImageCommandTest {

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

  @Test
  public void testSwap() {
    Readable rd = new StringReader("swap 1 2 #test the image names");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MockMultiLayerImage(ap);
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals("swap layers 0 and 1\n", ap.toString());
  }
}
