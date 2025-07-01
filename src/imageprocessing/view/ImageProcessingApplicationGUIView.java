package imageprocessing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import image.Image;
import imageprocessing.model.ImageProcessingApplicationModelState;

/**
 * Is a GUI view of an Image Processing Application.
 */
public class ImageProcessingApplicationGUIView extends JFrame
        implements ImageProcessingApplicationViewExtension {

  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton redComponentButton;
  private final JButton greenComponentButton;
  private final JButton blueComponentButton;
  private final JButton intensityComponentButton;
  private final JButton lumaComponentButton;
  private final JButton valueComponentButton;
  private final JButton flipHorizontalButton;
  private final JButton flipVerticalButton;
  private final JButton brightenButton;
  private final JButton darkenButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton greyscaleButton;
  private final JButton sepiaButton;

  // Active Image
  private final JLabel imageLabel;

  // Histogram for Active Image
  // INVARIANT: ALWAYS A HistogramPanel
  private JPanel histogramPanel;

  private ImageProcessingApplicationModelState model;

  /**
   * Construct a GUI View for an Image Processing Application.
   *
   * @param model the model that the view should use
   * @throws IllegalArgumentException if the model is null
   */
  public ImageProcessingApplicationGUIView(ImageProcessingApplicationModelState model)
          throws IllegalArgumentException {
    super();

    // Commands
    JPanel commandPanel;
    JPanel operationPanel;

    // Active Image
    JPanel imagePanel;

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;

    this.setTitle(" PhotoStore™");
    this.setSize(1400, 570);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // use a border layout with load/save on the top, operations
    // on the left, and histogram on the right
    this.setLayout(new BorderLayout());

    // command panel (load and save)
    commandPanel = new JPanel();
    commandPanel.setBackground(Color.darkGray);
    commandPanel.setLayout(new FlowLayout());
    this.add(commandPanel, BorderLayout.NORTH);

    // command buttons
    loadButton = new JButton("LOAD");
    commandPanel.add(loadButton);

    saveButton = new JButton("SAVE");
    commandPanel.add(saveButton);

    // operations panel
    operationPanel = new JPanel();
    operationPanel.setBackground(Color.gray);
    operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.Y_AXIS));
    JScrollPane operationScrollPane = new JScrollPane(operationPanel);
    this.add(operationScrollPane, BorderLayout.WEST);

    // operations
    redComponentButton = new JButton("Red Component");
    redComponentButton.setActionCommand("red-component ");

    greenComponentButton = new JButton("Green Component");
    greenComponentButton.setActionCommand("green-component ");

    blueComponentButton = new JButton("Blue Component");
    blueComponentButton.setActionCommand("blue-component ");

    intensityComponentButton = new JButton("Intensity Component");
    intensityComponentButton.setActionCommand("intensity-component ");

    lumaComponentButton = new JButton("Luma Component");
    lumaComponentButton.setActionCommand("luma-component ");

    valueComponentButton = new JButton("Value Component");
    valueComponentButton.setActionCommand("value-component ");

    flipHorizontalButton = new JButton("↔");
    flipHorizontalButton.setActionCommand("flip-horizontal ");

    flipVerticalButton = new JButton("↕");
    flipVerticalButton.setActionCommand("flip-vertical ");

    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("brighten ");

    darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("darken ");

    blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur ");

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen ");

    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("greyscale ");

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("sepia ");

    operationPanel.add(redComponentButton);
    operationPanel.add(greenComponentButton);
    operationPanel.add(blueComponentButton);
    operationPanel.add(intensityComponentButton);
    operationPanel.add(lumaComponentButton);
    operationPanel.add(valueComponentButton);
    operationPanel.add(flipHorizontalButton);
    operationPanel.add(flipVerticalButton);
    operationPanel.add(brightenButton);
    operationPanel.add(darkenButton);
    operationPanel.add(blurButton);
    operationPanel.add(sharpenButton);
    operationPanel.add(greyscaleButton);
    operationPanel.add(sepiaButton);

    // image panel
    imagePanel = new JPanel();
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    this.add(imagePanel, BorderLayout.CENTER);

    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    //imageLabel.setIcon(new ImageIcon(startupImage));
    imagePanel.add(imageScrollPane);


    // histogram panel
    histogramPanel = new HistogramPanel(model);
    //JScrollPane histogramScrollPane = new JScrollPane(histogramPanel);
    histogramPanel.setBackground(Color.white);
    imagePanel.add(histogramPanel, BorderLayout.EAST); // !

    //this.pack();
    this.repaintView();
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message,
            "Message", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void renderImageList() {
    String[] names = model.getImageNames();
    StringBuilder output = new StringBuilder();
    for (String name : names) {
      output.append(name).append("\n");
    }
    JOptionPane.showMessageDialog(this, output.toString(),
            "Message", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setButtonListeners(ActionListener actionEvent) {
    loadButton.addActionListener(actionEvent);
    saveButton.addActionListener(actionEvent);
    redComponentButton.addActionListener(actionEvent);
    greenComponentButton.addActionListener(actionEvent);
    blueComponentButton.addActionListener(actionEvent);
    intensityComponentButton.addActionListener(actionEvent);
    lumaComponentButton.addActionListener(actionEvent);
    valueComponentButton.addActionListener(actionEvent);
    flipHorizontalButton.addActionListener(actionEvent);
    flipVerticalButton.addActionListener(actionEvent);
    brightenButton.addActionListener(actionEvent);
    darkenButton.addActionListener(actionEvent);
    blurButton.addActionListener(actionEvent);
    sharpenButton.addActionListener(actionEvent);
    greyscaleButton.addActionListener(actionEvent);
    sepiaButton.addActionListener(actionEvent);
  }

  @Override
  public void addImage(ImageProcessingApplicationModelState model, String imageName) {
    Image imageFromModel = null;
    try {
      imageFromModel = model.getImageCopy(imageName);
    } catch (Exception e) {
      return;
    }

    BufferedImage bufferedImage = imageFromModel.asBufferedImage();
    imageLabel.setIcon(new ImageIcon(bufferedImage));

    ((HistogramPanel) histogramPanel).updateHistogram();
  }

  @Override
  public void displayError(String error) {
    JOptionPane.showMessageDialog(this, error,
            "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void repaintView() {

    this.setVisible(true);
    this.repaint();
    histogramPanel.repaint();
  }

  @Override
  public String getStringInput() {
    return (String) JOptionPane.showInputDialog("Value: ");
  }

  @Override
  public String fileChooserLoad() throws IllegalStateException {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG, PPM, & BMP Images", "jpg", "png", "bmp", "ppm");
    fchooser.setFileFilter(filter);
    return fileChooser(fchooser, true);
  }

  @Override
  public String fileChooserSave() throws IllegalStateException {
    final JFileChooser fchooserSave = new JFileChooser(".");
    return fileChooser(fchooserSave, false);
  }

  // Helper method for abstraction
  private String fileChooser(JFileChooser fchooser, boolean isLoad) throws IllegalStateException {
    int retvalue = 0;
    if (isLoad) {
      retvalue = fchooser.showOpenDialog(null);
    } else {
      retvalue = fchooser.showSaveDialog(null);
    }
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    throw new IllegalStateException("Error with file transmission");
  }

}
