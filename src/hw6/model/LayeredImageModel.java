package hw6.model;

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
}
