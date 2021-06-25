package hw7.model;

import hw5.model.IFunction;
import hw5.model.IPixel;
import hw5.model.Image2D;
import hw5.model.Image2D.ImageReader;
import hw5.model.ImageModel;
import hw5.model.RGBPixel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DownSizeTest {

  private ImageModel image;

  @Before
  public void setUp() {
    image = ImageReader.createImageFromPPM("res/Acadia.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    IFunction downsize = new DownSize(1, 1);
    downsize.apply(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWidthAbove1() {
    IFunction downsize = new DownSize(1.1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWidth0() {
    IFunction downsize = new DownSize(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHeightAbove1() {
    IFunction downsize = new DownSize(1, 1.1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHeight0() {
    IFunction downsize = new DownSize(1, 0);
  }

  @Test
  public void testDownSizeWidthAndHeight() {
    IFunction downsize = new DownSize(0.5, 0.67);
    ImageModel downSizedImage = downsize.apply(image);
    assertEquals((int) (image.width() * 0.5), downSizedImage.width());
    assertEquals((int) (image.height() * 0.67), downSizedImage.height());
  }

  @Test
  public void testDownSizeScaleOf1ChangesNothing() {
    IFunction downsize = new DownSize(1, 1);
    ImageModel downSizedImage = downsize.apply(image);
    assertEquals(image, downSizedImage);
  }

  @Test
  public void testPixelValue() {
    IFunction downsize = new DownSize(0.5, 1);
    IPixel[][] pixels = new IPixel[][]{
        {new RGBPixel(10, 10, 30), new RGBPixel(40, 50, 60),
            new RGBPixel(70, 80, 90), new RGBPixel(100, 110, 120)},
        {new RGBPixel(130, 140, 150), new RGBPixel(160, 170, 180),
            new RGBPixel(190, 200, 210), new RGBPixel(220, 230, 240)},
        {new RGBPixel(25, 75, 95), new RGBPixel(100, 120, 150),
            new RGBPixel(10, 120, 15), new RGBPixel(0, 0, 69)},
        {new RGBPixel(4, 2, 0), new RGBPixel(100, 100, 255),
            new RGBPixel(255, 255, 255), new RGBPixel(100, 100, 10)}
    };
    ImageModel testImage = new Image2D(pixels, 0, 255);
    ImageModel downSizedImage = downsize.apply(testImage);

    assertEquals(testImage.getPixel(2, 2), downSizedImage.getPixel(1, 2));
  }
}
