package HW05.Model;

/**
 * This model represents the different actions that can be taken from a given image.
 * The model should be able to manipulate, store, and stack images.
 * Note: An image is reprsented by an array of pixels of type K.
 * And each pixels have a list of colors, RGB.
 */
public interface ImageModel {

  // Notes:
  // Pixels should be an array
  // We should create a version history of the images... thinking possibe
  // hashmap with the new image mapped to a version number.
  //

  /**
   * Returns a copy of the pixels making up the image.
   *
   * @return a 2D array containing the pixels of type K in the ImageModel
   */
  IPixel[][] getPixels();

  /**
   * Returns the width of the image in number of pixels.
   *
   * @return width of the image
   */
  int width();

  /**
   * Returns the height of the image in number of pixels.
   *
   * @return height of the image
   */
  int height();


  /**
   * Returns the minimum value of any of the values that make up the hue of the pixels used to form
   * the image.
   *
   * @return min value of pixels.
   */
  int minValue();

  /**
   * Returns the maximum value of any of the values that make up the hue of the pixels used to form
   * the image.
   *
   * @return max value of pixels.
   */
  int maxValue();

  /**
   * Creates a new image but uses the same image properties as the original one. This is used when
   * creating a new image with a filter or transformation.
   *
   * @param pixels the pixels of the new image
   * @return a new ModelImage with the all values remaining the same except the pixels are different
   * @throws IllegalArgumentException if the values of any of the pixels are illegal
   */
  ImageModel copyProperties(IPixel[][] pixels) throws IllegalArgumentException;
}
