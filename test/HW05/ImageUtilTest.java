package HW05;

import HW05.Model.BlurImage;
import HW05.Model.Filter;
import HW05.Model.IFunction;
import HW05.Model.IPixel;
import HW05.Model.Image2D.ImageReader;
import HW05.Model.ImageModel;
import HW05.Model.Monochrome;
import HW05.Model.Sepia;
import HW05.Model.SharpenImage;
import HW05.Model.Transformation;
import java.util.function.IntFunction;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ImageUtilTest {

  @Test
  public void testPPMReader() {
    ImageModel koala = ImageReader.createImageFromPPM("src/koala.ppm");
//    ExportImage exportKoalaNoChangesMade = new ExportImage(koala);
//    exportKoalaNoChangesMade.makePPM("test/sameKoala3.ppm");
    IFunction sepiaImage = new Sepia();
    ImageModel sepiaKoala = sepiaImage.apply(koala);
    ExportImage exportKoala = new ExportImage(sepiaKoala);
    exportKoala.makePPM("test/KoalaSepia8.ppm");
//    IFunction blurImage = new BlurImage();
//    ImageModel blurredKoala = blurImage.apply(koala);
//    ExportImage exportBlurredKoala = new ExportImage(blurredKoala);
//    exportBlurredKoala.makePPM("test/blurredKoala7.ppm");
//    IFunction sharpenImage = new SharpenImage();
//    ImageModel sharpKoala = sharpenImage.apply(koala);
//    ExportImage exportSharpKoala = new ExportImage(sharpKoala);
//    exportSharpKoala.makePPM("test/sharpKoala3.ppm");
    IFunction monochrome = new Monochrome();
    ImageModel monochromeKoala = monochrome.apply(koala);
    ExportImage exportMonochrome = new ExportImage(monochromeKoala);
    exportMonochrome.makePPM("test/monochromeKoala3.ppm");
//    IPixel testPixel = koala.getPixel(100, 100);
//    System.out.println(testPixel.getRed() + " "  + testPixel.getGreen() + " " + testPixel.getBlue());
//    System.out.println(monochromeKoala.getPixel(100, 100).getRed());
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
