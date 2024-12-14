package student.view;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import student.controller.PokedexController;
import student.model.PokeRecord;
import student.view.Components.GridBackground;
import student.view.Components.ListItem;

import java.awt.*;

/**
 * Class handles the pokemon list panel, initializes and refreshes panel.
 */
public class PokemonListPanel extends JPanel {
    /** Instance of pokemonListPanel. */
    private static PokemonListPanel instance;
    /** pokedexController for getting pokemon. */
    PokedexController controller = new PokedexController();
    /** customRectList: list of items in panel. */
    List<ListItem> customRectList = new ArrayList<>();
    /** ListPanel: panel to hold list items. */
    JPanel listPanel = new JPanel();
    /** Variable to keep track of highlighted pokemon. */
    PokeRecord highlightedPokemon;
    /** List of current records. */
    List<PokeRecord> currRecords = new ArrayList<>();

    /**
     * Constructor for pokemon list panel.
     */
    private PokemonListPanel() {
        // Initialization code here
    }

    /**
     * Method provides access to instance.
     * @return PokemonListPanel instance
     */
    public static synchronized PokemonListPanel getInstance() {
        if (instance == null) {
            instance = new PokemonListPanel();
        }
        return instance;
    }

    /**
     * Refreshes the panel, removes current content and calls initialize again.
     */
    public void refreshPanel(List<PokeRecord> records) {
        // Remove all existing components
        this.removeAll();
        this.customRectList.clear();
        this.listPanel.removeAll();

        // Reinitialize components (assuming the constructor logic is in a method called initializeComponents)
        if (records == null) {
            records = controller.getAllPokemon();
        }

        initializePanel(records);

        // Revalidate and repaint to update the UI
        this.revalidate();
        this.repaint();
    }

    /**
     * Initialize pokemon list based on input.
     */
    public void initializePanel(List<PokeRecord> records) {
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        System.out.println(records.size());
        for (PokeRecord pokemon : records) {
            ListItem listItem = new ListItem(pokemon);
            if (highlightedPokemon != null && pokemon.name().equals(highlightedPokemon.name())) {
                listItem.highlightPanel();
            }
            listItem.setPreferredSize(new Dimension(400, 100)); // Set fixed size for each ListItem
            listItem.setMaximumSize(new Dimension(400, 100)); // Ensure max height is 100
            customRectList.add(listItem);
        }

        for (ListItem item : customRectList) {
            item.setPreferredSize(new Dimension(getWidth(), 100));
            listPanel.add(item);
        }

        if (records.size() == 0) {
            // Set GridBackground as the background
            GridBackground gridBackground = new GridBackground();
            gridBackground.setLayout(new BorderLayout());
            gridBackground.add(listPanel, BorderLayout.CENTER);

            listPanel.setOpaque(false);

            // Create a JPanel to show the message
            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new BorderLayout());
            JLabel messageLabel = new JLabel("No pokemon found", JLabel.CENTER);
            messagePanel.setOpaque(false);
            messageLabel.setFont(PokedexView.getPokemonFont().deriveFont(30f));
            messagePanel.add(messageLabel, BorderLayout.CENTER);


            // Add the messagePanel to the center of gridBackground
            gridBackground.add(messagePanel, BorderLayout.CENTER);


            JScrollPane scrollPane = new JScrollPane(gridBackground);
            scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            // Remove all components from the main panel and add the scrollPane
            this.removeAll();
            this.add(scrollPane, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();

        } else {
            // Validate and repaint to ensure layout is updated
            listPanel.revalidate();
            listPanel.repaint();

            JScrollPane scrollPane = new JScrollPane(listPanel);
            // Set custom scrolling increments
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setUnitIncrement(16); // Adjust this value as needed
            verticalScrollBar.setBlockIncrement(50); // Adjust this value as needed
            verticalScrollBar.setPreferredSize(new Dimension(0, 0));
            if (records.size() < 4) {
                scrollPane.setPreferredSize(new Dimension(400, records.size() * 100));
            } else {
                scrollPane.setPreferredSize(new Dimension(400, 400)); // Fixed size for more than 4 items
            }

            this.add(scrollPane, BorderLayout.CENTER);

            // Revalidate and repaint the main panel to update the UI
            scrollPane.revalidate();
            scrollPane.repaint();
            this.revalidate();
            this.repaint();
        }
    }

    /**
     * Sets the highlighted pokemon field.
     */
    public void setIsHighlited(PokeRecord pokemon) {
        highlightedPokemon = pokemon;
    }

    /**
     * Returns the highlighted pokemon.
     *
     * @return PokeRecord highlighted Pokemon
     */
    public PokeRecord getIsHighlited() {
        return highlightedPokemon;
    }

    /** Gets the custom rect list.
     *
     * @return returns a List<ListItem>
     */
    public List<ListItem> getItemList() {
        return customRectList;
    }

    /** Gets current records of pokemon displayed.
     *
     * @return a list of PokeRecords
     */
    public List<PokeRecord> getCurrRecords() {
        return currRecords;
    }
}
