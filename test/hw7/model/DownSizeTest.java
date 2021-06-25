package hw7.model;

import hw5.model.Image2D.ImageReader;
import hw5.model.ImageModel;
import hw6.model.ExportImage;
import org.junit.Test;

public class DownSizeTest {

  @Test
  public void testHalfDownsize() {
    ImageModel image = ImageReader.createImageFromPPM("res/Acadia.ppm");
    ImageModel halfImage = new DownSize(0.5, 0.5).apply(image);
    ExportImage exportMosaic = new ExportImage(halfImage);
    exportMosaic.makePPM("res/halfAcadia2.ppm");
  }

  @Test
  public void testSqueezeWidth() {
    ImageModel image = ImageReader.createImageFromPPM("res/Acadia.ppm");
    ImageModel halfImage = new DownSize(0.67, 1).apply(image);
    ExportImage exportMosaic = new ExportImage(halfImage);
    exportMosaic.makePPM("res/TwoThirdWidthAcadia.ppm");
  }
}
