package hw6.model;

import hw5.model.BlurImage;
import hw5.model.IPixel;
import hw5.model.Image2D;
import hw5.model.ImageModel;
import hw5.model.Monochrome;
import hw5.model.RGBPixel;
import hw5.model.Sepia;
import hw5.model.SharpenImage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
    LayeredImageModel layer = new LayeredImage2D("pixel", true, image);
    model.addLayer(layer);
    assertEquals(1, model.numLayers());
    model.addLayer(new LayeredImage2D("copy", true, image.copyProperties(pixels)));
    assertEquals(2, model.numLayers());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullLayer() {
    model.addLayer(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerNotSameSize() {
    IPixel[][] pixels = new IPixel[][]{
        {new RGBPixel(0,0,0)}
    };
    ImageModel image = new Image2D(pixels, 0, 255);
    LayeredImageModel layer = new LayeredImage2D("pixel", true, image);
    model.addLayer(layer);
    assertEquals(1, model.numLayers());
    model.addLayer(new LayeredImage2D("2pixel", true,
        new Image2D(new IPixel[][]{
            {new RGBPixel(0,0,0)},
            {new RGBPixel(0,0,0)}
        }, 0, 255)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentNoLayers() {
    model.setCurrent(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerIndexTooLow() {
    model.addLayer(new LayeredImage2D("", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    model.setCurrent(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerIndexOutOfBounds() {
    model.addLayer(new LayeredImage2D("", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    model.setCurrent(1);
  }

  @Test
  public void testSetCurrent() {
    model.addLayer(new LayeredImage2D("l1", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    model.addLayer(new LayeredImage2D("l2", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    // current layer is the newest one
    assertEquals("l2", model.layerName());
    model.setCurrent(0);
    // new current layer should be l1
    assertEquals("l1", model.layerName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSwapNoLayers() {
    model.swapLayers(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSwapOneIndexOutOfBounds() {
    model.addLayer(new LayeredImage2D("l1", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    model.swapLayers(0, 1);
  }

  @Test
  public void testSwapSameLayer() {
    model.addLayer(new LayeredImage2D("l1", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    model.swapLayers(0, 0);
    assertEquals("l1", model.layerName());
  }

  @Test
  public void testSwapLayers() {
    model.addLayer(new LayeredImage2D("l1", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    model.addLayer(new LayeredImage2D("l2", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    assertEquals("l2", model.layerName());
    model.swapLayers(0, 1);
    assertEquals("l1", model.layerName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToggleVisibilityNoLayers() {
    model.toggleVisibility();
  }

  @Test
  public void testToggleVisibility() {
    LayeredImageModel layer = new LayeredImage2D("l1", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255));
    model.addLayer(layer);
    assertEquals(true, layer.isVisible());
    model.toggleVisibility();
    assertEquals(false, layer.isVisible());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLayerNameNoLayers() {
    model.layerName();
  }

  @Test
  public void testLayerName() {
    model.addLayer(new LayeredImage2D("name", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    assertEquals("name", model.layerName());
  }

  @Test
  public void testNumLayers() {
    assertEquals(0, model.numLayers());
    model.addLayer(new LayeredImage2D("", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    assertEquals(1, model.numLayers());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullFunction() {
    model.addLayer(new LayeredImage2D("", true,
        new Image2D(new IPixel[][]{{new RGBPixel(0,0,0)}}, 0, 255)));
    model.apply(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyNoLayers() {
    model.apply(new BlurImage());
  }

  @Test
  public void testApplyBlurFunction() {
    ImageModel image = new Image2D(
        new IPixel[][]{
            {new RGBPixel(0,0,0)}},
        0, 255);
    LayeredImageModel layer = new LayeredImage2D("", true, image);
    model.addLayer(layer);
    model.apply(new BlurImage());
    assertEquals(new BlurImage().apply(image), layer.getImage());
  }

  @Test
  public void testApplySharpenFunction() {
    ImageModel image = new Image2D(
        new IPixel[][]{
            {new RGBPixel(0,0,0)}},
        0, 255);
    LayeredImageModel layer = new LayeredImage2D("", true, image);
    model.addLayer(layer);
    model.apply(new SharpenImage());
    assertEquals(new SharpenImage().apply(image), layer.getImage());
  }

  @Test
  public void testApplySepiaFunction() {
    ImageModel image = new Image2D(
        new IPixel[][]{
            {new RGBPixel(0,0,0)}},
        0, 255);
    LayeredImageModel layer = new LayeredImage2D("", true, image);
    model.addLayer(layer);
    model.apply(new Sepia());
    assertEquals(new Sepia().apply(image), layer.getImage());
  }

  @Test
  public void testApplyGrayscaleFunction() {
    ImageModel image = new Image2D(
        new IPixel[][]{
            {new RGBPixel(0,0,0)}},
        0, 255);
    LayeredImageModel layer = new LayeredImage2D("", true, image);
    model.addLayer(layer);
    model.apply(new Monochrome());
    assertEquals(new Monochrome().apply(image), layer.getImage());
  }
}
