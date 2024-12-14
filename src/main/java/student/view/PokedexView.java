package student.view;
import javax.swing.*;

import student.controller.PokedexController;

import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.IOException;

/**
 * Class creates all the panels and adds them together, passes input info to controller.
 */
public class PokedexView extends JFrame implements IPokedexView {
    /** pokedexPanel to get background and buttons. */
    private PokedexPanel pokedexPanel;
    /** indivPokemonPanel to show individual pokemon info. */
    private IndivPokemonPanel indivPokemonPanel;
    /** pokemonListPanel to display list of pokemon. */
    private PokemonListPanel pokemonListPanel;
    /** pokemonTeamPanel to display team list. */
    private PokemonTeamPanel pokemonTeamPanel;
    /** variable to store pokemon font. */
    private static Font pokemonFont;
    /** pokedexController to get pokemon. */
    private PokedexController controller = new PokedexController();

    /**
     * Constructor for PokedexView.
     * Create instances of panels and add them to layered pane, sets fonts.
     * @throws IOException
     */
    public PokedexView() throws IOException {
        // try to get pokemon font from resources, and set fonts in pokedexPanel
        try {
            pokemonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/gbboot.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pokemonFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            pokemonFont = new Font("Courier New", Font.BOLD, 20);
        }

        // initialize panels
        pokedexPanel = PokedexPanel.getInstance();
        pokedexPanel.initializePanel();
        indivPokemonPanel = IndivPokemonPanel.getInstance();
        // indivPokemonPanel.setRecord(null);
        pokemonListPanel = PokemonListPanel.getInstance();
        pokemonListPanel.initializePanel(controller.getAllPokemon());
        pokemonTeamPanel = PokemonTeamPanel.getInstance();
        // create layered pane to put all panels together
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 700));
        layeredPane.setMinimumSize(new Dimension(1000, 700));
        // Set background color of the layered pane
        layeredPane.setBackground(new Color(20, 20, 60));
        layeredPane.setOpaque(true);
        // set position and size of each panel
        pokedexPanel.setBounds(0, 0, 1000, 700);
        indivPokemonPanel.setBounds(53, 100, 427, 405);
        pokemonListPanel.setBounds(570, 100, 390, 440);
        pokemonTeamPanel.setBounds(570, 100, 390, 440);
        // add each panel to the layered pane
        layeredPane.add(pokedexPanel, Integer.valueOf(1)); // Lower layer
        layeredPane.add(indivPokemonPanel, Integer.valueOf(2)); // Higher layer
        layeredPane.add(pokemonListPanel, Integer.valueOf(2));
        layeredPane.add(pokemonTeamPanel, Integer.valueOf(2));

        // setting fonts after all panels have been added
        pokedexPanel.setFonts(pokemonFont);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(layeredPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        viewToggleButtonListener();
    }

    /**
     * Method listens for click and changes the visibility of pokemonTeamPanel and individualPokemonPanel,
     * and makes sure the correct text is displayed on the button depending on button status.
     */
    private void viewToggleButtonListener() {
        pokedexPanel.getViewToggleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pokedexPanel.getViewToggleButton().isSelected()) {
                    pokemonListPanel.setVisible(false);
                    pokemonTeamPanel.setVisible(true);
                    pokedexPanel.getViewToggleButton().setText("Team View");
                    try {
                        pokemonTeamPanel.refreshPanel();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    pokemonListPanel.setVisible(true);
                    pokemonTeamPanel.setVisible(false);
                    pokedexPanel.getViewToggleButton().setText("List View");
                }
            }
        });
    }

    /**
     * Method sets the listener for buttons.
     * @param clicks
     */
    public void setListeners(ActionListener clicks) {
        this.pokedexPanel.getSaveButton().addActionListener(clicks);
        this.pokedexPanel.getAddToggleButton().addActionListener(clicks);
        this.pokedexPanel.getViewToggleButton().addActionListener(clicks);
    }

    /**
     * Method sets the Item listener for checkableComboBox.
     * @param clicks
     */
    public void setItemListeners(ItemListener clicks) {
        this.pokedexPanel.getCheckableComboBox().addItemListener(clicks);
    }

    /**
     * Method sets the key listeners for searchbar.
     * @param press
     */
    public void setKeyListeners(KeyListener press) {
        this.pokedexPanel.getSearchbar().addKeyListener(press);
    }

    /**
     * Method gets the selected types from pokedexPanel.
     * @return list of types
     */
    public List<String> getTypes() {
        return pokedexPanel.getTypes();
    }

    /**
     * Method gets the inputted string in the searchbar in pokedexPanel.
     * @return searchbar input string
     */
    public String getSearchbarText() {
        return pokedexPanel.getSearchbarText();
    }

    /**
     * Gets the pokemon font.
     *
     * @return returns pokemon font
     */
    public static Font getPokemonFont() {
        return pokemonFont;
    }
}
