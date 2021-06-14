package hw5.model;

/**
 * Interface representing an image function.
 */
public interface IFunction {

  /**
   * Applies this function object to the given image.
   * @param image The given image to be modified
   * @return the modified version of the image
   * @throws IllegalArgumentException if the image is empty or null.
   */
  ImageModel apply(ImageModel image) throws IllegalArgumentException;

}
