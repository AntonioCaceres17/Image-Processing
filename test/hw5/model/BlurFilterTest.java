package hw5.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for the blur filter.
 */
public class BlurFilterTest extends IFunctionRGBTest {

  private ImageModel blurredImage;
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
    blurredImage = createFunction().apply(image);
  }

  @Override
  protected IFunction createFunction() {
    return new BlurImage();
  }

  @Test
  public void testBlurCornerPixel() {
    IPixel pixel = new RGBPixel(new int[]{
        (int) (10 * .25 + 50 * .125 + 10 * .125 + 50 * .0625),
        (int) (20 * .25 + 50 * .125 + 20 * .125 + 50 * .0625),
        (int) (30 * .25 + 50 * .125 + 30 * .125 + 50 * .0625)
    });
    assertEquals(pixel, blurredImage.getPixel(0, 0));
  }

  @Test
  public void testBlurImageFilterAndImageSameSize() {
    IPixel pixel = new RGBPixel(61, 64,66);
    assertEquals(pixel, blurredImage.getPixel(1,1));
  }

  @Test
  public void testImageWasModified() {
    assertNotEquals(image.getPixel(1,1), blurredImage.getPixel(1,1));
    assertNotEquals(image.getPixel(0,0), blurredImage.getPixel(0,0));
  }
}
