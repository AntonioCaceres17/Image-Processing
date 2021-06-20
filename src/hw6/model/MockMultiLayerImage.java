package hw6.model;

import hw5.model.IFunction;
import hw5.model.ImageModel;
import java.io.IOException;

/**
 * Mock class for MultiLayerImage. This class is used to test that the controller passes in the
 * correct values to the model's functions when each method is called through the controller.
 */
public class MockMultiLayerImage implements IMultiLayerImageModel {

  private final Appendable ap;

  /**
   * Constructor for the MockMultiLayerImage class.
   *
   * @param ap  Appendable object to write to
   */
  public MockMultiLayerImage(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void addLayer(LayeredImageModel image) throws IllegalArgumentException {
    try {
      ap.append("Add layer: " + image.name() + "\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
  }

  @Override
  public void setCurrent(int i) throws IllegalArgumentException {
    try {
      ap.append("set current to: " + i + "\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
  }

  @Override
  public void toggleVisibility() throws IllegalArgumentException {
    try {
      ap.append("toggle visibility\n");
    } catch (IOException e) {
    throw new IllegalArgumentException("Could not write to appendable.");
    }
  }

  @Override
  public void swapLayers(int l1, int l2) throws IllegalArgumentException {
    try {
      ap.append("swap layers " + l1 + " and " + l2 + "\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
  }

  @Override
  public ImageModel apply(IFunction function) throws IllegalArgumentException {
    try {
      ap.append("apply function: " + function.getClass().getSimpleName() + "\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
    return null;
  }

  @Override
  public ImageModel getTop() throws IllegalArgumentException {
    try {
      ap.append("return top layer.");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
    return null;
  }

  @Override
  public int numLayers() {
    try {
      ap.append("num layers\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }
    return -1;
  }

  @Override
  public String layerName() {
    try {
      ap.append("layer name\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write to appendable.");
    }    return null;
  }
}
