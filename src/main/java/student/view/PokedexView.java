package student.view;
import javax.swing.*;

import student.controller.PokedexController;
import student.model.PokeRecord;

import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.io.IOException;

public class PokedexView extends JFrame implements IPokedexView {
    private PokedexPanel pokedexPanel;
    private IndivPokemonPanel indivPokemonPanel;
    private PokemonListPanel pokemonListPanel;
    private PokemonTeamPanel pokemonTeamPanel;
    private Font pokemonFont;
    private PokedexController controller = new PokedexController();

    public PokedexView() throws IOException {
        // initialize panels
        pokedexPanel = new PokedexPanel();
        indivPokemonPanel = IndivPokemonPanel.getInstance();
        // indivPokemonPanel.setRecord(null);
        pokemonListPanel = PokemonListPanel.getInstance();
        pokemonListPanel.initializePanel(controller.getAllPokemon());
        pokemonTeamPanel = new PokemonTeamPanel();
        // create layered pane to put all panels together
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 700));
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

        // try to get pokemon font from resources, and set fonts in pokedexPanel
        try {
            pokemonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/gbboot.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pokemonFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            pokemonFont = new Font("Courier New", Font.BOLD, 20);
        }
        pokedexPanel.setFonts(pokemonFont);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(layeredPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        viewToggleButtonListener();
        addToggleButtonListener();
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
                } else {
                    pokemonListPanel.setVisible(true);
                    pokemonTeamPanel.setVisible(false);
                    pokedexPanel.getViewToggleButton().setText("List View");
                }
            }
        });
    }

    /**
     * Method listens for click and makes sure addToggleButton changes text with its status.
     */
    private void addToggleButtonListener() {
        pokedexPanel.getAddToggleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pokedexPanel.getAddToggleButton().isSelected()) {
                    pokedexPanel.getAddToggleButton().setText("Remove from Team");
                } else {
                    pokedexPanel.getAddToggleButton().setText("Add to Team");
                }
            }
        });
    }

    /**
     * Method adds mouse listener to list items.
     * @param mouseAdapter
     */
    public void addMouseListenerToListItems(MouseAdapter mouseAdapter) {
        pokemonListPanel.addMouseListenerToListItems(mouseAdapter);
        pokemonTeamPanel.addMouseListenerToListItems(mouseAdapter);
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
     * Method gets the status of addToggleButton.
     * If it's False, the button says "Add to Team." If True, it says "Remove from Team."
     * @return boolean
     */
    public boolean getAddToggleButtonStatus() {
        return pokedexPanel.getAddToggleButton().isSelected();
    }

    /**
     * Method sets the status of AddToggleButton.
     * Setting to False sets the button to "Add to Team" status.
     * Setting to True sets the burron to "Remove from Team" status.
     * @param status
     */
    public void setAddToggleButtonStatus(boolean status) {
        pokedexPanel.getAddToggleButton().setSelected(status);
        if (status) {
            pokedexPanel.getAddToggleButton().setText("Remove from Team");
        } else {
            pokedexPanel.getAddToggleButton().setText("Add to Team");
        }
    }

    /**
     * Method updates the pokemon list by passing in new list of pokerecords.
     * @param pokeRecords
     */
    public void updatePokemonListPanel(List<PokeRecord> pokeRecords) {
        pokemonListPanel.updatePanel(pokeRecords);
    }

    /**
     * Method updates the pokemon team by passing in new list of pokerecords.
     * @param pokeRecords
     */
    public void updatePokemonTeamPanel(List<PokeRecord> pokeRecords) {
        pokemonListPanel.updatePanel(pokeRecords);
    }

    /**
     * Method gets the selected types from pokedexPanel.
     * @return
     */
    public List<String> getTypes() {
        return pokedexPanel.getTypes();
    }

    /**
     * Method gets the inputted string in the searchbar in pokedexPanel.
     * @return
     */
    public String getSearchbarText() {
        return pokedexPanel.getSearchbarText();
    }
}
