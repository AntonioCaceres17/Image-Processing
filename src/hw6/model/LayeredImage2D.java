package hw6.model;

import hw5.model.Image2D;
import hw5.model.ImageModel;

public class LayeredImage2D implements LayeredImageModel  {

  private final String name;
  private boolean isVisible;
  private final ImageModel image;

  public LayeredImage2D(String name, boolean isVisible, ImageModel image) {
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
}
