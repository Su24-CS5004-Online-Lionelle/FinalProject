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
import student.view.Components.ListItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EventController implements ActionListener, ItemListener, KeyListener {

    private PokedexView pokedexView;
    private PokedexController controller = new PokedexController();
    private PokemonListPanel listPanel = PokemonListPanel.getInstance();
    private List<PokeRecord> currRecords;

    public EventController(PokedexView view) {
        this.pokedexView = view;
        view.setListeners(this);
        view.setItemListeners(this);
        view.setKeyListeners(this);
        addMouseListeners();
    }

    // Listening for list/team item clicks
    public void addMouseListeners() {
        this.pokedexView.addMouseListenerToListItems(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ListItem listItem = (ListItem) e.getSource();
                try {
                    handlePokemonClick(listItem.getCurrPokemon()); // Here you see how you can get the clicked pokemon name
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    // Method that runs when pokemon in list is clicked. Says which pokemon was clicked.
    public void handlePokemonClick(PokeRecord pokemonName) throws IOException {
        System.out.println("Clicked on: " + pokemonName.name());
    }

    // Listening for button clicks.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Export Team":
                handleExportTeam();
                break;
            case "Add to Team":
                PokeRecord recordToAdd = listPanel.getIsHighlited();
                controller.addPokemonToTeam(recordToAdd);

                listPanel.refreshPanel(currRecords);
                PokedexPanel.getInstance().refreshAddToggleButton();
                try {
                    PokemonTeamPanel.getInstance().refreshPanel();

                } catch (IOException ex) {
                    ex.printStackTrace();
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

    // Here I was seeing if the combobox was working properly, but it doesn't
    // seem to want to print out the list WITH the item that was just selected,
    // so we'll have to figure that out.
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            SwingUtilities.invokeLater(() -> {
                // Get selected types from the view
                List<String> checkedItems = pokedexView.getTypes();

                // Debug: Print checked items to verify the selection
                System.out.println("Selected Types: " + checkedItems);

                try {
                    // Get all Pokémon as a fallback
                    List<PokeRecord> records = controller.getAllPokemon();

                    if (checkedItems.isEmpty()) {
                        // If no types are selected, display all Pokémon
                        System.out.println("No types selected, showing all Pokémon");
                        PokemonListPanel.getInstance().refreshPanel(records);
                    } else {
                        // Filter records by selected types
                        List<PokeRecord> filteredRecords = controller.filterByTypes(checkedItems);

                        // Debug: Print filtered records count
                        System.out.println("Filtered Records Count: " + filteredRecords.size());

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


    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        return;
    }
    // this is the one that seems to work for the search bar.
    // The other two aren't getting the last letter typed for some reason.
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
