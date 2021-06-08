package HW05.Model;

/**
 * This model represents the different actions that can be taken from a given image.
 * The model should be able to manipulate, store, and stack images.
 * Note: An image is reprsented by an array of pixels of type K.
 * And each pixels have a list of colors, RGB.
 */
public interface ImageModel<K> {

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
  K[][] getPixels();
}
