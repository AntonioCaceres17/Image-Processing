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
         Integer[] updatedChannels = applyFilter(image, x, y);
         filteredImage[y][x] = makePixel(updatedChannels);
      }
    }

    return image.copyProperties(filteredImage);
  }

  private Integer[] applyFilter(ImageModel image, int imageX, int imageY) {
    IPixel currentPixel = image.getPixel(imageX, imageY);
    Integer[] sumForChannels = new Integer[currentPixel.getChannels().length];

    for (int i = 0; i < currentPixel.getChannels().length; i++) {
      Double sum = 0.0;
      for (int y = 0 - kernel.y; y < filter.length - kernel.y; y++) {
        for (int x = 0 - kernel.x; x < filter[x].length - kernel.x; x++) {
          if (imageY + y >= 0 && imageY + y < image.width()
              && imageX + x >= 0 && imageX + x < image.height()) {
            sum += filter[y][x] * image.getPixel(imageX + x, imageY + y).getChannels()[i];
          }
        }
      }


      // clamp values
      if (sum > image.maxValue()) {
        sum = (double) image.maxValue();
      } else if (sum < image.minValue()) {
        sum = (double) image.minValue();
      }
      sumForChannels[i] = sum.intValue();
    }

    return sumForChannels;
  }

  protected abstract IPixel makePixel(Integer[] channels);
}
