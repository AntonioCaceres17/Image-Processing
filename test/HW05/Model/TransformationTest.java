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
    ImageModel imageSepia = testImage;
    IFunction sepia = new Sepia();
    assertEquals(50, imageSepia.getPixel(1,0).getRed());
    imageSepia = sepia.apply(testImage);
    // r = .393* 50 + .769 * 50 + .189 * 50 or 67.55
    System.out.println(imageSepia);
    assertEquals(50, imageSepia.getPixel(1,0).getRed());
  }

}
