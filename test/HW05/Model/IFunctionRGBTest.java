package HW05.Model;

import static org.junit.Assert.assertEquals;

import HW05.ExportImage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IFunctionRGBTest {

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
    assertEquals(50, imageSepia.getPixel(1, 0).getRed());
    imageSepia = sepia.apply(testImage);
    // r = .393* 50 + .769 * 50 + .189 * 50 or 67.55
    assertEquals(67, imageSepia.getPixel(1, 0).getRed());
    //Test if the original image is unchanged
    assertEquals(50, testImage.getPixel(1, 0).getRed());
  }

  @Test
  public void testMonoTone() {
    ImageModel imageMono = testImage;
    IFunction mono = new Monochrome();
    assertEquals(50, imageMono.getPixel(1,0).getRed());
    imageMono = mono.apply(imageMono);
    assertEquals(50, imageMono.getPixel(1,0).getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullImageTransformation() {
    IFunction transform = new Sepia();
    transform.apply(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyPixelTransformation() {
    IFunction transform = new Sepia();
    IPixel[][] emptyPixels = new IPixel[5][5];
    ImageModel invalidModel = new Image2D(10, 10, 0, 255, emptyPixels);
    transform.apply(invalidModel);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullImageFilter() {
    IFunction filter = new BlurImage();
    filter.apply(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyImageFilter() {
    IFunction filter = new BlurImage();
    IPixel[][] emptyPixels = new IPixel[5][5];
    ImageModel invalidModel = new Image2D(10, 10, 0, 255, emptyPixels);
    filter.apply(invalidModel);
  }
}
