package HW05.Model;

/**
 * Class that represents a 2D image.
 */
public class Image2D implements ImageModel<IPixel> {

  private final int width;
  private final int height;
  private IPixel[][] imagePixels;

  public Image2D(int width, int height, IPixel[][] imagePixels) {
    this.width = width;
    this.height = height;
    this.imagePixels = imagePixels;
  }

  private Image2D(String filename) {
    width = 0;
    height = 0;
  }

  @Override
  public IPixel[][] getPixels() {
    return imagePixels;
  }
}
