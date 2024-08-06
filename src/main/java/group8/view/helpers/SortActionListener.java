package group8.view.helpers;

import group8.model.TriviaQuestion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for sorting the user collection based on the selected criteria.
 */
public class SortActionListener implements ActionListener {
    /** The list model for the user collection. */
    private DefaultListModel<TriviaQuestion> userListModel;

    /**
     * Constructs a SortActionListener with the specified user list model.
     *
     * @param userListModel the list model for the user collection
     */
    public SortActionListener(DefaultListModel<TriviaQuestion> userListModel) {
        this.userListModel = userListModel;
    }

    /**
     * Invoked when an action occurs. Sorts the user collection based on the selected criteria.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
        String selectedSort = (String) comboBox.getSelectedItem();
        // Logic to sort the user collection based on selectedSort
    }
}
