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
    if (layer == null) {
      throw new IllegalArgumentException("Cannot add null layer.");
    }
    //TODO: figure out how we are going to deal with names since we will only be taking in images from JPEG, PNG, PPM
    if (layers.isEmpty()
        || layer.getImage().width() == layers.peek().getImage().width()
        && layer.getImage().height() == layers.peek().getImage().height()) {
      this.layers.push(layer);
      this.currentLayer++;
    } else {
      throw new IllegalArgumentException("Image layers must have the same size.");
    }
  }

  @Override
  public void setCurrent(int i) throws IllegalArgumentException {
    if (i < 0 || i >= layers.size()) {
      throw new IllegalArgumentException("Invalid layer index: " + i);
    }
    currentLayer = i;
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
    if (l1 < 0 || l1 >= layers.size()) {
      throw new IllegalArgumentException("Invalid layer index: " + l1);
    }
    if ( l2 < 0 || l2 >= layers.size()) {
      throw new IllegalArgumentException("Invalid layer index: " + l2);
    }
    LayeredImageModel temp = layers.set(l1, layers.get(l2));
    layers.set(l2, temp);
  }

  // This currently sets the layer edited to automatically be visible
  @Override
  public ImageModel apply(IFunction function) throws IllegalArgumentException {
    if (function == null) {
      throw new IllegalArgumentException("IFunction object cannot be null.");
    }
    if (layers.size() == 0) {
      throw new IllegalArgumentException("The image has no current layer.");
    }
    LayeredImageModel newImage = layers.get(currentLayer).apply(function);
    return layers.set(currentLayer, newImage).getImage();
  }

  public int numLayers() {
    return layers.size();
  }

  @Override
  public String layerName() {
    if (layers.isEmpty()) {
      throw new IllegalArgumentException("Image has no layers.");
    }
    return this.layers.get(currentLayer).name();
  }
}
