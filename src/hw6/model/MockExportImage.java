package hw6.model;

import hw5.model.Image2D;
import hw5.model.Image2D.ImageReader;
import hw5.model.ImageModel;
import java.io.IOException;

/**
 * The mock export image class to make sure each image is written and expoted correctly.
 */
public class MockExportImage implements IExportImage {

  private final Appendable ap;

  public MockExportImage(Appendable ap) {
    this.ap = ap;
  }


  @Override
  public void makePPM(String filename) throws IllegalArgumentException, IOException {
      ap.append("Create PPM of name");

  }

  @Override
  public void makeImage(String filename, String imageFormat) throws IllegalArgumentException {

  }
}
