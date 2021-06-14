package hw5.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test class for RGBPixel.
 */
public class PixelTest {

  private IPixel rgbPixel;

  @Before
  public void initData() {
    rgbPixel = new RGBPixel(100,120,140);
  }

  @Test
  public void testGetChannels() {
    int[] channels = rgbPixel.getChannels();
    assertEquals(channels[0], 100);
    assertEquals(channels[1], 120);
    assertEquals(channels[2], 140);
  }

  @Test
  public void testGetRed() {
    assertEquals(100, rgbPixel.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(120, rgbPixel.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(140, rgbPixel.getBlue());
  }
}
