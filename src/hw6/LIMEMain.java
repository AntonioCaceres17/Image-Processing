package hw6;

import hw6.controller.ImageCommand;
import hw6.controller.ImageController;
import hw6.model.IMultiLayerImageModel;


import hw6.model.MultiLayerImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * This is the main method for the ImageController class
 * It is meant to be used for user input and output through the system's console.
 */
public class LIMEMain {

  public static void main(String[] args) {
    IMultiLayerImageModel model = new MultiLayerImage();
    try {
      Readable rd = new FileReader("res/script1.txt");
      ImageController controller = new ImageCommand(model, rd, System.out);
      controller.read();
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }

    IMultiLayerImageModel model2 = new MultiLayerImage();
    try {
      Readable rd = new FileReader("res/script2.txt");
      ImageController controller = new ImageCommand(model2, rd, System.out);
      controller.read();
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found.");
    }
  }
}
