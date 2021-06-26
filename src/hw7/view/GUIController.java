package hw7.view;

import hw6.controller.ImageCommand;
import hw6.model.IMultiLayerImageModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController extends ImageCommand implements ActionListener {
IView view;
IMultiLayerImageModel model;
Readable rd;
Appendable ap;
  public GUIController(ImageGUI view, IMultiLayerImageModel model,  Readable rd, Appendable ap) {
    super(model, rd, ap);
    this.view = view;

  }

  /**
   * Similar to our ReadLine from ImageCommand, this reads the current state of the view
   * and translates action accordingly
   * @param
   */
  public void getState() {}


  @Override
  public void actionPerformed(ActionEvent e) {
  }

}
