package hw6.model;

import hw5.model.IFunction;
import hw5.model.Image2D;
import hw5.model.ImageModel;

public class LayeredImage2D implements LayeredImageModel  {

  private final String name;
  private boolean isVisible;
  private ImageModel image;

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
    return false;
  }

  @Override
  public String name() {
    return null;
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
