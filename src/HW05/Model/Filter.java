package HW05.Model;

import java.awt.Point;
import javax.swing.text.Position;

/**
 * This class represents a general filter to be applied to an image
 */
// NOT SURE ABOUT THE GENERIC TYPES HERE
public abstract class Filter implements IFunction {

  protected final double[][] filter;
  protected final Point kernel;

  /**
   * Constructor for filter class.
   *
   * @param filter the filter to be applied
   * @param kernel the position representing the indices of the kernel in the given filter
   */
  public Filter(double[][] filter, Point kernel) {
    this.filter = filter;
    this.kernel = kernel;
  }

  @Override
  public ImageModel apply(ImageModel image) throws IllegalArgumentException {
    if (image == null || image.getPixels().length == 0) {
      throw new IllegalArgumentException("Image cannot be empty!");
    }

    IPixel[][] filteredImage = new IPixel[image.height()][image.width()];

    for (int y = 0; y < image.height(); y++) {
      for (int x = 0; x < image.width(); x++) {
         filteredImage[y][x] = applyFilter(image, x, y);
      }
    }

    return image.copyProperties(filteredImage);
  }

  private IPixel applyFilter(ImageModel image, int imageX, int imageY) {
    IPixel currentPixel = image.getPixel(imageX, imageY);
    int[] sumForChannels = new int[currentPixel.getChannels().length];

    for (int i = 0; i < currentPixel.getChannels().length; i++) {
      double sum = 0.0;
      for (int y = 0 - kernel.y; y < filter.length - kernel.y; y++) {
        for (int x = 0 - kernel.x; x < filter[y + kernel.y].length - kernel.x; x++) {
          if (imageY + y >= 0 && imageY + y < image.height()
              && imageX + x >= 0 && imageX + x < image.width()) {
            sum += filter[kernel.y + y][kernel.x + x] *
                image.getPixel(imageX + x, imageY + y).getChannels()[i];
          }
        }
      }

      // clamp values
      if (sum > image.maxValue()) {
        sum = image.maxValue();
      } else if (sum < image.minValue()) {
        sum = image.minValue();
      }

      sumForChannels[i] = (int) sum;
      }

    return makePixel(sumForChannels);
  }

  protected abstract IPixel makePixel(int[] channels);
}
