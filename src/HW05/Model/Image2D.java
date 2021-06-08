package HW05.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that represents a 2D image.
 */
public class Image2D implements ImageModel<IPixel> {

  private final int width;
  private final int height;
  private final int maxValue;
  private IPixel[][] imagePixels;

  private Image2D(int width, int height, int maxValue, IPixel[][] imagePixels) {
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.imagePixels = imagePixels;
  }

  public static class ImageReader {

    public static ImageModel<IPixel> createImage(String filename) throws IllegalArgumentException {
      Scanner sc;

      try {
        sc = new Scanner(new FileInputStream(filename));
      }
      catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File "+filename+ " not found!");
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
          imagePixels[height][width] = new Pixel(r, g, b);
        }
      }

      return new Image2D(height, width, maxValue, imagePixels);
    }
  }

  @Override
  public IPixel[][] getPixels() {
    return imagePixels;
  }

  @Override
  public int width() {
    return this.width;
  }

  @Override
  public int height() {
    return this.height;
  }

  @Override
  public int maxValue() {
    return this.maxValue;
  }
}
