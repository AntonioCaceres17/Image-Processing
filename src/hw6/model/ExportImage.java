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

  private final ImageModel image;

  /**
   * Constructor for ExportImage class.
   *
   * @param image image to be exported.
   * @throws IllegalArgumentException if the image is null
   */
  public ExportImage(ImageModel image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
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
    if (imageFormat == null) {
      throw new IllegalArgumentException("image format is null.");
    }
    if (image.getPixels().length == 0 || filename == null) {
      throw new IllegalArgumentException("Image or Filename is invalid!");
    }
    if (!filename.endsWith(".jpeg") || !filename.endsWith(".jpg") || filename.endsWith(".png")) {
      throw new IllegalArgumentException("Filename must end with .ppm");
    }
    File fileJPEG = new File(filename);
    try {
      IPixel[][] pixels = image.getPixels();
      BufferedImage img = new BufferedImage(image.width(), image.height(),
          BufferedImage.TYPE_INT_ARGB);
      for (int y = 0; y < pixels.length; y++) {
        for (int x = 0; x < pixels[y].length; x++) {
          IPixel thisPixel = pixels[x][y];
          int rgb = thisPixel.getRed();
          rgb = (rgb << 8) + thisPixel.getGreen();
          //TODO: Put this in the pixel class (GetRGB)....
          rgb = (rgb << 8) + thisPixel.getBlue();
          img.setRGB(x, y, rgb);
        }
      }
      ImageIO.write(img, imageFormat, fileJPEG);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not create " + imageFormat + " File.");
    }
  }
}

