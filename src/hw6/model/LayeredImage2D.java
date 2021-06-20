package hw6.model;

import hw5.model.IFunction;
import hw5.model.ImageModel;

/**
 * This class represents an image in a multi-layered image model. This class is an adapter
 * and adds the functionality of giving the image its own name and the ability to toggle its
 * visibility.
 */
public class LayeredImage2D implements LayeredImageModel  {

  private final String name;
  private boolean isVisible;
  private ImageModel image;

  /**
   * Constrcutor for LayeredImage2D class.
   *
   * @param name      the descriptive name of the layer
   * @param isVisible whether the layer is visible or not
   * @param image     the image the layer contains
   */
  public LayeredImage2D(String name, boolean isVisible, ImageModel image) {
    if (name == null) {
      throw new IllegalArgumentException("Image name is null.");
    }
    if (image == null) {
      throw new IllegalArgumentException("Image is null.");
    }
    this.name = name;
    this.isVisible = isVisible;
    this.image = image;
  }

  @Override
  public boolean isVisible() {
    return isVisible;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public void toggleVisibility() {
    isVisible = !isVisible;
  }

  @Override
  public ImageModel getImage() {
    return image.copyProperties(image.getPixels());
  }

  @Override
  public LayeredImageModel apply(IFunction function) {
    // THIS MIGHT ALIAS WILL HAVE TO TEST THIS
    LayeredImageModel oldImage = new LayeredImage2D(this.name, this.isVisible, this.image);
    this.image = function.apply(this.image);
    return oldImage;
  }
}
