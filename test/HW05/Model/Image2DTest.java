package HW05.Model;

import HW05.Model.Image2D.ImageReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class Image2DTest {

  ImageModel imageKoala;
  ImageModel imageRGB;
  @Before
  public void initData() {
    IPixel[][] LoPixel = {
        {new RGBPixel(100, 100, 100),
            new RGBPixel(50, 50, 50)},
        {new RGBPixel(100, 0, 100),
            new RGBPixel(255, 255, 255)}};

    imageRGB = new Image2D(2, 2, 0, 255, LoPixel);
   imageKoala = ImageReader.createImageFromPPM("src/Koala.ppm");
    }

    @Test
        public void testStoringValues() {
    //Interprets the dimensions of the image
    assertEquals(1024, imageKoala.width());
    assertEquals(768, imageKoala.height());

    // Interprets the max/min value of the image channels
    assertEquals(255, imageKoala.maxPixelValue());
    assertEquals(0, imageKoala.minPixelValue());
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

  @Test
  public void testCopyProperties() {
    IPixel[][] samePixels = imageRGB.getPixels();
    ImageModel sameImage = imageRGB.copyProperties(samePixels);
    assertEquals(sameImage.getPixel(0,0), imageRGB.getPixel(0,0));
    assertEquals(sameImage.height(), imageRGB.height());
    assertSame(sameImage.getPixels(), imageRGB.getPixels());
  }


}
