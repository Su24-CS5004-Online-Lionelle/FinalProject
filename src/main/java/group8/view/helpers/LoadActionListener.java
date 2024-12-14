package group8.view.helpers;

import group8.controller.MainController;
import group8.model.TriviaQuestion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

//pass state of the category and field that is selected - add that to the load
//function

/**
 * Action listener for loading the user collection from a file.
 */
public class LoadActionListener implements ActionListener {
    /** The list model for the user collection. */
    private DefaultListModel<TriviaQuestion> userListModel;

    /** The program's main controller. */
    private final MainController controller;

    /**
     * Constructs a LoadActionListener with the specified user list model.
     *
     * @param userListModel the list model for the user collection
     */
    public LoadActionListener(DefaultListModel<TriviaQuestion> userListModel, MainController controller) {
        this.userListModel = userListModel;
        this.controller = controller;
    }

    /**
     * Invoked when an action occurs. Opens a file chooser to select a file
     * and loads the user collection from the selected file.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        // Restricts user to select JSON files only
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.
                FileNameExtensionFilter("JSON Files", "json"));

        int result = fileChooser.showOpenDialog(null);
        // Pulls in Trivia questions from loaded file.
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            try {
                List<TriviaQuestion> updatedQuestions = controller.loadTriviaQuestions(filePath);
                // adds additional questions to the view's userListModel for display.
                userListModel.clear();
                for (TriviaQuestion question : updatedQuestions) {
                    userListModel.addElement(question);
                }
                JOptionPane.showMessageDialog(null, "Load Complete!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error loading trivia: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
