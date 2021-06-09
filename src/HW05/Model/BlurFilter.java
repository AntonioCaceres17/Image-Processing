package HW05.Model;

import java.awt.Point;

public class BlurFilter extends RGBFilter {

  public static BlurFilter createFilter() {
    Double[][] filter = new Double[][]{
        {.0625, .125, .0625},
        {.125, .25, .125},
        {.0625, .125, .0625}
    };
    return new BlurFilter(filter, new Point(1, 1));
  }

  /**
   * Constructor for filter class.
   *
   * @param filter the filter to be applied
   * @param kernel the position representing the indices of the kernel in the given filter
   */
  private BlurFilter(Double[][] filter, Point kernel) {
    super(filter, kernel);
  }
}
