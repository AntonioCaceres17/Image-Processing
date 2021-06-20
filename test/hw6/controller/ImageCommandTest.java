package hw6.controller;

import hw6.model.IMultiLayerImageModel;
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
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals(1, model.numLayers());
  }

  @Test
  public void testCommentAtFirstChar() {
    Readable rd = new StringReader("#import src/Acadia.JPG");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals(0, model.numLayers());
  }

  @Test
  public void testAdd2Layers() {
    Readable rd = new StringReader("import src/Acadia.JPG\n"
        + "import src/Acadia.ppm");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
    assertEquals(2, model.numLayers());
  }

  @Test
  public void testSwap() {
    Readable rd = new StringReader("import src/Acadia.JPG\n"
        + "import src/Acadia.ppm\n"
        + "swap 1 2 #test the image names");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController controller = new ImageCommand(model, rd, ap);
    controller.read();
  }
}
