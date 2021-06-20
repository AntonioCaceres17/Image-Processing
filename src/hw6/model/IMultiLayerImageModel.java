package hw6.model;

import hw5.model.IFunction;
import hw5.model.ImageModel;

/**
 * This interface represents methods for a multi-layer image model. A multi-layered image
 * contains layers that are all the same size and can be toggled on or off (visible/ not visible).
 * Layers can also be swapped. The model stores the current layer of the image and any changes
 * applied to an image are applied to the current layer. By default, the newest added layer is
 * always set to be the current layer when added.
 */
public interface IMultiLayerImageModel {

  /**
   * Adds an image to the MultiLayerImage as the top layer image in the model. The added image
   * is set to be the current layer and its visibility is set to on by default.
   *
   * @param image the image to add to the model
   * @throws IllegalArgumentException
   *         if the image is null, or if the image is not the same size as the other images
   *         in the model
   */
  void addLayer(LayeredImageModel image) throws IllegalArgumentException;

  /**
   * Sets the image at the given layer to be the current image. The bottom most layer is layer
   * 1 and the topmost layer is the nth layer.
   *
   * @param i the index of the new current layer
   * @throws IllegalArgumentException
   *         if the given index is out of bounds
   */
  void setCurrent(int i) throws IllegalArgumentException;

  /**
   * Toggles the visibility of the current layer.
   *
   * @throws IllegalArgumentException
   *         if there are no layers in the model.
   */
  void toggleVisibility() throws IllegalArgumentException;

  /**
   * Swaps the given layers in the image around. The layer set to be the current one does not
   * change when the images at the given layers are swapped.
   *
   * @param l1  index of layer 1
   * @param l2  index of layer 2
   * @throws IllegalArgumentException
   *         if either of the indices given are not valid layers
   */
  void swapLayers(int l1, int l2) throws IllegalArgumentException;

  /**
   * Applies the given function object to the image set as current.
   *
   * @param function  function object to be applied
   * @return the old image
   * @throws IllegalArgumentException
   */
  ImageModel apply(IFunction function) throws IllegalArgumentException;

  /**
   * Returns the image at the topmost visible layer. If there are no layers or all the layers
   * are set to invisible it throws an exception.
   *
   * @return top image
   * @throws IllegalArgumentException
   *         if there are no layers or if all the layers are set to not visible.
   */
  ImageModel getTop() throws IllegalArgumentException;

  /**
   * Returns the number of layers in the multi layered image.
   *
   * @return number of image layers
   */
  int numLayers();

  /**
   * Returns the name of the current layer.
   *
   * @return name of the current layer
   */
  String layerName();
}
