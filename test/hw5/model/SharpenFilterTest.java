package hw5.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Sharpen Image filter.
 */
public class SharpenFilterTest extends IFunctionRGBTest {

  @Override
  protected IFunction createFunction() {
    return new SharpenImage();
  }

  private ImageModel sharpenedImage;
  private ImageModel image;

  @Before
  public void initData() {
    IPixel[][] pixels = new IPixel[][]{
        {new RGBPixel(10, 20, 30), new RGBPixel(50, 50, 50),
            new RGBPixel(100, 100, 100)},
        {new RGBPixel(10, 20, 30), new RGBPixel(50, 50, 50),
            new RGBPixel(100, 100, 100)},
        {new RGBPixel(10, 20, 30), new RGBPixel(50, 50, 50),
            new RGBPixel(250, 250, 250)}
    };
    image = new Image2D(pixels, 0, 255);
    sharpenedImage = createFunction().apply(image);
  }

  @Test
  public void testBlurCornerPixel() {
    IPixel pixel = new RGBPixel(0, 0,0);
    assertEquals(pixel, sharpenedImage.getPixel(0,0));
  }

  @Test
  public void testBlurImageNotCornerPixel() {
    IPixel pixel = new RGBPixel(195, 202, 210);
    assertEquals(pixel, sharpenedImage.getPixel(1,1));
  }

  @Test
  public void testImageWasModified() {
    assertNotEquals(image.getPixel(1,1), sharpenedImage.getPixel(1,1));
    assertNotEquals(image.getPixel(0,0), sharpenedImage.getPixel(0,0));
  }
}
