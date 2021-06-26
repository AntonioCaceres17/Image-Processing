import hw6.model.ResizableLayeredImageModel;
import hw7.model.ResizableMultiLayerImage;
import hw7.view.GUIController;
import hw7.view.IView;
import hw7.view.ImageEx;
import hw7.view.ImageGUI;

public class ViewTest {

  public static void main (String[] args) {
   // ImageEx hello = new ImageEx();
   // hello.setVisible(true);
    ImageGUI view = new ImageGUI();
    ResizableMultiLayerImage model = new ResizableMultiLayerImage();
    Readable rd;
    GUIController controller = new GUIController(view,model);
  }

}
