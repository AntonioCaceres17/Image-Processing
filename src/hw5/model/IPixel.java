package hw5.model;

/**
 * This interface represents a pixel. Each pixel holds a RGB value,
 */
public interface IPixel {

  /**
   * Gets the channels that make up the color of the pixel.
   *
   * @return The specific channel making the color value attributed to this pixel.
   */
  int[] getChannels();

  /**
   * Returns the R in RGB which is the level of red hue in the pixel's color.
   *
   * @return amount of red hue in this pixel's RGB value.
   */
  int getRed();

  /**
   * Returns the G in RGB which is the level of green hue in the pixel's color.
   *
   * @return amount of green hue in this pixel's RGB value.
   */
  int getGreen();

  /**
   * Returns the B in RGB which is the level of blue hue in the pixel's color.
   *
   * @return amount of blue hue in this pixel's RGB value.
   */
  int getBlue();
}
