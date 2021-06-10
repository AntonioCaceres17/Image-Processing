package HW05.Model;

import org.junit.Before;
import org.junit.Test;

public class PixelTest {

  IPixel rgbPixel;
  IPixel channelPixel;
  @Before
  public void initData() {
    rgbPixel = new RGBPixel(100,100,100);

  }

  @Test
  public void testGetChannels() {

  }

}
