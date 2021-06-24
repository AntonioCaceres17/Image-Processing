package hw7.model;

import hw5.model.Image2D.ImageReader;
import hw5.model.ImageModel;
import hw6.model.ExportImage;
import org.junit.Test;

public class MosaicTest {

  @Test
  public void testMosaic() {
    ImageModel image = ImageReader.createImageFromPPM("res/Acadia.ppm");
    ImageModel mosaicImage = new Mosaic(150).apply(image);
    ExportImage exportMosaic = new ExportImage(mosaicImage);
    exportMosaic.makePPM("res/MosaicAcadia.ppm");
  }

  @Test
  public void testMosaic300Seeds() {
    ImageModel image = ImageReader.createImageFromPPM("res/Acadia.ppm");
    ImageModel mosaicImage = new Mosaic(300).apply(image);
    ExportImage exportMosaic = new ExportImage(mosaicImage);
    exportMosaic.makePPM("res/MosaicAcadia300.ppm");
  }

  @Test
  public void testMosaic1500Seeds() {
    ImageModel image = ImageReader.createImageFromPPM("res/Acadia.ppm");
    ImageModel mosaicImage = new Mosaic(1500).apply(image);
    ExportImage exportMosaic = new ExportImage(mosaicImage);
    exportMosaic.makePPM("res/MosaicAcadia1500.ppm");
  }
}
