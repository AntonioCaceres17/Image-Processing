package hw5.model;

import java.awt.Point;

/**
 * Sharpen Image function object class that represents a filter used to sharpen an image.
 */
public class SharpenImage extends RGBFilter {

  /**
   * Constructor for SharpenImage function object class.
   */
  public SharpenImage() {
    super(new double[][]{
        {-.125, -.125, -.125, -.125, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, .25, 1.0, .25, -.125},
        {-.125, .25, .25, .25, -.125},
        {-.125, -.125, -.125, -.125, -.125}
    }, new Point(2, 2));
  }
}
