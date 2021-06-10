package HW05.Model;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransformationTest {

  ImageModel testImage;
  @Before
  public void initData() {
    IPixel[][] LoPixel = {
        {new RGBPixel(100, 100, 100),
            new RGBPixel(50, 50, 50)},
        {new RGBPixel(100, 0, 100),
            new RGBPixel(255, 255, 255)}};

     testImage = new Image2D(2, 2, 0, 255, LoPixel);
  }

  @Test
  public void testSepiaTone() {
    IPixel[][] testPixels = testImage.getPixels();
    IFunction sepia = new Sepia();
    assertEquals(50, testImage.getPixel(1,0).getRed());
  }

}
