package HW05.Model;

public class RGBPixel implements IPixel {

  private final int red;
  private final int green;
  private final int blue;

  public RGBPixel(int red, int green, int blue) {
    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  public RGBPixel(int[] channels) throws IllegalArgumentException {
    if (channels.length != 3) {
      throw new IllegalArgumentException("RGB Pixel must have 3 values.");
    }
    this.red = channels[0];
    this.green = channels[1];
    this.blue = channels[2];
  }

  @Override
  public int[] getChannels() {
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
