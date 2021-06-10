package HW05.Model;

import java.awt.Point;

public class SharpenImage extends RGBFilter {

  /**
   * Constructor for SharpenImage function object class.
   */
  public SharpenImage() {
    super(new double[][]{
        {-.125, -.125, -.125, -.125, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, .25, 1.0, .25, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, -.125, -.125, -.125, -.125}
    }, new Point(2, 2));
  }
}
