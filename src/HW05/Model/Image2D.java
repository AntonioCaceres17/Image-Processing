package HW05.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that represents a 2D image.
 */
public class Image2D implements ImageModel {

  private final int width;
  private final int height;
  private final int minValue;
  private final int maxValue;
  private final IPixel[][] pixels;

  public Image2D(int width, int height, int minValue, int maxValue, IPixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.pixels = pixels;
  }

  public static class ImageReader {

    /**
     * This class takes in a PPM file name, searches for it, and stores it into the appropriate
     * ImageModel class.
     * @param filename the name of the file to be scanned.
     * @return an ImageModel version of the file, ready to be modified
     * @throws IllegalArgumentException if the file is invalid
     */
    public static ImageModel createImageFromPPM(String filename)
        throws IllegalArgumentException {
      Scanner sc;

      try {
        sc = new Scanner(new FileInputStream(filename));
      }
      catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File "+filename+ " not found!");
      }
      catch (NullPointerException e) {
        throw new IllegalArgumentException("File name cannot be null!");
      }
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0)!='#') {
          builder.append(s+System.lineSeparator());
        }
      }

      //now set up the scanner to read from the string we just built
      sc = new Scanner(builder.toString());

      String token;

      token = sc.next();
      if (!token.equals("P3")) {
        throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
      }
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxValue = sc.nextInt();
      //System.out.println("Maximum value of a color in this file (usually 256): "+maxValue);

      // have to make sure height/width is not backwards
      IPixel[][] imagePixels = new IPixel[height][width];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          imagePixels[i][j] = new RGBPixel(r, g, b);
        }
      }

      return new Image2D(width, height, 0, maxValue,  imagePixels);
    }
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
  public IPixel getPixel(int x, int y) {
    return pixels[y][x];
  }

  @Override
  public ImageModel copyProperties(IPixel[][] pixels)
      throws IllegalArgumentException {
    try {
      for (IPixel[] row: pixels) {
        for (IPixel pixel: row) {
          for (Integer channel: pixel.getChannels()) {
            try {
              if (channel < 0 || channel > maxValue()) {
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

    return new Image2D(width(), height(), minValue(), maxValue(), pixels);
  }
}
