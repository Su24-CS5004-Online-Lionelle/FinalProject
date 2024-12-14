package student.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import student.model.PokeRecord;
import student.view.IndivPokemonPanel;
import student.view.PokedexPanel;
import student.view.PokedexView;
import student.view.PokemonListPanel;
import student.view.PokemonTeamPanel;

import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * The EventController class provides methods to handle user interactions.
 */
public class EventController implements ActionListener, ItemListener, KeyListener {
    /** PokedexView to receive information from buttons/input. */
    private PokedexView pokedexView;
    /** Controller for getting pokemon or making changes to team. */
    private PokedexController controller = new PokedexController();
    /** listPanel to make changes to list. */
    private PokemonListPanel listPanel = PokemonListPanel.getInstance();
    /** Variable to keep track of current records. */
    private List<PokeRecord> currRecords;

    /**
     * Constructs an EventController with the given PokedexView.
     *
     * @param view The view to handle user interactions.
     */
    public EventController(PokedexView view) {
        this.pokedexView = view;
        view.setListeners(this);
        view.setItemListeners(this);
        view.setKeyListeners(this);
    }

    /** Method listens for button clicks and executes correct code. */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Export Team":
                handleExportTeam();
                break;
            
            case "Add to Team":
                PokeRecord recordToAdd = listPanel.getIsHighlited();
                try {
                    controller.addPokemonToTeam(recordToAdd);
                    listPanel.refreshPanel(currRecords);
                    PokedexPanel.getInstance().refreshAddToggleButton();
                    PokemonTeamPanel.getInstance().refreshPanel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pokedexView, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            
            case "Remove from Team":
                PokeRecord recordToRemove = PokemonListPanel.getInstance().getIsHighlited();

                // unhighlight all pokemon
                listPanel.getItemList().forEach(item -> item.unhighlight());
                controller.removePokemonFromTeam(recordToRemove);
                IndivPokemonPanel.getInstance().setRecord(listPanel.getIsHighlited());
                IndivPokemonPanel.getInstance().refreshPanel();
                listPanel.refreshPanel(currRecords);
                PokedexPanel.getInstance().refreshAddToggleButton();
                try {
                    PokemonTeamPanel.getInstance().refreshPanel();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    /**
     * Handles exporting the Pokémon team to a JSON file.
     * Can select the name and location of the file to be saved.
     */
    private void handleExportTeam() {
        SwingUtilities.invokeLater(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export Pokémon Team");

            // Set the default file filter to JSON files
            fileChooser.setFileFilter(new javax.swing.filechooser
                        .FileNameExtensionFilter("JSON files (*.json)", "json"));
            fileChooser.setAcceptAllFileFilterUsed(false);

            int userSelection = fileChooser.showSaveDialog(pokedexView);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    controller.ExportTeamToFile(filePath);
                    JOptionPane.showMessageDialog(pokedexView,
                                                    "Team exported successfully!",
                                                    "Success",
                                                    JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(pokedexView,
                                                    "Failed to export team: " + ex.getMessage(),
                                                    "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    /*
     * Listens for the changes in the selected types in the PokedexView.
     * 
     * @param e The ItemEvent object that contains the event details.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            SwingUtilities.invokeLater(() -> {
                // Get selected types from the view
                List<String> checkedItems = pokedexView.getTypes();

                try {
                    // Get all Pokémon as a fallback
                    List<PokeRecord> records = controller.getAllPokemon();

                    if (checkedItems.isEmpty()) {
                        // Update the list panel with all Pokémon
                        PokemonListPanel.getInstance().refreshPanel(records);
                    } else {
                        // Filter records by selected types
                        List<PokeRecord> filteredRecords = controller.filterByTypes(checkedItems);

                        // Update the list panel with the filtered list of Pokémon
                        currRecords = filteredRecords;
                        PokemonListPanel.getInstance().refreshPanel(filteredRecords);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    // not used.
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }
    // not used.
    @Override
    public void keyPressed(KeyEvent e) {
        return;
    }

    /**
     * Method listens for key releases and gets the input from the search bar
     * and filters pokemon.
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        String input = pokedexView.getSearchbarText();
        PokemonListPanel listPanel = PokemonListPanel.getInstance();
        try {
            List<PokeRecord> records = controller.filterByContains(input); // searchs both name & ID
            listPanel.refreshPanel(records);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(input);
    }
}
