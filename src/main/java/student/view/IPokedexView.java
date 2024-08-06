package student.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.util.List;

import student.model.PokeRecord;

public interface IPokedexView {
    /**
     * Adds listener to items/pokemon in list.
     * @param mouseAdapter
     */
    void addMouseListenerToListItems(MouseAdapter mouseAdapter);

    /**
     * Method sets action listener to buttons.
     * @param clicks
     */
    void setListeners(ActionListener clicks);

    /**
     * Method adds item listener to combobox.
     * @param clicks
     */
    void setItemListeners(ItemListener clicks);

    /**
     * Method sets key listener to text input.
     * @param press
     */
    void setKeyListeners(KeyListener press);

    /**
     * Method gets the current status of the "add to team" button.
     * (In case the controller needs to check).
     * @return
     */
    boolean getAddToggleButtonStatus();

    /**
     * Method sets the status of the "add to team" button. False sets it to "add to team."
     * (So the controller can set it to "add" or "remove" depending on
     * if the selected pokemon is in the team or not).
     * @param status
     */
    void setAddToggleButtonStatus(boolean status);

    /**
     * Method takes a list of pokeRecords and updates the pokemon list.
     * @param pokeRecords
     */
    void updatePokemonListPanel(List<PokeRecord> pokeRecords);

    /**
     * Method takes a list of pokeRecords and updates the pokemon team.
     * @param pokeRecords
     */
    void updatePokemonTeamPanel(List<PokeRecord> pokeRecords);

    /**
     * Method gets the selected types from the CheckableCombobox.
     * @return
     */
    List<String> getTypes();

    /**
     * Method gets the inputted text from the search bar.
     * @return
     */
    String getSearchbarText();
}
