package hw7.model;

import hw5.model.IFunction;
import hw5.model.Image2D.ImageReader;
import hw5.model.ImageModel;
import hw5.model.RGBPixel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MosaicTest {

  private ImageModel image;

  @Before
  public void setUp() {
    image = ImageReader.createImageFromPPM("res/Acadia.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    IFunction mosaic = new Mosaic(1000);
    mosaic.apply(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMosaicWithNoSeeds() {
    IFunction mosaic = new Mosaic(0);
  }

  @Test
  public void testTileAveragesColor() {
    IFunction mosaic = new Mosaic(1);
    ImageModel mosaicImage = mosaic.apply(image);
    assertEquals(mosaicImage.getPixel(100, 100), mosaicImage.getPixel(0,0));
  }
}
