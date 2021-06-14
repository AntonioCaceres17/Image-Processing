package hw5.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for Monochrome transformation.
 */
public class MonochromeTest extends IFunctionRGBTest {

  @Override
  protected IFunction createFunction() {
    return new Monochrome();
  }

  private ImageModel monochromeImage;
  private ImageModel image;

  @Before
  public void initData() {
    IPixel[][] loPixel = {
        {new RGBPixel(100, 75, 50),
            new RGBPixel(50, 50, 50)},
        {new RGBPixel(100, 0, 100),
            new RGBPixel(255, 255, 255)}};

    image = new Image2D( loPixel,0, 255);
    monochromeImage = createFunction().apply(image);
  }

  @Test
  public void testMonoTonePixelTransformedCorrectly() {
    IPixel pixel = new RGBPixel(78, 78, 78);
    assertEquals(pixel, monochromeImage.getPixel(0,0));
  }

  @Test
  public void testMonoToneAllPixelsHaveSameRGB() {
    for (IPixel[] row : monochromeImage.getPixels()) {
      for (IPixel pixel : row) {
        int pixelValue = pixel.getRed();
        assertEquals(pixelValue, pixel.getGreen());
        assertEquals(pixelValue, pixel.getBlue());
      }
    }
  }

  @Test
  public void testImageWasModified() {
    assertNotEquals(image.getPixel(1,1), monochromeImage.getPixel(1,1));
    assertNotEquals(image.getPixel(0,0), monochromeImage.getPixel(0,0));
  }
}
