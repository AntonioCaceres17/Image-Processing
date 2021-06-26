package hw6.controller;

import hw5.model.BlurImage;
import hw6.model.ExportImage;
import hw5.model.IFunction;
import hw5.model.IPixel;
import hw5.model.Image2D;
import hw5.model.Image2D.ImageReader;
import hw5.model.ImageModel;
import hw5.model.Monochrome;
import hw5.model.RGBPixel;
import hw5.model.Sepia;
import hw5.model.SharpenImage;
import hw6.model.IMultiLayerImageModel;
import hw6.model.LayeredImage2D;
import hw6.model.LayeredImageModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.swing.text.html.ImageView;

/**
 * T This class is able to take in batch commands to either load, edit, or save images This is able
 * to support both interactive or file-based scripting
 */
public class ImageCommand implements ImageController {

  private IMultiLayerImageModel model;
  private final Readable rd;
  private final Appendable ap;
  private Map<String, IFunction> singleImageCommands;
  private Map<String, Consumer<Scanner>> multiLayerImageCommands;
  private ImageView view;

  /**
   * Constructor for the ImageCommand class.
   *
   * @param model model controller passes inputs to
   * @param rd    readable with the client commands
   * @param ap    appendable to write to a view
   * @throws IllegalArgumentException
   */
  public ImageCommand(IMultiLayerImageModel model,  Readable rd, Appendable ap)
      throws IllegalArgumentException {
    try {
      Objects.requireNonNull(model, "Model cannot be null!");
      Objects.requireNonNull(rd, "Readable cannot be null!");
      Objects.requireNonNull(ap, "Appendable cannot be null!");
    } catch (NullPointerException npe) {
      throw new IllegalArgumentException(npe.getMessage());
    }

    this.model = model;
    this.rd = rd;
    this.ap = ap;

    singleImageCommands = new HashMap<>();
    singleImageCommands.put("blur", new BlurImage());
    singleImageCommands.put("sharpen", new SharpenImage());
    singleImageCommands.put("sepia", new Sepia());
    singleImageCommands.put("grayscale", new Monochrome());

    multiLayerImageCommands = new HashMap<>();
    multiLayerImageCommands.put("import", s -> readImage(s));
    multiLayerImageCommands.put("current", s -> current(s));
    multiLayerImageCommands.put("toggle", s -> this.model.toggleVisibility());
    multiLayerImageCommands.put("swap", s -> swap(s));
    multiLayerImageCommands.put("export", s -> export(s));
  }

  private void readImage(Scanner s) throws IllegalArgumentException {
    String filename = null;
    try {
      filename = s.next();
      ImageModel imageModel;
      if (filename.endsWith(".ppm")) {
        imageModel = ImageReader.createImageFromPPM(filename);
      } else {
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);
        IPixel[][] pixels = new IPixel[image.getHeight()][image.getWidth()];
        for (int y = 0; y < image.getHeight(); y++) {
          for (int x = 0; x < image.getWidth(); x++) {
            int pixel = image.getRGB(x, y);
            Color color = new Color(pixel);
            pixels[y][x] = new RGBPixel(color.getRed(), color.getGreen(), color.getBlue());
          }
        }
        imageModel = new Image2D(pixels, 0, 255);
      }
      LayeredImageModel layer =
          new LayeredImage2D(filename, true, imageModel);
      model.addLayer(layer);
    } catch (Exception e) {
      throw new IllegalArgumentException("File " + filename + " could not be read from.");
    }
  }

  private void current(Scanner s) throws IllegalArgumentException {
    try {
      int newCurrent = s.nextInt() - 1;
      model.setCurrent(newCurrent);
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Argument after current must of type int.");
    }
  }

  private void swap(Scanner s) throws IllegalArgumentException {
    try {
      int layer1 = s.nextInt() - 1;
      int layer2 = s.nextInt() - 1;
      model.swapLayers(layer1, layer2);
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Arguments after swap must be of type int, int.");
    }
  }

  @Override
  public void read() {
    Scanner in = new Scanner(rd);
    while (in.hasNextLine()) {
      readLine(in.nextLine());
    }
  }

  // reads a single command. Each command should be on its own line
  private void readLine(String rd) throws IllegalStateException, IllegalArgumentException {
    Scanner in = new Scanner(rd);
    if (!in.hasNext()) {
      throw new IllegalStateException("Scanner is over.");
    }
    String nxt = in.next();
    if (nxt.charAt(0) == '#') {
      return;
    } else if (singleImageCommands.containsKey(nxt)) {
      IFunction function = singleImageCommands.get(nxt);
      model.apply(function);
    } else if (multiLayerImageCommands.containsKey(nxt)) {
      multiLayerImageCommands.get(nxt).accept(in);
    } else {
      throw new IllegalArgumentException("Command " + nxt + " not found.");
    }
  }

  private void export(Scanner s) throws IllegalArgumentException {
    try {
      String imageType = s.next();
      ExportImage exp = new ExportImage(model.getTop());
      if (imageType.contains(".jpeg") || imageType.contains(".jpg")) {
        exp.makeImage(imageType, "jpeg");
      } else if (imageType.contains(".png")) {
        exp.makeImage(imageType, "png");
      } else if (imageType.contains(".ppm")) {
        exp.makePPM(imageType);
      } else {
        throw new IllegalArgumentException("Invalid File Extension!");
      }
    }catch (InputMismatchException e) {
      throw new IllegalArgumentException("Arguments after export must be of type String.");
    }
  }

}
