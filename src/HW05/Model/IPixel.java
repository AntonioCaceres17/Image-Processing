package HW05.Model;

import java.awt.Color;

/**
 * This interface represents a pixel. Each pixel holds a RGB value,
 */
public interface IPixel<K extends Comparable> {

  /**
   * Gets the channels that make up the color of the pixel
   *
   * @return The specific channel making the color value attributed to this pixel.
   */
  K[] getChannels();
}
