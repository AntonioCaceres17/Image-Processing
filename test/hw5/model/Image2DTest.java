package hw5.model;

import hw5.model.Image2D.ImageReader;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Test class for Image2D.
 */
public class Image2DTest {

  private ImageModel imageAcadia;
  private ImageModel imageRGB;

  @Before
  public void initData() {
    IPixel[][] loPixel = {
        {new RGBPixel(100, 100, 100),
            new RGBPixel(50, 50, 50)},
        {new RGBPixel(100, 0, 100),
            new RGBPixel(255, 255, 255)}};

    imageRGB = new Image2D(loPixel, 0, 255);
    imageAcadia = ImageReader.createImageFromPPM("src/Acadia.ppm");
  }

  @Test
  public void testStoringValues() {
    //Interprets the dimensions of the image
    assertEquals(1024, imageAcadia.width());
    assertEquals(768, imageAcadia.height());

    // Interprets the max/min value of the image channels
    assertEquals(255, imageAcadia.maxPixelValue());
    assertEquals(0, imageAcadia.minPixelValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullPixels() {
    ImageModel nullPixels = new Image2D(null, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithPixelsLength0() {
    ImageModel nullPixels = new Image2D(new IPixel[][]{}, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInconsistentWidth() {
    IPixel[][] pixels = new IPixel[][]{
        {new RGBPixel(0, 0, 0)},
        {}};
    ImageModel nullPixels = new Image2D(new IPixel[0][], 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithPixelsWidth0() {
    ImageModel nullPixels = new Image2D(new IPixel[13][], 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullPixel() {
    IPixel[][] pixels = new IPixel[][]{
        {new RGBPixel(0, 0, 0), null, new RGBPixel(0, 0,0)},
        {new RGBPixel(0, 0, 0), new RGBPixel(0,0,0),
            new RGBPixel(0, 0,0)}
    };
    ImageModel nullPixels = new Image2D(pixels, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithPixelWithInvalidRGBValueBelowMin() {
    IPixel[][] pixels = new IPixel[][]{
        {new RGBPixel(-1, 0, 0)}
    };
    ImageModel nullPixels = new Image2D(pixels, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithPixelWithInvalidRGBValueAboveMax() {
    IPixel[][] pixels = new IPixel[][]{
        {new RGBPixel(10, 256, 0)}
    };
    ImageModel nullPixels = new Image2D(pixels, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullFileName() {
    ImageReader.createImageFromPPM(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectPPMFormat() {
    ImageReader.createImageFromPPM("test/P6PPMFile.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFileNotFound() {
    ImageReader.createImageFromPPM("src/Foo.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullProperties() {
    imageRGB.copyProperties(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyProperties() {
    IPixel[][] emptyPixel = new IPixel[5][5];
    imageRGB.copyProperties(emptyPixel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidProperties() {
    IPixel[][] invalidPixels = {{new RGBPixel(300, 300, 300),
        (new RGBPixel(-1, -20, 20))}};
    imageRGB.copyProperties(invalidPixels);
  }

  @Test
  public void testCopyProperties() {
    IPixel[][] samePixels = imageRGB.getPixels();
    ImageModel sameImage = imageRGB.copyProperties(samePixels);
    assertEquals(sameImage.getPixel(0, 0), imageRGB.getPixel(0, 0));
    assertEquals(sameImage.height(), imageRGB.height());
    assertSame(sameImage.getPixels(), imageRGB.getPixels());
  }
}
