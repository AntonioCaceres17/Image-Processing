package hw7.model;

import hw5.model.IFunction;
import hw6.model.MultiLayerImage;
import hw6.model.ResizableLayeredImageModel;

public class ResizableMultiLayerImage extends MultiLayerImage implements
    ResizableLayeredImageModel {

  @Override
  public void applyToAll(IFunction function) throws IllegalArgumentException {
    if (function == null) {
      throw new IllegalArgumentException("IFunction object is null.");
    }

    for (int i = 0; i < numLayers(); i++) {
      setCurrent(i);
      apply(function);
    }
  }
}
