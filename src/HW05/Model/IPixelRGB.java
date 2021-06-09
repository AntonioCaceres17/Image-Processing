package HW05.Model;

public interface IPixelRGB extends IPixel<Integer> {

  /**
   * Returns the R in RGB which is the level of red hue in the pixel's color.
   *
   * @return amount of red hue in this pixel's RGB value.
   */
  Integer getRed();

  /**
   * Returns the G in RGB which is the level of green hue in the pixel's color.
   *
   * @return amount of green hue in this pixel's RGB value.
   */
  Integer getGreen();

  /**
   * Returns the B in RGB which is the level of blue hue in the pixel's color.
   *
   * @return amount of blue hue in this pixel's RGB value.
   */
  Integer getBlue();
}
