package HW05;

import HW05.Model.IPixel;
import HW05.Model.ImageModel;
import java.io.File;
import java.io.FileWriter;


public class exportImage {

  ImageModel image;

  public exportImage(ImageModel image) {
    this.image = image;
  }

  public void makePPM(String filename) {
    // possible throw if the file is empty......
    IPixel[][] pixels = image.getPixels();
    try {
      File filePPM = new File(filename);
      if (!filePPM.exists()) {
        filePPM.createNewFile();
      }
      FileWriter writer = new FileWriter(filePPM);
      writer.write("P3");
      writer.write(System.getProperty("line.separator"));
      writer.write(image.height() + " " + image.width());
      writer.write(image.maxValue());
      for (int i = 0; i < pixels.length; i++) {
        for (int j = 0; j < pixels[i].length; j++) {
          writer.write(pixels[i][j].getRed() + " "
              + pixels[i][j].getGreen() + " "
              + pixels[i][j].getBlue());
          writer.write(String.format("%t"));
        }
        writer.write(System.getProperty("line.separator"));
      }
      writer.close();
    }catch (Exception e) {
      throw new IllegalArgumentException();
    }
  }
}

