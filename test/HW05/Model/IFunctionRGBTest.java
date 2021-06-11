package HW05.Model;

import HW05.ExportImage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Abstract test class for Image function objects
 */
public abstract class IFunctionRGBTest {

  private ImageModel testImage;

  protected abstract IFunction createFunction();

  @Before
  public void initData() {
    IPixel[][] LoPixel = {
        {new RGBPixel(100, 100, 100),
            new RGBPixel(50, 50, 50)},
        {new RGBPixel(100, 0, 100),
            new RGBPixel(255, 255, 255)}};

    testImage = new Image2D( LoPixel,0, 255);
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
  public void testNullImageFunction() {
    IFunction transform = createFunction();
    transform.apply(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyPixelTransformation() {
    IFunction transform = new Sepia();
    IPixel[][] emptyPixels = new IPixel[5][5];
    ImageModel invalidModel = new Image2D( emptyPixels, 0, 255);
    transform.apply(invalidModel);
  }
}
