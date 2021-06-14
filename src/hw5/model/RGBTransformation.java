package hw5.model;

/**
 * Class for transforming an image made with RGB pixels.
 */
public abstract class RGBTransformation extends Transformation {

  /**
   * Constructor for RGBTransformation class.
   *
   * @param colorMatrix The matric used to perform the transformation.
   */
  public RGBTransformation(double[][] colorMatrix) {
    super(colorMatrix);
  }

  @Override
  protected IPixel makePixel(int[] channels) {
    return new RGBPixel(channels);
  }
}
