package student.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.util.List;

public interface IPokedexView {

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
