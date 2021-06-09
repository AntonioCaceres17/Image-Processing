package HW05.Model;

/**
 * This class represents a transformation to be applied to an image.
 */
public abstract class Transformation implements IFunction {

  protected int[][] colorMatrix;

  protected Transformation() {
    this.colorMatrix = colorMatrix;
  }

  /**
   * This applies the matrix multiplication needed for the color transformation and flattens it to
   * become a 1D array.
   *
   * @param toMultiply the Pixel to be multiplied with this matrix
   * @return the flatten version of the multiplied matrices.
   */
  protected IPixel multiply(IPixel toMultiply) {
    int[]colors = {toMultiply.getRed(), toMultiply.getGreen() , toMultiply.getBlue()};
    int[]finalColor = new int[3];
    for(int i = 0; i < colorMatrix.length; i ++ ) {
      for(int j = 0; j < colors.length; j++) {
        for(int k = 0; k < colorMatrix[i].length; k++) {
          finalColor[j] += colorMatrix[k][j] * colors[j];
        }
      }
    }
    return new RGBPixel(finalColor[0], finalColor[1], finalColor[2]);
  }

  public ImageModel apply(ImageModel image) throws IllegalArgumentException {
    if (image == null || image.getPixels().length == 0) {
      throw new IllegalArgumentException("Image cannot be empty!");
    }
    IPixel[][] pixels = image.getPixels();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j] = multiply(pixels[i][j]);
      }
    }
    return image.copyProperties(pixels);
  }
}
