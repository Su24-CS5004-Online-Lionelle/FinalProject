package student.view.Components;

import javax.swing.*;
import java.awt.*;

/**
 * GridBackground is a custom JPanel that paints a pokeball design with a grid overlay.
 */
public class GridBackground extends JPanel {
    /**
     * Constructor for GridBackground.
     */
    public GridBackground() {
        super();
        this.setBackground(Color.WHITE);
    }

    /**
     * This method paints a pokeball centered in the panel with a grid pattern.
     *
     * @param g The Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g); // Step 2.1: Call superclass's method

        Graphics2D g2 = (Graphics2D) g;

        // Drawing the Pokeball
        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;
        int radius = 150; // Adjust for size

        // Outer circle
        g2.setColor(new Color(220, 220, 220));
        g2.fillOval(xCenter - radius, yCenter - radius, 2 * radius, 2 * radius);

        // Middle line
        g2.setStroke(new BasicStroke(10)); // Thicker line for the middle
        g2.drawLine(xCenter - radius, yCenter, xCenter + radius, yCenter);

        g2.setColor(new Color(220, 220, 220));
        g2.fillArc(xCenter - radius, yCenter - radius, 2 * radius, 2 * radius, 0, 180);

        // Drawing two horizontal white lines around the equator
        g2.setColor(Color.WHITE); // Set color to white
        g2.setStroke(new BasicStroke(10)); // Set line thickness, adjust as needed

        // Calculate the positions
        int lineSpacing = 5; // Space between the equator and each line, adjust as needed

        // Draw the lines
        g2.drawLine(xCenter - radius, yCenter - lineSpacing, xCenter + radius, yCenter - lineSpacing); // Above the equator
        g2.drawLine(xCenter - radius, yCenter + lineSpacing, xCenter + radius, yCenter + lineSpacing); // Below the equator

        // Center circle
        int centerCircleRadius = 60; // Adjust for size
        g2.setColor(Color.WHITE); // Set color to white for the cutout effect
        g2.fillOval(xCenter - centerCircleRadius, yCenter - centerCircleRadius, 2 * centerCircleRadius, 2 * centerCircleRadius);

        int innerCircleRadius = 40; // Adjust for size
        g2.setColor(new Color(220, 220, 220));
        g2.fillOval(xCenter - innerCircleRadius, yCenter - innerCircleRadius, 2 * innerCircleRadius, 2 * innerCircleRadius);

        // Draw the grid
        g.setColor(new Color(190, 190, 190));
        g2.setStroke(new BasicStroke(2));
        int gridSize = 30; // Size of each grid square, adjust as needed
        for (int i = 0; i < getWidth(); i += gridSize) {
            g.drawLine(i, 0, i, getHeight()); // Vertical lines
        }
        for (int j = 0; j < getHeight(); j += gridSize) {
            g.drawLine(0, j, getWidth(), j); // Horizontal lines
        }
    }
}
