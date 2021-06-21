package hw5.model;

import hw5.model.Image2D.ImageReader;
import hw6.controller.ImageCommand;
import hw6.controller.ImageController;
import hw6.model.ExportImage;
import hw6.model.IMultiLayerImageModel;
import hw6.model.MultiLayerImage;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.ModuleLayer.Controller;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test class for testing exporting images works properly.
 */
public class ExportImageTest {

  private ImageModel image = ImageReader.createImageFromPPM("src/Acadia.ppm");
  private ExportImage exportImage = new ExportImage(image);

  @Test(expected = IllegalArgumentException.class)
  public void testGivenNullImage() {
    ExportImage nullImage = new ExportImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportMustEndWithPPM() {
    exportImage.makePPM("AcadiaCopy.ppm");
  }

  @Test
  public void testExportImageAndImportSameOne() {
    exportImage.makePPM("AcadiaCopy.ppm");
    ImageModel copy = ImageReader.createImageFromPPM("AcadiaCopy.ppm");
    assertEquals(copy, image);
  }

  @Test
  public void testExportToJPEG() {
    Readable rd = new StringReader("import src/Acadia.JPG");
    Appendable ap = new StringBuilder();
    IMultiLayerImageModel model = new MultiLayerImage();
    ImageController control = new ImageCommand(model, rd,ap);
    control.read();

  }
}
