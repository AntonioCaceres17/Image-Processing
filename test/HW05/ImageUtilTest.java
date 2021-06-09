package HW05;

import HW05.Model.BlurImage;
import HW05.Model.Filter;
import HW05.Model.IFunction;
import HW05.Model.Image2D.ImageReader;
import HW05.Model.ImageModel;
import HW05.Model.Sepia;
import HW05.Model.Transformation;
import java.util.function.IntFunction;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ImageUtilTest {

  @Test
  public void testPPMReader() {
    ImageModel koala = ImageReader.createImageFromPPM("src/koala.ppm");
    IFunction sepiaImage = new Sepia();
    ImageModel sepiaKoala = sepiaImage.apply(koala);
    ExportImage exportKoala = new ExportImage(sepiaKoala);
    exportKoala.makePPM("test/KoalaExport.ppm");
  }
  /*
  Things to test :
  In the ExportImage:
  Give a invalid Image/Null string
  Also check to see if we can test the try catch method they made me put when i tried to create
  a file.
  All apply method throwing exception if the image has a empty list of pixel.
   */
}
