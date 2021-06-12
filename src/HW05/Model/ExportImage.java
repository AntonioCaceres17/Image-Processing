package HW05.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class exports an ImageModel to the desired type of image file.
 */
public class ExportImage {

  private ImageModel image;

  /**
   * Constructor for ExportImage class.
   *
   * @param image image to be exported.
   */
  public ExportImage(ImageModel image) {
    this.image = image;
  }

  /**
   * Writes and creates a PPM file of the ImageModel, abiding by the format rules.
   * @param filename the desired name of the file to be written and exported
   * @throws IllegalArgumentException if the Image or filename is invalid
   */
  public void makePPM(String filename) throws IllegalArgumentException {
    if (image.getPixels().length ==  0 || filename == null) {
      throw new IllegalArgumentException("Image or Filename is invalid!");
    }
    if (!filename.endsWith(".ppm")) {
      throw new IllegalArgumentException("Filename must end with .ppm");
    }
    IPixel[][] pixels = image.getPixels();
    try {
      File filePPM = new File(filename);
      if (!filePPM.exists()) {
        filePPM.createNewFile();
      }
      FileWriter writer = new FileWriter(filePPM);
      //header
      writer.write("P3");
      writer.write(System.getProperty("line.separator"));
      writer.write(image.width() + " " + image.height());
      writer.write(System.getProperty("line.separator"));
      writer.write(Integer.toString(image.maxPixelValue()));
      writer.write(System.getProperty("line.separator"));
      //Pixels
      for (int i = 0; i < pixels.length; i++) {
        for (int j = 0; j < pixels[i].length; j++) {
          writer.write(pixels[i][j].getRed() + " "
              + pixels[i][j].getGreen() + " "
              + pixels[i][j].getBlue());
          // tab to represent a different pixel
          writer.write("  ");
        }
        writer.write(System.getProperty("line.separator"));
      }
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }
}

