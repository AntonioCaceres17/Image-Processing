package hw6.model;

import hw5.model.IPixel;
import hw5.model.Image2D;
import hw5.model.ImageModel;
import hw5.model.RGBPixel;
import hw5.model.Sepia;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test class for LayeredImage2D class. This class tests all the unit function of the class to
 * ensure they work properly.
 */
public class LayeredImage2DTest {

  private ImageModel image;

  @Before
  public void setUp() {
    image = new Image2D(new IPixel[][]{{new RGBPixel(100, 100, 100)}},
        0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    LayeredImageModel model = new LayeredImage2D(null, true, image);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    LayeredImageModel model = new LayeredImage2D("name", true, null);
  }

  @Test
  public void testIsVisible() {
    LayeredImageModel model = new LayeredImage2D("name", true, image);
    assertEquals(true, model.isVisible());
  }

  @Test
  public void testToggleVisibility() {
    LayeredImageModel model = new LayeredImage2D("name", true, image);
    model.toggleVisibility();
    assertEquals(false, model.isVisible());
  }

  @Test
  public void testName() {
    LayeredImageModel model = new LayeredImage2D("name", true, image);
    assertEquals("name", model.name());
  }

  @Test
  public void testGetImage() {
    LayeredImageModel model = new LayeredImage2D("name", true, image);
    assertEquals(image.copyProperties(image.getPixels()), model.getImage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullFunction() {
    LayeredImageModel model = new LayeredImage2D("name", true, image);
    model.apply(null);
  }

  @Test
  public void testApplyFunction() {
    LayeredImageModel model = new LayeredImage2D("name", true, image);
    model.apply(new Sepia());
    assertEquals(new Sepia().apply(image), model.getImage());
  }
}
