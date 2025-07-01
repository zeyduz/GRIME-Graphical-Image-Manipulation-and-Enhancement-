package imageprocessing.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Represents a single bar of a histogram.
 */
public class HistogramBar extends JPanel {

  private final int redHeight;
  private final int greenHeight;
  private final int blueHeight;
  private final int intensityHeight;

  /**
   * Construct a bar of a histogram with the given heights.
   *
   * @param redHeight red height
   * @param greenHeight green height
   * @param blueHeight blue height
   * @param intensityHeight intensity height
   */
  public HistogramBar(int redHeight, int greenHeight, int blueHeight, int intensityHeight) {
    this.redHeight = redHeight;
    this.greenHeight = greenHeight;
    this.blueHeight = blueHeight;
    this.intensityHeight = intensityHeight;
  }

  @Override
  public void paint(Graphics graphics) {
    graphics.setColor(new Color(255, 0, 0, 50));
    graphics.fillRect(0, 500 - redHeight, 4, redHeight);
    graphics.drawRect(0, 500 - redHeight, 4, redHeight);

    graphics.setColor(new Color(0, 255, 0, 50));
    graphics.fillRect(0, 500 - greenHeight, 4, greenHeight);
    graphics.drawRect(0, 500 - greenHeight, 4, greenHeight);

    graphics.setColor(new Color(0, 0, 255, 50));
    graphics.fillRect(0, 500 - blueHeight, 4, blueHeight);
    graphics.drawRect(0, 500 - blueHeight, 4, blueHeight);

    graphics.setColor(new Color(150, 150, 150, 50));
    graphics.fillRect(0, 500 - intensityHeight, 4, intensityHeight);
    graphics.drawRect(0, 500 - intensityHeight, 4, intensityHeight);
  }

}
