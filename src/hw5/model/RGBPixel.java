package hw5.model;

/**
 * Class representing an RGB pixel in an image.
 */
public class RGBPixel implements IPixel {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructor for RGBPixel.
   *
   * @param red   amount of red in the pixel
   * @param green amount of green in the pixel
   * @param blue  amount of blue in the pixel
   */
  public RGBPixel(int red, int green, int blue) {
    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  /**
   * Constructor for RGBPixel taking in an array containing the values for red, green, and blue
   * values in the pixel.
   *
   * @param channels  the channels for the pixel containing rgb values.
   * @throws IllegalArgumentException If the channels do not contain the right number of values.
   */
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

  @Override
  public int hashCode() {
    return this.getRed() * 5 + this.blue * 3 + this.green * 1;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof RGBPixel)) {
      return false;
    }

    IPixel p = (IPixel) o;

    return this.hashCode() == p.hashCode();
  }
}
