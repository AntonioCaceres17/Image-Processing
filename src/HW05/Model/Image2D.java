package HW05.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that represents a 2D image.
 */
public abstract class Image2D implements ImageModel {

  private final int width;
  private final int height;
  private final int minValue;
  private final int maxValue;
  private final IPixel[][] pixels;

  protected Image2D(int width, int height, int minValue, int maxValue, IPixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.pixels = pixels;
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }

  @Override
  public int minValue() {
    return minValue;
  }

  @Override
  public int maxValue() {
    return maxValue;
  }

  @Override
  public IPixel[][] getPixels() {
    return pixels;
  }

  @Override
  public ImageModel copyProperties(IPixel[][] pixels)
      throws IllegalArgumentException {
    try {
      for (IPixel[] row: pixels) {
        for (IPixel pixel: row) {
          for (Comparable channel: pixel.getChannels()) {
            try {
              if (channel.compareTo(minValue()) <= 0 || channel.compareTo(maxValue()) >= 0) {
                throw new IllegalArgumentException("Invalid pixel: " + channel.toString() + ".");
              }
            } catch (ClassCastException cce) {
              throw new IllegalArgumentException("Pixel types cannot be compared.");
            }
          }
        }
      }
    } catch (NullPointerException npe) {
      throw new IllegalArgumentException("Null pixel.");
    }

    return new Image2DRGB(width(), height(), minValue(), maxValue(), pixels);
  }
}
