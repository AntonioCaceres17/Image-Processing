package hw7.view;

import hw5.model.BlurImage;
import hw5.model.IFunction;
import hw5.model.IPixel;
import hw5.model.Image2D;
import hw5.model.ImageModel;
import hw5.model.Monochrome;
import hw5.model.RGBPixel;
import hw5.model.Sepia;
import hw5.model.SharpenImage;
import hw6.model.LayeredImage2D;
import hw6.model.LayeredImageModel;
import hw7.model.DownSize;
import hw7.model.Mosaic;
import hw7.model.ResizableMultiLayerImage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class GUIController implements IGUIController, ActionListener {

  private IView view;
  private ResizableMultiLayerImage model;

  protected Map<String, IFunction> imageButtonNoTextNeededCommandMap;
  protected Map<String, Runnable> imageButtonCommandWithDropDownMenuMap;

  public GUIController(ImageGUI view, ResizableMultiLayerImage model)
      throws IllegalArgumentException {
    try {
      this.view = view;
      this.model = model;
    } catch (NullPointerException npe) {
      throw new IllegalArgumentException(npe.getMessage());
    }

    view.setListener(this);

    imageButtonNoTextNeededCommandMap = new HashMap<>();
    imageButtonNoTextNeededCommandMap.put("Sepia", new Sepia());
    imageButtonNoTextNeededCommandMap.put("Mono", new Monochrome());
    imageButtonNoTextNeededCommandMap.put("Blur", new BlurImage());
    imageButtonNoTextNeededCommandMap.put("Sharpen", new SharpenImage());

    imageButtonCommandWithDropDownMenuMap = new HashMap<>();
    imageButtonCommandWithDropDownMenuMap.put("Mosaic", mosaic());
    imageButtonCommandWithDropDownMenuMap.put("Downsize", downSize());
    imageButtonCommandWithDropDownMenuMap.put("Open Image", importImage());
    imageButtonCommandWithDropDownMenuMap.put("Save Image", () ->
        view.dropDown("export"));
  }

  private Runnable importImage() throws IllegalArgumentException {
    Runnable runnable = new Runnable() {
      public void run() {
        String filePath = view.dropDown("import");
        try {
          BufferedImage image = ImageIO.read(new File(filePath));
          IPixel[][] pixels = new IPixel[image.getHeight()][image.getWidth()];
          for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
              int pixel = image.getRGB(x, y);
              Color color = new Color(pixel);
              pixels[y][x] = new RGBPixel(color.getRed(), color.getGreen(), color.getBlue());
            }
          }
          ImageModel imageModel = new Image2D(pixels, 0, 255);
          LayeredImageModel layer =
              new LayeredImage2D(filePath, true, imageModel);
          model.addLayer(layer);
        } catch (IOException e) {
          throw new IllegalArgumentException("File was not found.");
        }
      }
    };
    return runnable;
  }

  private Runnable mosaic() {
    return new Runnable() {
      public void run() {
        try {
          int seeds = Integer.parseInt(view.dropDown("mosaic"));
          model.apply(new Mosaic(seeds));
        } catch (NumberFormatException nfe) {
          throw new IllegalArgumentException("Number of seeds must be an int.");
        }
      }
    };
  }

  private Runnable downSize() {
    return () -> {
      try {
        String inputs = view.dropDown("downsize");
        String[] scales = inputs.split(",");
        double scaleWidth = Double.parseDouble(scales[0].trim());
        double scaleHeight = Double.parseDouble(scales[1].trim());
        model.applyToAll(new DownSize(scaleWidth, scaleHeight));
      } catch (Exception e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    };
  }

  /**
   * Similar to our ReadLine from ImageCommand, this reads the current state of the view and
   * translates action accordingly
   *
   * @param
   */
  public void getState() {
  }


  @Override
  public void actionPerformed(ActionEvent e) throws IllegalArgumentException {
    String command = e.getActionCommand();
    if (imageButtonCommandWithDropDownMenuMap.containsKey(command)) {
      imageButtonCommandWithDropDownMenuMap.get(command).run();
    } else if (imageButtonNoTextNeededCommandMap.containsKey(command)) {
      model.apply(imageButtonNoTextNeededCommandMap.get(command));
    } else {
      throw new IllegalArgumentException("Command not found.");
    }
    view.setImage(model.layerName());
    view.updateView();
  }

  @Override
  public void run() throws IllegalArgumentException {

  }
}
