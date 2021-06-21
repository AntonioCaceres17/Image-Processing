package hw6.model;

import java.io.IOException;

public interface IExportImage {

  /**
   * Writes and creates a PPM file of the ImageModel, abiding by the format rules.
   *
   * @param filename the desired name of the file to be written and exported
   * @throws IllegalArgumentException if the Image or filename is invalid or could not be created
   */
  void makePPM(String filename) throws IllegalArgumentException, IOException;

  /**
   * Writes and creates a specified Image File of the ImageModel, as supported by the
   * ImageIO class' @Code write method.
   * @param filename the desired name of the file ot be written and exported
   * @param imageFormat the type of the file to be exported such as .JPEG or .PNG
   * @throws IllegalArgumentException if the Image or filename is invalid or could not be created
   */
  void makeImage(String filename, String imageFormat) throws IllegalArgumentException;

}
