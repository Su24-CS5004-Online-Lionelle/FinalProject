package group8.view.helpers;

import group8.controller.MainController;
import group8.model.TriviaQuestion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Action listener for saving the user collection to a file.
 */
public class SaveActionListener implements ActionListener {
    /** The list model for the user collection. */
    private DefaultListModel<TriviaQuestion> userListModel;

    /** The program's Main Controller */
    private MainController controller;

    /**
     * Constructs a SaveActionListener with the specified user list model.
     *
     * @param userListModel The list model for the user collection.
     * @param controller The controller of the application.
     */
    public SaveActionListener(DefaultListModel<TriviaQuestion> userListModel, MainController controller) {
        this.userListModel = userListModel;
        this.controller = controller;

    }

    /**
     * Invoked when an action occurs. Opens a file chooser to select a file
     * and saves the user collection to the selected file.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Captures user selected file path location
            File selectedFolder = fileChooser.getSelectedFile();
            String folderPath = selectedFolder.getAbsolutePath();

            try { // Passes file path to controller
                controller.saveTrivia(folderPath);
                JOptionPane.showMessageDialog(null, "Trivia saved successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving trivia: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
