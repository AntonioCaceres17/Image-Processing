package HW05.Model;

/**
 * This class represents a transformation to be applied to an image.
 */
public abstract class Transformation implements IFunction {

  protected final  double[][] colorMatrix;

  public Transformation(double[][] colorMatrix) {
    this.colorMatrix = colorMatrix;
  }

  public ImageModel apply(ImageModel image) throws IllegalArgumentException {
    if (image == null || image.getPixels().length == 0) {
      throw new IllegalArgumentException("Image cannot be empty!");
    }

    IPixel[][] transformedPixels = new IPixel[image.height()][image.width()];
    for (int y = 0; y < image.height(); y++) {
      for (int x = 0; x < image.width(); x++) {
        transformedPixels[y][x] = applyTransformation(image.getPixel(x, y),
            image.minPixelValue(), image.maxPixelValue());
      }
    }
    return image.copyProperties(transformedPixels);
  }
  /**
   * This applies the matrix multiplication needed for the color transformation and flattens it to
   * become a 1D array.
   *
   * @param curPixel the Pixel to be multiplied with this matrix
   * @return a modified version of the given Pixel
   */
  private IPixel applyTransformation(IPixel curPixel, int minValue, int maxValue) {
    int[]channels = curPixel.getChannels();
    int[]finalColor = new int[channels.length];

    for (int i = 0; i < channels.length; i++) {
      double transformedColor = 0;
      for (int j = 0; j < colorMatrix[i].length; j++) {
        transformedColor += channels[j] * colorMatrix[i][j];
      }
      if (transformedColor > maxValue) {
        transformedColor = maxValue;
      } else if (transformedColor < minValue) {
        transformedColor = minValue;
      }
      finalColor[i] = (int) transformedColor;
    }

    return makePixel(finalColor);
  }

  protected abstract IPixel makePixel(int[] channels);
}
