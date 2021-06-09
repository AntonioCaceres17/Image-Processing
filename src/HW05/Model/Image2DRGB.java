package HW05.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Image2DRGB extends Image2D {

  public Image2DRGB(int width, int height, int minValue, int maxValue, IPixel[][] pixels) {
    super(width, height, minValue, maxValue, pixels);
  }
}
