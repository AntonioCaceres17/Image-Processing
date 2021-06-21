package hw6;

import hw6.controller.ImageCommand;
import hw6.controller.ImageController;
import hw6.model.IMultiLayerImageModel;


import hw6.model.MultiLayerImage;
import java.io.InputStreamReader;

/**
 * This is the main method for the ImageController class
 * It is meant to be used for user input and output through the system's console.
 */
public class LIMEMain {

  public static void main(String[] args) {
    IMultiLayerImageModel model = new MultiLayerImage();
    InputStreamReader rd = new InputStreamReader(System.in);
    ImageController controller = new ImageCommand(model, rd, System.out);

    }
  }

