package hw7.view;

import hw5.model.ImageModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * This class is a graphical user interface that acts as a view for an image with multiple layers.
 */
public class ImageGUI extends JFrame implements IView  {

  private JSplitPane editPanel;
  private JPanel filePanel;
  private JPanel imagePanel;
  private ArrayList<JLabel> imageLabels;
  private JScrollPane scrollImage;
  private JLabel currentImage;
  private JTabbedPane buttonPanel;
  private HashMap<String, JLabel> LoImage;
  private String imagePath;
  private JPopupMenu fileMenu = new JPopupMenu();
  private ActionMap commands;

  private JButton openImageButton;
  private JButton exportImageButton;

  private JButton sepiaButton;
  private JButton monoButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton mosaicButton;
  private JButton resizeButton;
  private JButton removeLayerButton;
  private JButton goBackButton;
  private JButton goForwardButton;

  public ImageGUI() {
    super();
    setTitle("Image Editor");
    setSize(700, 500);
    makeFileMenu();
    buttonCreator();
    makeFrame();
    super.setVisible(true);
    commands = new ActionMap();

  }

  public void setListener(ActionListener listener) {
    openImageButton.addActionListener(listener);
    exportImageButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    monoButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    blurButton.addActionListener(listener);
    mosaicButton.addActionListener(listener);
    resizeButton.addActionListener(listener);
    removeLayerButton.addActionListener(listener);
    goBackButton.addActionListener(listener);
    goForwardButton.addActionListener(listener);

  }

  private void makeFrame() {
    editPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, buttonPanel);
    /// Setting Image
    imagePanel = new JPanel();
    currentImage = new JLabel("res/Acadia.png");
    currentImage.setIcon(new ImageIcon("res/Acadia.png"));
    scrollImage = new JScrollPane(currentImage);

    //Change Dimensions....
    scrollImage.setPreferredSize(new Dimension(300, 600));
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image being edited."));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
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
    openImageButton = new JButton("Import Image");
    openImageButton.setActionCommand("Open Image");

    exportImageButton = new JButton("Export Image");
    exportImageButton.setActionCommand("Save Image");

    filePanel.add(openImageButton);
    filePanel.add(exportImageButton);
  }


  private void buttonCreator() {
    buttonPanel = new JTabbedPane();
    buttonPanel.setPreferredSize(new Dimension(300, 500));
    JPanel transformationTab = new JPanel();
    JPanel layeredImageTab = new JPanel();
    JPanel filterTab = new JPanel();

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");

    monoButton = new JButton("MonoChrome");
    monoButton.setActionCommand("Mono");


    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");


    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");


    mosaicButton = new JButton("Mosaic");
    mosaicButton.setActionCommand("Mosaic");


    resizeButton = new JButton("Image Downsize");
    resizeButton.setActionCommand("Downsize");


    removeLayerButton = new JButton("Remove Layer");
    removeLayerButton.setActionCommand("Remove");


    goBackButton = new JButton("Go back 1 layer");
    goBackButton.setActionCommand("Back");


    goForwardButton = new JButton("Go Forward 1 Layer");
    goForwardButton.setActionCommand("Forward");


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
  public void updateImage(String filename) {
    scrollImage.revalidate();
    JLabel newImage = new JLabel();
    newImage.setIcon(new ImageIcon(filename));
    scrollImage.repaint();
    imageLabels.add(newImage);
    imagePanel.revalidate();
    imagePanel.add(scrollImage);
    imagePanel.repaint();
  }



  @Override
  public String dropDown(String command) {
    switch (command) {
      case "import": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPEG, PNG, & PPM Images", "jpeg", "png", "ppm");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(ImageGUI.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          //****** Maybe a new method that saves this file path? ****
          return f.getAbsolutePath();
        }
      }
      break;
      case "export": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(ImageGUI.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          return f.getAbsolutePath();
        }
      }
      break;
      case "downsize": {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add(new JLabel("Enter Width"));
        label.add(new JLabel("Enter Height"));
        panel.add(label, BorderLayout.WEST);

        JPanel controller = new JPanel(new GridLayout( 0 ,1, 2,2));
        JTextField width = new JTextField();
        controller.add(width);
        JTextField height = new JTextField();
        controller.add(height);
        panel.add(controller, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(this, panel,
            "Downsize dimensions", JOptionPane.OK_CANCEL_OPTION);

        return width.getText() + "," + height.getText();
      }

      case "mosaic":
        JPanel panel = new JPanel(new BorderLayout(5,5));
        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add(new JLabel("Enter Seed Number"));
        panel.add(label, BorderLayout.CENTER);

       return JOptionPane.showInputDialog(this, panel,
            "Seed Number", JOptionPane.OK_CANCEL_OPTION);


    }
    throw new IllegalArgumentException("Invalid Command");
  }

  @Override
  public void updateView() {
    editPanel.revalidate();
    editPanel.repaint();
  }

  @Override
  public void setImage(String imagePath) {
    currentImage.setIcon(new ImageIcon(imagePath));
  }
}