package HW05.Model;

public class PixelRGB implements IPixelRGB {

  private final Integer red;
  private final Integer green;
  private final Integer blue;

  public PixelRGB(Integer red, Integer green, Integer blue) {
    this.red = red;
    this.blue = blue;
    this.green = green;
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
