package student.view;
import javax.swing.*;

import student.controller.PokedexController;
import student.model.PokeRecord;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.GeneralPath;
import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.List;

/**
 * Class creates the pokedex background and inputs.
 */
public class PokedexPanel extends JPanel {
    /** Instance of PokedexPanel. */
    private static PokedexPanel instance;
    /** Text input field. */
    private JTextField searchbar;
    /** Placeholder text for searchbar. */
    private JLabel placeholderLabel;
    /** Custom component to select types with dropdown menu. */
    private CheckableComboBox typeSelect;
    /** Save button. */
    private JButton saveButton;
    /** Toggle button for adding/removing pokemon from team. */
    private JToggleButton addToggleButton;
    /** Toggle button for switching between team and list panels. */
    private JToggleButton viewToggleButton;
    /** Controller to get pokemon. */
    private PokedexController controller;

    /**
     * Constructor for PokedexPanel.
     * Creates buttons, labels, searchbar, dropdown menu.
     */
    private PokedexPanel() {
        // private constructor to prevent instantiation
        controller = new PokedexController();

        // initializes search bar
        searchbar = new JTextField();
        searchbar.setBounds(565, 53, 200, 30);
        searchbar.setOpaque(true);
        searchbar.setBackground(Color.WHITE);
        searchbar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        searchbar.setForeground(Color.BLACK);

        // initializes placeholder label
        placeholderLabel = new JLabel("Search by name or ID");
        placeholderLabel.setForeground(Color.GRAY);
        placeholderLabel.setBounds(570, 53, 200, 30);
        placeholderLabel.setVisible(true);

        // manages placeholder visibility based on focus
        searchbar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                placeholderLabel.setVisible(false); // hides placeholder when focused
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchbar.getText().isEmpty()) {
                    placeholderLabel.setVisible(true); // hides placeholder if search bar is empty
                }
            }
        });

        // create add team toggle button
        addToggleButton = new JToggleButton("Add to Team");
        addToggleButton.setBounds(68, 560, 220, 30); // x, y, width, height
        addToggleButton.setForeground(Color.BLACK);
        addToggleButton.setBackground(new Color(144, 238, 144));
        this.add(addToggleButton);
        addToggleButton.setEnabled(false);
    }

    /**
     * Method gets instance.
     * @return instance of PokedexPanel
     */
    public static PokedexPanel getInstance() {
        if (instance == null) {
            instance = new PokedexPanel();
        }
        return instance;
    }

    /**
     * Initialize panel.
     */
    public void initializePanel() {
        this.setPreferredSize(new Dimension(1000, 700));
        this.setBackground(new Color(20, 20, 60));
        this.setLayout(null); // Use null layout for absolute positioning

        this.add(placeholderLabel);
        this.add(searchbar);
        this.add(addToggleButton);

        // create drop down menu for selecting types to filter by
        String[] pokemonTypes = {"Bug", "Dragon", "Electric", "Fighting", "Fire", "Flying",
                                "Ghost", "Grass", "Ground", "Ice", "Normal", "Posion",
                                "Psychic", "Rock", "Water"};
        typeSelect = new CheckableComboBox(pokemonTypes, "Type");
        typeSelect.setBounds(600, 570, 150, 30); // x, y, width, height
        typeSelect.setForeground(Color.BLACK);
        typeSelect.setBackground(Color.WHITE);
        this.add(typeSelect);

        // create save button
        saveButton = new JButton("Export Team");
        saveButton.setBounds(780, 570, 150, 30); // x, y, width, height
        saveButton.setForeground(Color.BLACK);
        saveButton.setBackground(new Color (135, 206, 250));
        this.add(saveButton);



        // create view team toggle button
        viewToggleButton = new JToggleButton("List View");
        viewToggleButton.setBounds(780, 618, 150, 30); // x, y, width, height
        viewToggleButton.setForeground(Color.BLACK);
        viewToggleButton.setBackground(new Color(235, 235, 92));
        this.add(viewToggleButton);
    }

    /**
     * Method to set fonts for all components.
     * @param font
     */
    public void setFonts(Font font) {
        searchbar.setFont(font);
        placeholderLabel.setFont(font);
        typeSelect.setFont(font);
        saveButton.setFont(font);
        addToggleButton.setFont(font);
        viewToggleButton.setFont(font);
    }

    /**
     * Method gets save button.
     * @return the save button
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * Method gets addToggleButton.
     * @return the addToggleButton
     */
    public JToggleButton getAddToggleButton() {
        return addToggleButton;
    }

    /**
     * Method refreshes addToggleButton.
     */
    public void refreshAddToggleButton() {
        PokeRecord currSelectedPokemon = PokemonListPanel.getInstance().getIsHighlited();
        JToggleButton addRemoveButton = getAddToggleButton();
        try {
            if (currSelectedPokemon != null) {
                if (controller.isPokemonInTeam(currSelectedPokemon)) {
                    addRemoveButton.setText("Remove from team");
                    addRemoveButton.setEnabled(true);
                    addRemoveButton.setSelected(true);
                    addRemoveButton.setActionCommand("Remove from Team");
                    addRemoveButton.revalidate();
                    addRemoveButton.repaint();
                } else if (!controller.isPokemonInTeam(currSelectedPokemon)) {
                    addRemoveButton.setText("Add to team");
                    addRemoveButton.setEnabled(true);
                    addRemoveButton.setSelected(false);
                    addRemoveButton.setActionCommand("Add to Team");
                    addRemoveButton.revalidate();
                    addRemoveButton.repaint();
                }
            } else {
                addRemoveButton.setText("Add to team");
                addRemoveButton.setEnabled(false);
                addRemoveButton.setSelected(false);
                addRemoveButton.revalidate();
                addRemoveButton.repaint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method gets viewToggleButton.
     * @return the viewToggleButton
     */
    public JToggleButton getViewToggleButton() {
        return viewToggleButton;
    }

    /**
     * Method gets checkableComboBox.
     * @return the checkableComboBox
     */
    public CheckableComboBox getCheckableComboBox() {
        return typeSelect;
    }

    /**
     * Method gets searchbar.
     * @return the searchbar
     */
    public JTextField getSearchbar() {
        return searchbar;
    }

    /**
     * Method gets selected types.
     * @return the list of types from combobox
     */
    public List<String> getTypes() {
        return typeSelect.getCheckedItems();
    }

    /**
     * Method gets searchbar input text.
     * @return text input string
     */
    public String getSearchbarText() {
        return searchbar.getText();
    }

    /**
     * Method draws the background.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        // create left pokedex shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(25, 40, 488, 650);

        // Create right pokedex shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(542, 40, 450, 650);

        // create right pokedex
        g2D.setColor(new Color(230, 40, 50));
        g2D.fillRect(25, 25, 488, 650);

        // create left pokedex
        g2D.setColor(new Color(230, 40, 50));
        g2D.fillRect(542, 25, 450, 650);

        // create the shadow of the top part of left pokedex
        GeneralPath topPokedexShadow = new GeneralPath();
        topPokedexShadow.moveTo(25, 25);
        topPokedexShadow.lineTo(25, 80);
        topPokedexShadow.lineTo(220, 80);
        topPokedexShadow.lineTo(280, 40);
        topPokedexShadow.lineTo(525, 40);
        topPokedexShadow.lineTo(525, 25);
        topPokedexShadow.lineTo(25, 25);
        topPokedexShadow.lineTo(100, 240);
        topPokedexShadow.closePath();

        g2D.setColor(new Color(160, 30, 40));  // Darker red for shadow effect
        g2D.fill(topPokedexShadow);  // Fill the shadow shape

        // Create top part of pokedex
        GeneralPath topPokedex = (GeneralPath) topPokedexShadow.clone();
        AffineTransform translateTransform = new AffineTransform();
        translateTransform.translate(0, -10);
        topPokedex.transform(translateTransform);
        g2D.setColor(new Color(255, 100, 100));
        g2D.fill(topPokedex);

        // create top carved out part of left pokedex
        AffineTransform flipTransform = new AffineTransform();
        flipTransform.translate(1050, 0);
        flipTransform.scale(-1, 1);
        GeneralPath mirroredPokedex = (GeneralPath) topPokedex.clone();
        mirroredPokedex.transform(flipTransform);
        g2D.setColor(new Color(20, 20, 60));
        g2D.fill(mirroredPokedex);

        // create outline shape for left pokedex
        GeneralPath leftPokedexOutline = new GeneralPath();
        leftPokedexOutline.moveTo(35, 80);
        leftPokedexOutline.lineTo(35, 665);
        leftPokedexOutline.lineTo(500, 665);
        leftPokedexOutline.lineTo(500, 40);
        leftPokedexOutline.lineTo(280, 40);
        leftPokedexOutline.lineTo(220, 80);
        leftPokedexOutline.lineTo(35, 80);
        leftPokedexOutline.closePath();

        g2D.setColor(new Color(160, 30, 40));
        g2D.setStroke(new BasicStroke(5));
        g2D.draw(leftPokedexOutline);

        // create right pokedex outline
        GeneralPath rightPokedexOutline = new GeneralPath();
        rightPokedexOutline.moveTo(553, 40);
        rightPokedexOutline.lineTo(553, 665);
        rightPokedexOutline.lineTo(980, 665);
        rightPokedexOutline.lineTo(980, 80);
        rightPokedexOutline.lineTo(825, 80);
        rightPokedexOutline.lineTo(765, 40);
        rightPokedexOutline.lineTo(553, 40);
        rightPokedexOutline.closePath();

        g2D.draw(rightPokedexOutline);

        // create hinge
        g2D.setColor(new Color(255, 100, 100));
        g2D.fillRect(512, 15, 30, 650);

        // top of hinge
        g2D.setColor(new Color(255, 100, 100));
        g2D.fillOval(512, 8, 30, 15);

        // bottom hinge shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillOval(512, 657, 30, 15);

        // hinge shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(531, 11, 8, 650);

        // hinge highlight
        g2D.setColor(new Color(255, 150, 150));
        g2D.fillRect(518, 10, 4, 646);

        // Create white sphere
        g2D.setColor(new Color(240, 240, 240));
        g2D.fill(new Ellipse2D.Double(35, 20, 45, 45));
        // Create blue sphere
        g2D.setColor(new Color(0, 0, 205));
        g2D.fill(new Ellipse2D.Double(39, 23, 37, 37));
        // Create blue highlight
        g2D.setColor(new Color(135, 206, 250, 128));
        g2D.fill(new Ellipse2D.Double(42, 25, 29, 29));
        // Create white highlight
        g2D.setColor(new Color(255, 255, 255));
        g2D.fill(new Ellipse2D.Double(53, 33, 5, 5));

        // Create small red top button
        g2D.setColor(new Color(0, 0, 0));
        g2D.fill(new Ellipse2D.Double(109, 22.5, 22, 22));
        g2D.setColor(new Color(255, 0, 0));
        g2D.fill(new Ellipse2D.Double(111.5, 24, 17, 17));
        g2D.setColor(new Color(240, 240, 240));
        g2D.fill(new Ellipse2D.Double(117, 27, 4, 4));

        // Create small yellow top button
        g2D.setColor(new Color(0, 0, 0));
        g2D.fill(new Ellipse2D.Double(149, 22.5, 22, 22));
        g2D.setColor(new Color(240, 240, 0));
        g2D.fill(new Ellipse2D.Double(151.5, 24, 17, 17));
        g2D.setColor(new Color(255, 255, 255));
        g2D.fill(new Ellipse2D.Double(157, 27, 4, 4));

        // Create small green top button
        g2D.setColor(new Color(0, 0, 0));
        g2D.fill(new Ellipse2D.Double(189, 22.5, 22, 22));
        g2D.setColor(new Color(0,240, 0));
        g2D.fill(new Ellipse2D.Double(191.5, 24, 17, 17));
        g2D.setColor(new Color(255, 255, 255));
        g2D.fill(new Ellipse2D.Double(197, 27, 4, 4));

        // Create left screen background shadow
        GeneralPath leftScreenShadow = new GeneralPath();
        leftScreenShadow.moveTo(48, 105);
        leftScreenShadow.lineTo(48, 518);
        leftScreenShadow.lineTo(70, 545);
        leftScreenShadow.lineTo(485, 545);
        leftScreenShadow.lineTo(485, 105);
        leftScreenShadow.lineTo(48, 105);
        leftScreenShadow.closePath();

        g2D.setColor(new Color(160, 30, 40));
        g2D.fill(leftScreenShadow);
        // Create left screen background
        GeneralPath leftScreen = new GeneralPath();
        leftScreen.moveTo(48, 95);
        leftScreen.lineTo(48, 508);
        leftScreen.lineTo(70, 535);
        leftScreen.lineTo(485, 535);
        leftScreen.lineTo(485, 95);
        leftScreen.lineTo(48, 95);
        leftScreen.closePath();

        g2D.setColor(new Color(240, 240, 240));
        g2D.fill(leftScreen);

        // Create left screen outline
        GeneralPath leftScreenOutline = new GeneralPath();
        leftScreenOutline.moveTo(53, 100);
        leftScreenOutline.lineTo(53, 505);
        leftScreenOutline.lineTo(480, 505);
        leftScreenOutline.lineTo(480, 100);
        leftScreenOutline.lineTo(53, 100);
        leftScreenOutline.closePath();

        g2D.setColor(new Color(0, 0, 50));
        g2D.fill(leftScreenOutline);

        // create red button bottom of screen
        g2D.setColor(new Color(0, 0, 0));
        g2D.fill(new Ellipse2D.Double(98, 508.5, 22, 22));
        g2D.setColor(new Color(255, 0, 0));
        g2D.fill(new Ellipse2D.Double(100.8, 510, 17, 17));
        g2D.setColor(new Color(240, 240, 240));
        g2D.fill(new Ellipse2D.Double(106.5, 513, 4, 4));

        // create screen vent
        g2D.setColor(new Color(0, 0, 0));
        g2D.fillRect(370, 510, 80, 3);
        g2D.fillRect(370, 518, 80, 3);
        g2D.fillRect(370, 526, 80, 3);

        // create arrow buttons shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(435, 575, 20, 70);
        g2D.fillRect(410, 600, 70, 20);
        // create arrow buttons
        g2D.setColor(new Color(20, 20, 20));
        g2D.fillRect(435, 570, 20, 70);
        g2D.fillRect(410, 595, 70, 20);

        // create right screen Shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(565, 90, 400, 450);
        // create right screen
        g2D.setColor(new Color(40, 40, 40));
        g2D.fillRect(565, 95, 400, 450);
    }
}
