package hw7.model;

import hw5.model.IFunction;
import hw5.model.IPixel;
import hw5.model.ImageModel;
import hw5.model.RGBPixel;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Mosaic implements IFunction {

  private final int seeds;

  public Mosaic(int seeds) {
    this.seeds = seeds;
  }

  @Override
  public ImageModel apply(ImageModel image) throws IllegalArgumentException {
    Map<Point, List<Point>> seedLocs = generateSeedLocations(image);
    IPixel[][] mosaicPixels = new IPixel[image.height()][image.width()];

    // create the mosaics
    for (int y = 0; y < image.height(); y++) {
      for (int x = 0; x < image.width(); x++) {
        Point closestPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Point curPixel = new Point(x, y);
        for (Point p : seedLocs.keySet()) {
          if (curPixel.distance(p) < curPixel.distance(closestPoint)) {
            closestPoint = p;
          }
        }
        seedLocs.get(closestPoint).add(curPixel);
      }
    }

    // set the colors of the new pixels to the average of the pixels in each tile
    for (Point p : seedLocs.keySet()) {
      int[] colorChannels = averageTileColor(image, seedLocs.get(p));
      for (Point pixel : seedLocs.get(p)) {
        mosaicPixels[pixel.y][pixel.x] = new RGBPixel(colorChannels);
      }
    }

    return image.copyProperties(mosaicPixels);
  }

  private Map<Point, List<Point>> generateSeedLocations(ImageModel image) {
    Map<Point, List<Point>> rndPixels = new HashMap<>();
    Random rnd = new Random();
    for (int i = 0; i < seeds; i++) {
      Point p = new Point(rnd.nextInt(image.width()), rnd.nextInt(image.height()));
      rndPixels.put(p, new ArrayList<>());
    }
    return rndPixels;
  }

  private int[] averageTileColor(ImageModel image, List<Point> tile) {
    int r = 0, g = 0, b = 0;
    for (Point p: tile) {
      IPixel pixel = image.getPixel(p.x, p.y);
      r += pixel.getRed();
      g += pixel.getGreen();
      b += pixel.getBlue();
    }
    if (tile.size() == 0) {
      return new int[]{0, 0, 0};
    }
    return new int[]{r / tile.size(), g / tile.size(), b / tile.size()};
  }
}
