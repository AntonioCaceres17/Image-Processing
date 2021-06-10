package HW05.Model;

import java.awt.Point;

public class BlurImage extends RGBFilter {

  /**
   * Constructor BlurImage function object class.
   */
  public BlurImage() {
    super(new double[][]{
        {.0625, .125, .0625},
        {.125, .25, .125},
        {.0625, .125, .0625}
    }, new Point(1,1));
  }
}
