package hw6.controller;

import hw5.model.BlurImage;
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
import hw6.model.MultiLayerImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.imageio.ImageIO;


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

  public ImageCommand(IMultiLayerImageModel model, Readable rd, Appendable ap)
      throws IllegalArgumentException {
    Objects.requireNonNull(model, "Model cannot be null!");
    Objects.requireNonNull(rd, "Readable cannot be null!");
    Objects.requireNonNull(ap, "Appendable cannot be null!");

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
      int newCurrent = s.nextInt();
      model.setCurrent(newCurrent);
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Argument after current must of type int.");
    }
  }

  private void swap(Scanner s) throws IllegalArgumentException {
    try {
      int layer1 = s.nextInt();
      int layer2 = s.nextInt();
      model.swapLayers(layer1, layer2);
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Arguments after swap must be of type int, int.");
    }
  }

  public void read() {
    Scanner in = new Scanner(rd);
    while (in.hasNextLine()) {
      readLine(in.nextLine());
    }
  }

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
}
