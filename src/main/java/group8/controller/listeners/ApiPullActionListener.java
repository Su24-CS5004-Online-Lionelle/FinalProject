package group8.controller.listeners;

import group8.model.Enums;
import group8.model.TriviaQuestion;
import group8.model.helpers.APIUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Action listener for pulling questions from an API and adding them to the API list model.
 */
public class ApiPullActionListener implements ActionListener {
    /** The list model for the API questions. */
    private DefaultListModel<String> apiListModel;
    private List<Enums.Category> selectedCategories;

    /**
     * Constructs an ApiPullActionListener with the specified API list model.
     *
     * @param apiListModel the list model for the API questions
     * @param selectedCategories
     */
    public ApiPullActionListener(DefaultListModel<String> apiListModel, List<Enums.Category> selectedCategories) {
        this.apiListModel = apiListModel;
        this.selectedCategories = selectedCategories;
    }

    /**
     * Invoked when an action occurs. Pulls questions from the API and adds them to the API list model.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Ensure the session token and categories are initialized
            if (APIUtils.getSessionToken() == null) {
                APIUtils.requestToken();
            }

            if (APIUtils.getCategoryMap() == null) {
                APIUtils.requestCategories();
            }

            // Fetch questions from the selected categories
            apiListModel.clear();
            for (Enums.Category category : selectedCategories) {
                List<TriviaQuestion> questions = APIUtils.getBatchedQuestions(10, category, null, null);
                for (TriviaQuestion question : questions) {
                    apiListModel.addElement(question.toString());
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to pull questions: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
