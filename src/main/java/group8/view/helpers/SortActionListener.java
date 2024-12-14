package group8.view.helpers;

import group8.controller.MainController;
import group8.model.TriviaQuestion;
import group8.view.MainView;
import group8.model.Enums;
import group8.model.Enums.Field;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Action listener for sorting the user collection based on the selected
 * criteria.
 * Handles sorting when a sort field is selected from a JComboBox and toggles
 * between ascending and descending order using a JToggleButton.
 */
public class SortActionListener implements ActionListener {

    private MainController controller;
    private MainView mainView;
    private MainViewState state;
    
    /**
     * Constructs a SortActionListener with the provided controller, main view, and
     * state.
     *
     * @param controller the main controller of the application
     * @param mainView   the main view of the application
     * @param state      the current state of filter checkboxes, sort selection, and toggle.
     */
    public SortActionListener(MainController controller, MainView mainView, MainViewState state) {
        this.controller = controller;
        this.mainView = mainView;
        this.state = state;
    }

    /**
     * Invoked when an action occurs. Determines whether the source of the action
     * was a JComboBox or a JToggleButton, and delegates to the appropriate handler.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
            handleSortAction((JComboBox<String>) e.getSource());
        } else if (e.getSource() instanceof JToggleButton) {
            handleToggleAction((JToggleButton) e.getSource());
        }
    }

    /**
     * Handles sorting when a sort field is selected from the JComboBox.
     * Updates the selected sort field in the MainViewState and sorts the list.
     *
     * @param comboBox the JComboBox from which the sort field is selected
     */
    private void handleSortAction(JComboBox<String> comboBox) {
        String selectedSort = (String) comboBox.getSelectedItem();
        Enums.Field currentSortField = SortMapping.sortLabelMap.get(selectedSort);
        state.setSortSelected(currentSortField);
        sortUserList();
    }

    /**
     * Handles toggling the sort order when the JToggleButton is clicked.
     * Flips the sort direction in MainViewState and updates the button's text
     * to reflect the new direction.
     *
     * @param toggleButton the JToggleButton that toggles the sort direction
     */
    private void handleToggleAction(JToggleButton toggleButton) {
        boolean isAscending = toggleButton.getText().equals("\u2191"); // "\u2191" is the up arrow
        state.setSortDirection(!isAscending);
        toggleButton.setText(!isAscending ? "\u2191" : "\u2193"); // "\u2193" is the down arrow
        sortUserList();
    }


    /**
     * Sorts the user list based on the currently selected sort field and direction.
     * Retrieves the current sort field and direction from the MainViewState,
     * sorts the list accordingly, and updates the view with the sorted list.
     */
    public void sortUserList() {
        // Get the current sort field and direction from MainViewState
        Field currentSortField = state.getSortSelected();
        System.out.println();
        boolean isAscending = state.getSortDirection();

        List<TriviaQuestion> questions = controller.getFormattedUserQuestions(currentSortField, isAscending);
        mainView.updateUserListModel(questions);
    }


    /**
     * Utility class for mapping sort field labels to their corresponding Field
     * enums.
     */
    private static class SortMapping {
        /**
         * A map that associates sort field labels with their corresponding Field enums.
         */
        private static final Map<String, Enums.Field> sortLabelMap = new HashMap<>();

        static {
            sortLabelMap.put("Category", Field.CATEGORY);
            sortLabelMap.put("Difficulty", Field.DIFFICULTY);
            sortLabelMap.put("Question Type", Field.TYPE);
        }
    }
}
