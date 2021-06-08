package HW05.Model;

public class Pixel implements IPixel {

  private final int red;
  private final int green;
  private final int blue;

  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  @Override
  public int[] getRGB() {
    return new int[]{red, green, blue};
  }

  @Override
  public int getRed() {
    return red;
  }

  @Override
  public int getGreen() {
    return green;
  }

  @Override
  public int getBlue() {
    return blue;
  }
}
