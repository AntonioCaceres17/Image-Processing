package HW05.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class SepiaTest extends IFunctionRGBTest {

  @Override
  protected IFunction createFunction() {
    return new Sepia();
  }

  private ImageModel image;
  private ImageModel sepiaImage;

  @Before
  public void initData() {
    IPixel[][] LoPixel = {
        {new RGBPixel(100, 100, 100),
            new RGBPixel(50, 50, 50)},
        {new RGBPixel(100, 0, 100),
            new RGBPixel(255, 255, 255)}};

    image = new Image2D( LoPixel,0, 255);
    sepiaImage = createFunction().apply(image);
  }

  @Test
  public void testSepiaTone() {
    IPixel pixel = new RGBPixel(135, 120, 93);
    assertEquals(pixel, sepiaImage.getPixel(0, 0));
  }

  @Test
  public void testImageWasModified() {
    assertNotEquals(image.getPixel(1,1), sepiaImage.getPixel(1,1));
    assertNotEquals(image.getPixel(0,0), sepiaImage.getPixel(0,0));
  }
}
