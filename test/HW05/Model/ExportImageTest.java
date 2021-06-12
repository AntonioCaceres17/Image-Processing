package HW05.Model;

import HW05.Model.Image2D.ImageReader;
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
}
