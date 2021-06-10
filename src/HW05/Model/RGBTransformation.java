package HW05.Model;

public abstract class RGBTransformation extends Transformation {

  public RGBTransformation(double[][] colorMatrix) {
    super(colorMatrix);
  }

  @Override
  protected IPixel makePixel(int[] channels) {
    return new RGBPixel(channels);
  }
}
