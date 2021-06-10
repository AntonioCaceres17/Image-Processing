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
    IPixel[][] pixels = image.getPixels();
    // You did not have this before
    IPixel[][] transformedImage = new IPixel[image.height()][image.width()];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        transformedImage[i][j] = multiply(image.getPixel(j, i));
      }
    }
    return image.copyProperties(transformedImage);
  }
  /**
   * This applies the matrix multiplication needed for the color transformation and flattens it to
   * become a 1D array.
   *
   * @param toMultiply the Pixel to be multiplied with this matrix
   * @return a modified version of the given Pixel
   */
  protected IPixel multiply(IPixel toMultiply) {
    int[]colors = {toMultiply.getRed(), toMultiply.getGreen() , toMultiply.getBlue()};
    double[]finalColor = new double[3];
    for(int i = 0; i < colorMatrix.length; i ++) {
      for(int j = 0; j < colors.length; j++) {
        for(int k = 0; k < colorMatrix[i].length; k++) {
          finalColor[j] += colorMatrix[k][j] * colors[j];
          if(finalColor[j] > 255) {
            finalColor[j] = 255;
          }
        }
      }
    }
    return new RGBPixel(  (int) finalColor[0], (int)finalColor[1], (int) finalColor[2]);
  }
}
