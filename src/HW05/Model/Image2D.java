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
  private final int minPixelValue;
  private final int maxPixelValue;
  private final IPixel[][] pixels;

  public Image2D(IPixel[][] pixels, int minPixelValue, int maxPixelValue) {
    if (pixels == null || pixels.length == 0) {
      throw new IllegalArgumentException("image is empty or null.");
    }
    this.pixels = pixels;
    this.minPixelValue = minPixelValue;
    this.maxPixelValue = maxPixelValue;

    testWidth();
    this.height = pixels.length;
    this.width = pixels[0].length;
  }

  // throws an error if the image width is invalid.
  private void testWidth() throws IllegalArgumentException {
    if (pixels[0] == null || pixels[0].length == 0) {
      throw new IllegalArgumentException("Image width is 0.");
    }
    int widthTest = pixels[0].length;
    for (int i = 0; i < pixels.length; i++) {
      if (pixels[i].length != widthTest) {
        throw new IllegalArgumentException("Image width is not consistent.");
      }
    }
  }

  // throws an error if any pixel has an illegal value.
  private void testPixelValues() throws IllegalArgumentException {
    for (IPixel[] row : pixels) {
      for (IPixel pixel : row) {
        for (int channel : pixel.getChannels()) {
          if (channel < minPixelValue || channel > maxPixelValue) {
            throw new IllegalArgumentException("Invalid pixel found containing value: " + channel);
          }
        }
      }
    }
  }

  private Image2D(int width, int height, int minPixelValue, int maxPixelValue, IPixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.minPixelValue = minPixelValue;
    this.maxPixelValue = maxPixelValue;
    this.pixels = pixels;
  }

  public static class ImageReader {

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
  public int minPixelValue() {
    return minPixelValue;
  }

  @Override
  public int maxPixelValue() {
    return maxPixelValue;
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
              if (channel < 0 || channel > maxPixelValue()) {
                throw new IllegalArgumentException(
                    "Invalid pixel found containing value: " + channel);
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

    return new Image2D(width(), height(), minPixelValue(), maxPixelValue(), pixels);
  }
}
