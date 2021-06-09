package HW05.Model;

import java.awt.Point;

public abstract class RGBFilter extends Filter {

  /**
   * Constructor for filter class.
   *
   * @param filter the filter to be applied
   * @param kernel the position representing the indices of the kernel in the given filter
   */
  public RGBFilter(Double[][] filter, Point kernel) {
    super(filter, kernel);
  }

  @Override
  protected IPixel makePixel(Integer[] channels) {
    return new RGBPixel(channels);
  }
}
