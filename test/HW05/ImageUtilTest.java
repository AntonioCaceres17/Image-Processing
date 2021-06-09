package HW05;

import HW05.Model.Image2D.ImageReader;
import HW05.Model.ImageModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ImageUtilTest {

  @Test
  public void testPPMReader() {
    ImageModel koala = ImageReader.createImageFromPPM("src/koala.ppm");
    ExportImage exportKoala = new ExportImage(koala);
    System.out.println(koala.maxValue());
    //exportKoala.makePPM("test/KoalaExport.ppm");
  }
}
