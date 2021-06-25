package hw7.model;

import hw5.model.IFunction;
import hw5.model.IPixel;
import hw5.model.Image2D;
import hw5.model.ImageModel;
import hw5.model.RGBPixel;

/**
 * This class is a function object that is used to downsize an ImageModel. This class takes in
 * a double between 1 and 0 which are values representing how much to downsize the model by with
 * 1 keeping the image the same size and .01 making it extremely small.
 */
public class DownSize implements IFunction {

  private final double scaleW;
  private final double scaleH;

  /**
   * Constructor for DownSize class.
   *
   * @param scaleW  new width scaled to this number
   * @param scaleH  new height scaled to this number
   * @throws IllegalArgumentException
   *         if either the scaled width or height is less than or equal to 0 or greater than 1
   */
  public DownSize(double scaleW, double scaleH) throws IllegalArgumentException {
    if (scaleW <= 0 || scaleW > 1 || scaleH <= 0 || scaleH > 1) {
      throw new IllegalArgumentException("Image cannot be scaled down by a height or width "
          + "less than 0 or greater than 1.");
    }
    this.scaleW = scaleW;
    this.scaleH = scaleH;
  }

  @Override
  public ImageModel apply(ImageModel image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image is null.");
    }

    IPixel[][] downSizedPixels =
        new IPixel[(int) (image.height() * scaleH)][(int) (image.width() * scaleW)];

    for (int y = 0; y < downSizedPixels.length; y++) {
      for (int x = 0; x < downSizedPixels[y].length; x++) {
        downSizedPixels[y][x] = new RGBPixel(calculatePixelColors(
            image,
            x * image.width() / downSizedPixels[y].length,
            y * image.height() / downSizedPixels.length));
      }
    }

    return new Image2D(downSizedPixels, 0, 255);
  }

  private int[] calculatePixelColors(ImageModel image, double x, double y) {
    int[] colorChannels = new int[3];

    for (int i = 0; i < 3; i++) {
      double m = image.getPixel((int) Math.ceil(x), (int) Math.floor(y)).getChannels()[i] *
          (x - Math.floor(x))
          + image.getPixel((int) Math.floor(x), (int) Math.floor(y)).getChannels()[i] *
          (Math.round(x + 0.5) - x);
      double n = image.getPixel((int) Math.ceil(x), (int) Math.ceil(y)).getChannels()[i] *
          (x - Math.floor(x))
          + image.getPixel((int) Math.floor(x), (int) Math.ceil(y)).getChannels()[i] *
          (Math.round(x + 0.5) - x);

      int downSizedColor = (int) Math.round(n * (y - Math.floor(y))
          + m * (Math.round(y + 0.5) - y));
      colorChannels[i] = downSizedColor;
    }

    return colorChannels;
  }
}
