package group8.view.helpers;

import group8.controller.MainController;
import group8.model.Enums;
import group8.model.TriviaQuestion;
import group8.view.MainView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * ApiPullActionListener initiates the action of pulling trivia questions from the API.
 */
public class ApiPullActionListener implements ActionListener {
    /**
     * The main view of the application.
     */
    private MainView mainView;

    /**
     * The list of selected categories to fetch questions from.
     */
    private List<Enums.Category> selectedCategories;

    /**
     * The controller to manage the interaction with the API.
     */
    private MainController controller;

    /**
     * Constructs an ApiPullActionListener with the specified main view, selected categories, and controller.
     *
     * @param mainView the main view of the application.
     * @param selectedCategories the list of selected categories to fetch questions from.
     * @param controller the controller to manage the interaction with the API.
     */
    public ApiPullActionListener(MainView mainView, List<Enums.Category> selectedCategories, MainController controller) {
        this.mainView = mainView;
        this.selectedCategories = selectedCategories;
        this.controller = controller;
    }

    /**
     * Invoked when an action occurs. Fetches trivia questions from the API and updates the main view's list model.
     *
     * @param e the event that triggered the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            controller.generateApiList(selectedCategories);
            List<TriviaQuestion> questions = controller.getFormattedApiQuestions();
            mainView.updateApiListModel(questions);

            // resets the checkboxes on new api calls
            mainView.getCheckboxState().resetFilters();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to generate questions: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

