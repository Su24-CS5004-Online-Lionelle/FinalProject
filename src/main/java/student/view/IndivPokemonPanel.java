package student.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import student.GUIUtil;
import student.model.PokeRecord;
import student.controller.PokedexController;
import student.model.PokemonMoves.PokemonMove;
import student.model.PokemonTypes.PokemonType;
import student.view.Components.GridBackground;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IndivPokemonPanel extends JPanel {

    private PokedexController controller = new PokedexController();
    private PokeRecord record;
    private static IndivPokemonPanel instance;

    // Private constructor to prevent instantiation
    private IndivPokemonPanel() {
        initializeComponents();
    }

    // Public method to provide access to the instance
    public static synchronized IndivPokemonPanel getInstance() {
    if (instance == null) {
        instance = new IndivPokemonPanel();
    }
    return instance;
}


    /**
     * Create new panel.
     * @param text text that displayed in the panel
     * @param fontSize font size
     * @param mouseListener mouse listener
     * @return a new panel
     */
    private JPanel createPanel(String text, int fontSize, MouseAdapter mouseListener) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        CustomLabel label = new CustomLabel(text, fontSize);
        if (mouseListener != null) {
            label.addMouseListener(mouseListener);
        }
        newPanel.add(label);
        return newPanel;
    }

    /**
     * Create a panel that contains the pokemon image.
     *
     * @return a panel that contains the pokemon image
     */
    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel();
        try {
            /** Convert the link string to URL format. */
            URL imageURL = new URL(this.record.sprites().getFrontDefault());
            Image image = ImageIO.read(imageURL);
            /** Set width and height. */
            int width = 200;
            int height = 200;
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(imageIcon);
            imagePanel.add(imageLabel);
            return imagePanel;
        } catch (MalformedURLException e) {
            GUIUtil.showMessage("Error: Unvalid image link.", "MalformedURLException");
        } catch (IOException e) {
            GUIUtil.showMessage("Error: " + e.getMessage(), "IOException");
        }
        return null;
    }

    /**
     * Create a panel that contains pokemon information.
     * Information includes id, weight, height, types and moves.
     *
     * @return a panel that contains pokemon information.
     */
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        /** Stack info vertically. */
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JPanel idPanel = createPanel("ID: " + String.valueOf(this.record.id()) + "\n", 35, null);
        JPanel weightPanel = createPanel("Weight: " + String.valueOf(this.record.weight()) + "\n", 35, null);
        JPanel heightPanel = createPanel("Height: " + String.valueOf(this.record.height()) + "\n", 35, null);
        JPanel typesPanel = createPanel("Types: " + getTypeFromList(), 30, null);
        /** Create moves panel with listener. */
        JPanel movesPanel = createPanel(
            "Moves: Click to see.",
            30,
            new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GUIUtil.showMessage("Moves:\n" + getMoveFromList(), "Moves");
            }
            }
        );
        infoPanel.add(idPanel);
        infoPanel.add(weightPanel);
        infoPanel.add(heightPanel);
        infoPanel.add(typesPanel);
        infoPanel.add(movesPanel);
        return infoPanel;
    }

    /**
     * Get type info from the list that contains type info.
     *
     * @return string of type info.
     */
    private String getTypeFromList() {
        List<PokemonType> typeList = this.record.types();
        /** Get name of each type. */
        String[] typeString = typeList.toString().substring(1, typeList.toString().length() - 1).split(",");
        String newTypeString = "";
        /** If there are more than 2 types, display the first two. */
        for (int i = 0; i < Math.min(2, typeString.length); i++) {
            newTypeString = newTypeString + typeString[i] + ", ";
        }
        return newTypeString.substring(0, newTypeString.length() - 2);
    }

    /**
     * Get move info from the list that contains move info.
     *
     * @return string of move info.
     */
    private String getMoveFromList() {
        List<PokemonMove> movesList = this.record.moves();
        StringBuilder moveName = new StringBuilder();
        /** If there are more than 10 mvoes, display the first ten. */
        for (int i = 0; i < Math.min(10, movesList.size()); i++) {
            String moveDetails = movesList.get(i).toString().replace("{", "").replace("}", "");
            moveName.append(i + 1 + ". " + moveDetails + "\n");
        }
        String newMoveString = moveName.toString().substring(0, moveName.toString().length() - 1);
        return newMoveString;
    }

    /**
     * Method to start displaying the panel.
     */
    public void start() {
        this.setVisible(true);
    }

    /** A private static class to make custom label. */
    private static class CustomLabel extends JPanel {

        /** Constructor of the class.
         *
         * @param text text that displayed in the panel
         * @param fontSize font size
         */
        public CustomLabel(String text, int fontSize) {
            setLayout(new BorderLayout());
            /** Make sure the background is not drawn by the JPanel itself. */
            setOpaque(false);
            JLabel label = new JLabel(text, SwingConstants.CENTER);
            /** Label itself should not be opaque. */
            label.setOpaque(false);
            label.setForeground(Color.BLACK);
            Font newFont = new Font("Calibri", Font.BOLD, fontSize);
            label.setFont(newFont);
            add(label, BorderLayout.CENTER);
            /** Set preferred size for the custom label. */
            setPreferredSize(new Dimension(400, 100)); // Adjust the size as needed
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
            super.paintComponent(g);
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
            g2d.dispose();
        }
    }

    /**
     * Refreshed the panel, removes current content and calls initialize again.
     */
    public void refreshPanel() {
        // Remove all existing components
        this.removeAll();

        // Reinitialize components (assuming the constructor logic is in a method called initializeComponents)
        initializeComponents();

        // Revalidate and repaint to update the UI
        this.revalidate();
        this.repaint();
    }


    /**
     * Sets up the screen.
     */
    public void initializeComponents() {
        if (record != null) {
            /** Set layout manager for the main panel. */
            this.setLayout(new BorderLayout());
            /** Create the name panel. */
            JPanel namePanel = createPanel(getRecord().name(), 50, null);
            /** Create the image panel. */
            JPanel imagePanel = createImagePanel();
            /** Create the info panel. */
            JPanel infoPanel = createInfoPanel();
            /** Create scrollable panel. */
            JPanel scrollablePanel = new JPanel();
            /** Create background panel. */
            JPanel gridPanel = new GridBackground();

            scrollablePanel.setLayout(new BorderLayout());
            scrollablePanel.add(namePanel, BorderLayout.NORTH);
            scrollablePanel.add(imagePanel, BorderLayout.CENTER);
            scrollablePanel.add(infoPanel, BorderLayout.SOUTH);
            /** Make the scrollable panel transparent. */
            scrollablePanel.setOpaque(false);
            // scrollablePanel.setBackground(new Color(0, 0, 0, 0));
            /** Wrap the scrollable panel in a JScrollPane. */
            JScrollPane scrollPane = new JScrollPane(scrollablePanel);
            scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default border
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);

            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setUnitIncrement(16); // Adjust this value as needed
            verticalScrollBar.setBlockIncrement(50); // Adjust this value as needed

            /** Create a JLayeredPane to layer the panels. */
            JLayeredPane layeredPane = new JLayeredPane() {
                @Override
                public void doLayout() {
                    super.doLayout();
                    gridPanel.setBounds(0, 0, getWidth(), getHeight());
                    scrollPane.setBounds(0, 0, getWidth(), getHeight());
                }
            };

            /** Add the background panel at the default layer (0). */
            layeredPane.add(gridPanel, JLayeredPane.DEFAULT_LAYER);

            /** Add the scroll pane at a higher layer (1). */
            layeredPane.add(scrollPane, JLayeredPane.PALETTE_LAYER);

            /** Add the layered pane to the main panel. */
            this.add(layeredPane, BorderLayout.CENTER);
            /** Set the panel to be initially invisible. */
            // this.setVisible(false);
        } else {
            // GUIUtil.showMessage("Error: " + e.getMessage(), "IOException");
            // Show grid panel if no pokemon is selected
            this.setLayout(new BorderLayout());
            JPanel gridPanel = new GridBackground();
            this.add(gridPanel, BorderLayout.CENTER);
            this.setVisible(true);
        }
    }

    /**
     * Gets the current record.
     *
     * @return PokeRecord
     */
    public PokeRecord getRecord() {
        return this.record;
    }


    /**
     * Sets the record field.
     *
     * @param record pokemon record to set
     */
    public void setRecord(PokeRecord record) {
        this.record = record;
    }
}
