package HW05.Model;

import java.awt.Color;

/**
 * This interface represents a pixel. Each pixel holds a RGB value,
 */
public interface IPixel {

  /**
   * Gets the RGB Color for this pixel in the form of an array of ints.
   *
   * @return The specific RGB color values attributed to this pixel.
   */
  int[] getRGB();

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
