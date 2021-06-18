package hw6.model;

import hw5.model.IFunction;
import hw5.model.ImageModel;
import java.util.Stack;

public class MultiLayerImage implements IMultiLayerImageModel {

  private Stack<LayeredImageModel> layers;
  private int currentLayer;

  public MultiLayerImage() {
    this.layers = new Stack<>();
    this.currentLayer = -1;
  }

  @Override
  public void addLayer(LayeredImageModel layer) throws IllegalArgumentException {
    //TODO: figure out how we are going to deal with names since we will only be taking in images from JPEG, PNG, PPM
    this.layers.add(layer);
  }

  @Override
  public void setCurrent(int i) throws IllegalArgumentException {
    if (i < 1 || i > layers.size()) {
      throw new IllegalArgumentException("Invalid layer index: " + i);
    }
    currentLayer = i - 1;
  }

  @Override
  public void toggleVisibility() throws IllegalArgumentException {
    if (layers.size() == 0) {
      throw new IllegalArgumentException("The image has no current layer.");
    }
    this.layers.get(currentLayer).toggleVisibility();
  }

  @Override
  public void swapLayers(int l1, int l2) throws IllegalArgumentException {
    if (l1 < 1 || l1 > layers.size()) {
      throw new IllegalArgumentException("Invalid layer index: " + l1);
    }
    if ( l2 < 1 || l2 > layers.size()) {
      throw new IllegalArgumentException("Invalid layer index: " + l2);
    }
    LayeredImageModel temp = layers.set(l1, layers.get(l2));
    layers.set(l2, temp);
  }

  // This currently sets the layer edited to automatically be visible
  @Override
  public ImageModel apply(IFunction function) throws IllegalArgumentException {
    if (layers.size() == 0) {
      throw new IllegalArgumentException("The image has no current layer.");
    }
    LayeredImageModel newImage = layers.get(currentLayer).apply(function);
    return layers.set(currentLayer, newImage).getImage();
  }

  @Override
  public void exportImage() throws IllegalArgumentException {

  }
}
