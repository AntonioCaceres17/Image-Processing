package hw5.model;

/**
 * Class representing a Monochrome transformation to an image.
 */
public class Monochrome extends RGBTransformation {

  /**
   * Constructor for Monochrome class.
   */
  public Monochrome() {
    super(new double[][]{
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}});
  }
}

