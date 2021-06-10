package HW05.Model;

/**
 * This class represents the Monochrome Tone transformation for a given picture.
 * The RGB Matrix specifications are:
 * <br> | 0.2126 0.7152 0.0722 |
 * <br> | 0.2126 0.7152 0.0722 |
 * <br> | 0.2126 0.7152 0.0722 |
 */
public class Monochrome extends RGBTransformation {

  /**
   * Constructor for the Monochrome transformation class.
   */
  public Monochrome() {
    super(new double[][]{
        {.2126, .7152, .0722},
        {.2126, .7152, .0722},
        {.2126, .7152, .0722}
    });
  }
}
