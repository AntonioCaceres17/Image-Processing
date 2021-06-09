package HW05.Model;

import java.awt.Point;
import javax.swing.text.Position;

/**
 * This class represents a general filter to be applied to an image
 */
// NOT SURE ABOUT THE GENERIC TYPES HERE
public abstract class Filter implements IFunction {

  protected final Double[][] filter;
  protected final Point kernel;

  /**
   * Constructor for filter class.
   *
   * @param filter the filter to be applied
   * @param kernel the position representing the indices of the kernel in the given filter
   */
  public Filter(Double[][] filter, Point kernel) {
    this.filter = filter;
    this.kernel = kernel;
  }

  @Override
  public ImageModel apply(ImageModel image) {
    IPixel[][] filteredImage = new IPixel[image.height()][image.width()];

    for (int y = 0; y < image.height(); y++) {
      for (int x = 0; x < image.width(); x++) {

      }
    }

    return image.copyProperties(filteredImage);
  }

  private Double[] applyFilter(ImageModel image, int r, int c) {
    Comparable[] channels = image.getPixels()[r][c].getChannels();
    Double[] sumForChannels = new Double[channels.length];

    for (int i = 0; i < channels.length; i++) {
      for (int y = 0 - kernel.y; y < filter.length - kernel.y; y++) {
        for (int x = 0 - kernel.x; x < filter[x].length - kernel.x; x++) {
          if (y >= 0 && y < image.width() && x >= 0 && x < image.height()) {

          }
        }
      }
    }

    return sumForChannels;
  }

  protected abstract IPixel createPixel();
}
