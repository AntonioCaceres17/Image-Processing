package hw6.model;

import hw5.model.IPixel;
import hw5.model.Image2D;
import hw5.model.ImageModel;
import hw5.model.RGBPixel;
import org.junit.Before;
import org.junit.Test;

public class MultiLayerImageTest {

  private IMultiLayerImageModel model;

  @Before
  public void setUp() {
    model = new MultiLayerImage();
  }

  @Test
  public void testAddLayer() {
    IPixel[][] pixels = new IPixel[][]{
      {new RGBPixel(0,0,0)}
    };
    ImageModel image = new Image2D(pixels, 0, 255);
  }



}
