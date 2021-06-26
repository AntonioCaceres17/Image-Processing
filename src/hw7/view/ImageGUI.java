package hw7.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ImageGUI extends JFrame implements IView, ActionListener {

  private JSplitPane editPanel;
  private JPanel filePanel;
  private boolean isThereImage;
  private JPanel imagePanel;
  private JScrollPane scrollImage;
  private JTabbedPane buttonPanel;
  private HashMap<String, JLabel> LoImage;
  JLabel imagePath;
  private JPopupMenu fileMenu = new JPopupMenu();
  private ActionMap commands;

  public ImageGUI() {
    super();
    setTitle("Image Editor");
    setSize(700, 500);
    makeFileMenu();
    buttonCreator();
   makeFrame();
    super.setVisible(true);
    isThereImage = false;
    commands = new ActionMap();

  }

private void makeFrame() {
  editPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, buttonPanel);
  /// Setting Image
  imagePanel = new JPanel();
  JLabel imageLabel = new JLabel();
  scrollImage = new JScrollPane(imageLabel);
  //Change Dimensions....
scrollImage.setPreferredSize(new Dimension(300,600));
  imagePanel.setBorder(BorderFactory.createTitledBorder("Image being edited."));
  imagePanel.setLayout(new GridLayout(1,0,10,10));
  editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.LINE_AXIS));
  editPanel.setDividerLocation(150);
  JTabbedPane tabs = new JTabbedPane(1);
  tabs.addTab("Edit", editPanel);
  tabs.addTab("File", filePanel);
  super.add(tabs);
  imagePanel.add(scrollImage);
  editPanel.add(imagePanel);


}



private void makeFileMenu() {
  filePanel = new JPanel();
  filePanel.setLayout(new FlowLayout());
  JButton openImageButton = new JButton("Import Image");
  openImageButton.setActionCommand("Open Image");
  openImageButton.addActionListener(this);

  JButton exportImageButton = new JButton("Export Image");
  exportImageButton.setActionCommand("Save Image");
  exportImageButton.addActionListener(this);

 imagePath = new JLabel("\"File path will appear here\"");



  filePanel.add(openImageButton);
  filePanel.add(exportImageButton);
}




  public void addImage(String filename) {
    LoImage.put(filename, new JLabel());
    LoImage.get(filename).setIcon(new ImageIcon(filename));
    imagePanel.add(LoImage.get(filename));


  }

  private void buttonCreator() {
    buttonPanel = new JTabbedPane();
    buttonPanel.setPreferredSize(new Dimension(300, 500 ));
   JPanel transformationTab = new JPanel();
   JPanel layeredImageTab = new JPanel();
   JPanel filterTab = new JPanel();


    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    sepiaButton.addActionListener(this);

    JButton monoButton = new JButton("MonoChrome");
    monoButton.setActionCommand("Mono");
    monoButton.addActionListener(this);

    JButton blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    blurButton.addActionListener(this);

    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    sharpenButton.addActionListener(this);

    JButton mosaicButton = new JButton("Mosaic");
    mosaicButton.setActionCommand("Mosaic");
    mosaicButton.addActionListener(this);

    JButton resizeButton = new JButton("Image Resize");
    resizeButton.setActionCommand("Resize");
    resizeButton.addActionListener(this);


    JButton removeLayerButton = new JButton("Remove Layer");
    removeLayerButton.setActionCommand("Remove");
    removeLayerButton.addActionListener(this);

    JButton goBackButton = new JButton("Go back 1 layer");
    goBackButton.setActionCommand("Back");
    goBackButton.addActionListener(this);


    JButton goForwardButton = new JButton("Go Forward 1 Layer");
    goForwardButton.setActionCommand("Forward");
    goForwardButton.addActionListener(this);


    transformationTab.setLayout(new FlowLayout());
    transformationTab.add(sharpenButton);
    transformationTab.add(blurButton);
    transformationTab.add(resizeButton);

    filterTab.add(sepiaButton);
    filterTab.add(monoButton);
    filterTab.add(mosaicButton);


    layeredImageTab.add(goForwardButton);
    layeredImageTab.add(goBackButton);
    layeredImageTab.add(removeLayerButton);


    buttonPanel.addTab("Image Layer", layeredImageTab);
   buttonPanel.addTab("Transformation", transformationTab);
    buttonPanel.add("Filter", filterTab);



//    buttonPanel.setLayout(new CardLayout());
  //  buttonPanel.add(combo);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //TODO : Make an observer method in the Controller that can read this swtich method
    //TODO : Finish all the commands for the view(Most are for the controller)
    //TODO : Be able to display image from Controller.
      switch (e.getActionCommand()) {
        case "Open Image" : {
          final JFileChooser fchooser = new JFileChooser(".");
          FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPEG, PNG, & PPM Images", "jpeg", "png", "ppm");
          fchooser.setFileFilter(filter);
          int retvalue = fchooser.showOpenDialog(ImageGUI.this);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            //****** Maybe a new method that saves this file path? ****
            imagePath.setText(f.getAbsolutePath());
          }
        }
        break;
        case "Save Image" :  {
          final JFileChooser fchooser = new JFileChooser(".");
          int retvalue = fchooser.showSaveDialog(ImageGUI.this);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            imagePath.setText(f.getAbsolutePath());

          }
        }
        break;
      }
    }


}
