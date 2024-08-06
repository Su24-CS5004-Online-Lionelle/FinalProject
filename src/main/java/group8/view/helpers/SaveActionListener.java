package group8.view.helpers;

import group8.model.TriviaQuestion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for saving the user collection to a file.
 */
public class SaveActionListener implements ActionListener {
    /** The list model for the user collection. */
    private DefaultListModel<TriviaQuestion> userListModel;

    /**
     * Constructs a SaveActionListener with the specified user list model.
     *
     * @param userListModel the list model for the user collection
     */
    public SaveActionListener(DefaultListModel<TriviaQuestion> userListModel) {
        this.userListModel = userListModel;
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
            // Logic to save the user collection to the selected file
        }
    }
}
