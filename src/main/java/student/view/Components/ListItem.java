package student.view.Components;

import javax.swing.*;

import student.controller.PokedexController;
import student.model.PokeRecord;
import student.view.IndivPokemonPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListItem extends JPanel {
    private static List<ListItem> panels = new ArrayList<>();
    private boolean isHighlighted = false;
    private PokeRecord currPokemon;
    private PokedexController controller = new PokedexController();

    public ListItem(PokeRecord pokemon) {
        this.currPokemon = pokemon;
        setPreferredSize(new Dimension(400, 100)); // Adjust the size as needed
        setOpaque(false); // Ensure transparency

        // Add this panel to the list of panels
        panels.add(this);

        // Add mouse listener for highlighting
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                highlightPanel();
                IndivPokemonPanel indivPokemonPanel = IndivPokemonPanel.getInstance();
                indivPokemonPanel.setRecord(currPokemon);
                indivPokemonPanel.refreshPanel();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 100); // Set the preferred width and height
    }

    /**
     * Custom painting code for the component. This method overrides the default
     * painting behavior to draw a rounded rectangle with a black border, an inner
     * white rounded rectangle, and two red stripes on the left and right sides.
     *
     * @param g the object used to paint the component
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        /** Enable anti-aliasing for smooth edges. */
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int arcSize = 30;
        /** Draw the rounded rectangle with black border. */
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(0, 0, width, height, arcSize, arcSize);
        /** Draw the inner white rounded rectangle. */
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(5, 5, width - 10, height - 10, arcSize, arcSize);
        /** Draw the left red stripe. */
        g2d.setColor(Color.RED);
        g2d.fillRoundRect(5, 5, 20, height - 10, arcSize, arcSize);
        /** Draw the right red stripe. */
        g2d.fillRoundRect(width - 25, 5, 20, height - 10, arcSize, arcSize);

        // Load the custom font from the resources/Font directory
        try {
            Font customFont;
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("Font/PressStart2P-Regular.ttf")) {
                if (is == null) {
                    throw new IOException("Font file not found");
                }
                customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            }
            customFont = customFont.deriveFont(14f); // Set the font size to 20
            g2d.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Fallback to default font if loading fails
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        }

        // Draw the text in the center
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        String text = currPokemon.name().substring(0, 1).toUpperCase() + currPokemon.name().substring(1).toLowerCase();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int textX = (width - textWidth) / 2;
        int textY = (height + textHeight) / 2 - fm.getDescent();
        g2d.drawString(text, textX, textY);

        g2d.setColor(Color.LIGHT_GRAY);
        String id = String.format("#%d", currPokemon.id());
        textWidth = fm.stringWidth(id);
        textHeight = fm.getAscent();
        textX = 30;
        textY = (height + textHeight) / 2 - fm.getDescent();
        g2d.drawString(id, textX, textY);

        drawPokeball(g2d);

        // Highlight the panel if it is selected
        if (isHighlighted) {
            g2d.setColor(new Color(0, 0, 0, 50)); // Semi-transparent black
            g2d.fillRoundRect(1, 1, width - 2, height - 2, arcSize, arcSize);
        }
    }

    private void highlightPanel() {
        // Unhighlight all panels
        for (ListItem panel : panels) {
            panel.isHighlighted = false;
            panel.repaint();
        }

        // Highlight this panel
        isHighlighted = true;
        repaint();
    }

    /**
     * Sets the currPokemon field.
     *
     * @param pokemon PokeRecord to set
     */
    public void setCurrPokemon(PokeRecord pokemon) {
        this.currPokemon = pokemon;
    }

    /**
     * Gets the current pokemon.
     *
     * @return returns PokeRecord currPokemon
     */
    public PokeRecord getCurrPokemon() {
        return this.currPokemon;
    }

    /**
     * Draws pokeball in color or gray depending if the pokemon
     * is already in the team.
     *
     * @param g Graphics object to draw with
     */
    private void drawPokeball(Graphics g) {
        boolean isInTeam;
        try {
            isInTeam = controller.isPokemonInTeam(currPokemon);
        } catch (IOException e) {
            isInTeam = false;
        }

        Graphics2D g2d = (Graphics2D) g;

        int panelWidth = getWidth();
        int pokeballDiameter = 30;
        int x = panelWidth - pokeballDiameter - 40;

        // Save the original transformation
        AffineTransform originalTransform = g2d.getTransform();

        // Rotate the 2dgraphics context by -45 degrees (left tilt)
        g2d.rotate(Math.toRadians(-45), x + pokeballDiameter / 2, pokeballDiameter / 2); // Rotate around the center of the Pokéball

        // Draw the outer circle
        g2d.setColor(isInTeam ? Color.RED : Color.GRAY);
        g2d.fillOval(x, 15, pokeballDiameter, pokeballDiameter);

        // Draw the bottom half
        g2d.setColor(isInTeam ? Color.BLACK : Color.DARK_GRAY);
        g2d.fillArc(x, 15, pokeballDiameter, pokeballDiameter, 0, -180);

        // Draw the center circle
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x + 10, 25, 10, 10);

        // Draw the center black circle
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x + 13, 28, 4, 4);

        // Restore the original transformation
        g2d.setTransform(originalTransform);
    }

}
