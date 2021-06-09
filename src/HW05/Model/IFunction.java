package HW05.Model;

/**
 * Interface representing an image function.
 */
public interface IFunction<K> {

  /**
   * Applies this function object to the given image.
   */
  K apply(K image);
}
