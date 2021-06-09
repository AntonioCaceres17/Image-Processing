package HW05.Model;

public class RGBPixel implements IPixel {

  private final Integer red;
  private final Integer green;
  private final Integer blue;

  public RGBPixel(Integer red, Integer green, Integer blue) {
    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  public RGBPixel(Integer[] channels) throws IllegalArgumentException {
    if (channels.length != 3) {
      throw new IllegalArgumentException("RGB Pixel must have 3 values.");
    }
    this.red = channels[0];
    this.blue = channels[1];
    this.green = channels[2];
  }

  @Override
  public Integer[] getChannels() {
    return new Integer[]{red, green, blue};
  }

  @Override
  public Integer getRed() {
    return red;
  }

  @Override
  public Integer getGreen() {
    return green;
  }

  @Override
  public Integer getBlue() {
    return blue;
  }
}
