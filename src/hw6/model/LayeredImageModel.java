package hw6.model;

import hw5.model.IFunction;
import hw5.model.ImageModel;

/**
 * Interface representing an image model that can be part of a multi-layered image.
 */
public interface LayeredImageModel {

  /**
   * Returns a boolean value representing whether the layer is visible or not. True = visible,
   * False = not visible.
   *
   * @return whether the layer is visible or not.
   */
  boolean isVisible();

  /**
   * Returns a meaningful, user-defined name for the layer of the image.
   *
   * @return name of the layered image
   */
  String name();

  /**
   * Toggles the image's visibility.
   */
  void toggleVisibility();

  /**
   * Returns the wrapped ImageModel.
   *
   * @return image in the LayeredImageModel
   */
  ImageModel getImage();

  /**
   * Applies the given IFunction to the current image and returns the old image.
   *
   * @param function function to apply to the image
   * @return the old image
   */
  LayeredImageModel apply(IFunction function);
}
