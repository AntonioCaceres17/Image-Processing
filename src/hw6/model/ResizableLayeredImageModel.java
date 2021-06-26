package hw6.model;

import hw5.model.IFunction;

public interface ResizableLayeredImageModel {

  /**
   * Applies the given function to all the images. After applying the function to all the layers
   * the new current layer is set to the top one.
   *
   * @param function  the function to apply
   * @throws IllegalArgumentException if the function is null
   */
  void applyToAll(IFunction function) throws IllegalArgumentException;
}
