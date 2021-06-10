package HW05.Model;

import java.awt.Point;

public class SharpenImage extends RGBFilter {

  public static SharpenImage createFilter() {
    double[][] filter = new double[][]{
        {-.125, -.125, -.125, -.125, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, .25, 1.0, .25, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, -.125, -.125, -.125, -.125}
    };
    return new SharpenImage(filter, new Point(1, 1));
  }

  /**
   * Constructor for filter class.
   *
   * @param filter the filter to be applied
   * @param kernel the position representing the indices of the kernel in the given filter
   */
  private SharpenImage(double[][] filter, Point kernel) {
    super(filter, kernel);
  }
}
