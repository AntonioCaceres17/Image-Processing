package hw6.model;

import hw5.model.IFunction;
import hw5.model.ImageModel;
import java.io.IOException;

public class MockMultiLayerImage implements IMultiLayerImageModel {

  private final Appendable ap;

  private MockMultiLayerImage(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void addLayer(LayeredImageModel image) throws IllegalArgumentException {
    try {
      ap.append("Add layer: " + image.name());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
  }

  @Override
  public void setCurrent(int i) throws IllegalArgumentException {
    try {
      ap.append("set current to: " + i);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
  }

  @Override
  public void toggleVisibility() throws IllegalArgumentException {
    try {
      ap.append("toggle visibility");
    } catch (IOException e) {
    throw new IllegalArgumentException("Could not write to appendable.");
    }
  }

  @Override
  public void swapLayers(int l1, int l2) throws IllegalArgumentException {
    try {
      ap.append("swapped layers " + l1 + " and " + l2);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
  }

  @Override
  public ImageModel apply(IFunction function) throws IllegalArgumentException {
    try {
      ap.append("apply function: " + function.getClass().getName());
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
    return null;
  }

  @Override
  public int numLayers() {
    try {
      ap.append("num layers");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
    return -1;
  }

  @Override
  public String layerName() {
    try {
      ap.append("layer name");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }    return null;
  }
}
