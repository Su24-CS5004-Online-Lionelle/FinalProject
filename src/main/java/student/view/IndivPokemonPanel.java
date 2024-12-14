package student.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;
import student.GUIUtil;
import student.model.PokeRecord;
import student.controller.PokedexController;
import student.model.PokemonMoves.PokemonMove;
import student.model.PokemonTypes.PokemonType;
import student.view.Components.GridBackground;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;
import org.newdawn.easyogg.OggClip;

/** IndivPokemonPanel class that displays basic info of each Pokemon. */
public class IndivPokemonPanel extends JPanel {

    /** Controller of the Pokemon. */
    private PokedexController controller = new PokedexController();
    /** Pokemon record. */
    private PokeRecord record;
    /** An instance of the class. */
    private static IndivPokemonPanel instance;

    /** Private constructor to prevent instantiation. */
    private IndivPokemonPanel() {
        refreshPanel();
    }

    /** Public method to provide access to the instance.
     * @return IndivPokemonPanel
    */
    public static synchronized IndivPokemonPanel getInstance() {
    if (instance == null) {
        instance = new IndivPokemonPanel();
    }
    return instance;
    }

    /**
     * Create new panel.
     * 
     * @param text text that displayed in the panel
     * @param fontSize font size
     * 
     * @return a new panel
     */
    private JPanel createPanel(String text, float fontSize) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        CustomLabel label = new CustomLabel(text, fontSize);
        newPanel.add(label);
        return newPanel;
    }

    /**
     * Create a panel that contains the Pokemon image.
     * And play the cry sound when the user click the image.
     *
     * @return a panel that contains the Pokemon image and sound.
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
            /** Add the cry sound. */
            imagePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    playSound();
                }
            });
            return imagePanel;
        } catch (IOException e) {
            GUIUtil.showMessage("Error: " + e.getMessage(), "IOException");
        }
        return null;
    }

    /**
     * Get the sound url and play it.
     */
    private void playSound() {
        try {
            /** Get the sound string. */
            String soundLink = controller.getCryByName(this.record.name());
            URL soundURL = new URL(soundLink);
            InputStream in = soundURL.openStream();
            OggClip clip = new OggClip(in);
            clip.play();
        } catch (UnknownHostException e) {
            GUIUtil.showMessage("Error: " + e.getMessage(), "UnknownHostException");
        } catch (IOException ex) {
            GUIUtil.showMessage("Error: " + ex.getMessage(), "IOException");
        } catch (Exception e) {
            GUIUtil.showMessage("Error: " + e.getMessage(), "Exception");
        }
    }

    /**
     * Create a panel that contains Pokemon information.
     * Information includes id, weight, height, types and moves.
     *
     * @return a panel that contains Pokemon information.
     */
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        /** Stack info vertically. */
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JPanel idPanel = createPanel("ID: " + String.valueOf(this.record.id()) + "\n", 35);
        JPanel weightPanel = createPanel(
            "Weight: " + String.valueOf(this.record.weight()) + "\n", 35
        );
        JPanel heightPanel = createPanel(
            "Height: " + String.valueOf(this.record.height()) + "\n", 35
        );
        JPanel typesPanel = createPanel("Types: " + getTypeFromList(), 30);
        /** Create moves panel with listener. */
        JPanel movesPanel = createPanel("Moves: Click to see.", 30);
        movesPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame tableFrame = new JFrame("Moves Table");
                tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                tableFrame.setSize(1450, 600); // Set the size of the new window.
                tableFrame.setLocationRelativeTo(null); // Center the new window.

                /** Get the table and add it to the new window. */
                JScrollPane moveScrollPane = getMoveFromList();
                tableFrame.add(moveScrollPane);

                /** Make the new window visible. */
                tableFrame.setVisible(true);
            }
        });
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
        String[] typeString = (
            typeList.toString().substring(1, typeList.toString().length() - 1).split(",")
        );
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
     * @return scorll panel that contains table of move info.
     */
    private JScrollPane getMoveFromList() {
        List<PokemonMove> movesList = this.record.moves();
        String[] columnNames = {"", "Name", "Accuracy", "Power", "PP", "Damage Type", ""};
        Object[][] data = new Object[Math.min(10, movesList.size())][7];
        /** If there are more than 10 mvoes, display the first ten. */
        for (int i = 0; i < Math.min(10, movesList.size()); i++) {
            PokemonMove move = movesList.get(i);
            data[i][0] = "";
            data[i][1] = move.getMove().getName();
            data[i][2] = move.getMove().getMoveDetails().getAccuracy();
            data[i][3] = move.getMove().getMoveDetails().getPower();
            data[i][4] = move.getMove().getMoveDetails().getPp();
            data[i][5] = move.getMove().getMoveDetails().getDamageClass().getDamageType();
            data[i][6] = "";
        }
        /** Using data and column name to create the table. */
        /** And make the JTable non-editable. */
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        JTable moveTable = new JTable(tableModel);
        moveTable.setRowHeight(70); // Set the row height of the table.
        moveTable.setShowGrid(false); // Set to not show the grid.

        /** Create TableRowSorter and apply it to JTable. */
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
        moveTable.setRowSorter(sorter);

        /** Create custom cell renderer to set font and size. */
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, 
                boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                /** Set custom font and size. */
                c.setFont(PokedexView.getPokemonFont().deriveFont(30f));
                if (row % 2 == 0) {
                    c.setBackground(new Color(240, 240, 240)); // Light gray background for even rows.
                } else {
                    c.setBackground(new Color(255, 255, 255)); // White background for odd rows.   
                }
                /** Set foreground color for text. */
                c.setForeground(Color.BLACK);
                return c;
            }
        };
        /** Apply renderer to all columns and the header. */
        TableColumnModel columnModel = moveTable.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setCellRenderer(renderer);
            column.setPreferredWidth(70);
        }
        JTableHeader header = moveTable.getTableHeader();
        header.setDefaultRenderer(renderer);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 70)); // Set the height of the header.
        /** Add the table into scroll panel. */
        JScrollPane moveScrollPane = new JScrollPane(moveTable);
        return moveScrollPane;
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
            String name = getRecord().name();
            String capitalName= name.substring(0, 1).toUpperCase() + name.substring(1);
            JPanel namePanel = createPanel(capitalName, 50);
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

        } else {
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
     * @param record Pokemon record to set
     */
    public void setRecord(PokeRecord record) {
        this.record = record;
    }

    /** A private static class to make custom label. */
    private static class CustomLabel extends JPanel {

        /** Constructor of the class.
         *
         * @param text text that displayed in the panel
         * @param fontSize font size
         */
        public CustomLabel(String text, float fontSize) {
            setLayout(new BorderLayout());
            /** Make sure the background is not drawn by the JPanel itself. */
            setOpaque(false);
            JLabel label = new JLabel(text, SwingConstants.CENTER);
            /** Label itself should not be opaque. */
            label.setOpaque(false);
            label.setForeground(Color.BLACK);
            Font newFont = PokedexView.getPokemonFont().deriveFont(fontSize);
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
}
