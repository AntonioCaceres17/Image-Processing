package HW05.Model;

/**
 * This class represents the Sepia Tone transformation for a given picture.
 * The RGB Matrix specifications are:
 * <br> | 0.393 0.769 0.189 |
 * <br>| 0.349  0.686 0.168 |<br>| 0.272 0.534 0.131 |
 */
public class Sepia extends RGBTransformation {

 /**
  * Constructor for Sepia transformation class.
  */
  public Sepia() {
   super(new double[][]{
       {0.393 , 0.769 , 0.189},
       {0.349, 0.686, 0.168},
       {0.272, 0.534, 0.131}});
  }
}
