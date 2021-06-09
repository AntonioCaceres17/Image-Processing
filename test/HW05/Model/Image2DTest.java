package HW05.Model;

import HW05.Model.Image2D.ImageReader;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Image2DTest {

  @Test
  public void testImageReaderCreateImage() {
    ImageModel<IPixel> image = ImageReader.createImage("src/Koala.ppm");
    for (int row = 0; row < image.height(); row++) {
      for (int column = 0; column < image.width(); column++) {
        System.out.println("x: " + row);
      }
    }
  }
}
