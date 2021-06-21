package hw6.model;

import hw5.model.IPixel;
import hw5.model.ImageModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class exports an ImageModel to the desired type of image file.
 */
public class ExportImage implements IExportImage {

  private ImageModel image;

  /**
   * Constructor for ExportImage class.
   *
   * @param image image to be exported.
   * @throws IllegalArgumentException if image is null
   */
  public ExportImage(ImageModel image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image is null.");
    }
    this.image = image;
  }


  @Override
  public void makePPM(String filename) throws IllegalArgumentException {
    if (image.getPixels().length == 0 || filename == null) {
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
      throw new IllegalArgumentException("Could not create PPM File.");
    }
  }

  @Override
  public void makeImage(String filename, String imageFormat) throws IllegalArgumentException {
    if (image.getPixels().length == 0 || filename == null) {
      throw new IllegalArgumentException("Image or Filename is invalid!");
    }
    if (!filename.toLowerCase().endsWith("." + imageFormat.toLowerCase())) {
      throw new IllegalArgumentException("Filename must end with ." + imageFormat);
    }
    File file = new File(filename);
    try {
      IPixel[][] pixels = image.getPixels();
      BufferedImage img = new BufferedImage(image.width(), image.height(),
          BufferedImage.TYPE_INT_ARGB);
      for (int x = 0; x < pixels.length; x++) {
        for (int y = 0; y < pixels[y].length; y++) {
          IPixel thisPixel = pixels[x][y];
          int rgb = thisPixel.getRed();
          rgb = (rgb << 8) + thisPixel.getGreen();
          rgb = (rgb << 8) + thisPixel.getBlue();
          img.setRGB(x, y, rgb);
        }
      }
      ImageIO.write(img, imageFormat, file);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not create Image File.");
    }
  }
}

