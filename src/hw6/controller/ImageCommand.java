package hw6.controller;

import hw5.model.ImageModel;
import hw6.model.MultiLayerImage;
import java.util.Objects;
import java.util.Scanner;


/** T
 * This class is able to take in batch commands to either load, edit, or save images
 * This is able to support both interactive or file-based scripting
 */
public class ImageCommand implements ImageController{

private MultiLayerImage model;
private Readable rd;
private Appendable ap;


public ImageCommand(MultiLayerImage model, Readable rd, Appendable ap)
    throws IllegalArgumentException {
  Objects.requireNonNull(model, "Model cannot be null!");
  Objects.requireNonNull(rd, "Readable cannot be null!");
  Objects.requireNonNull(ap, "Appendable cannot be null!");

  this.model = model;
  this.rd = rd;
  this.ap = ap;

}

private void readNewMessage()
    throws IllegalStateException {
  Scanner scan = new Scanner(rd);
  if(!scan.hasNext()) {
    throw new IllegalStateException("Scanner is over.");
  }
  else {
    if(scan.next().toLowerCase().equals("import")) {
      //expect the file
    }
  }
}
}
