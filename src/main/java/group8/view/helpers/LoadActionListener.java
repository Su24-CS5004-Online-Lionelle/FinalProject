package group8.view.helpers;

import group8.model.TriviaQuestion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for loading the user collection from a file.
 */
public class LoadActionListener implements ActionListener {
    /** The list model for the user collection. */
    private DefaultListModel<TriviaQuestion> userListModel;

    /**
     * Constructs a LoadActionListener with the specified user list model.
     *
     * @param userListModel the list model for the user collection
     */
    public LoadActionListener(DefaultListModel<TriviaQuestion> userListModel) {
        this.userListModel = userListModel;
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
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Logic to load the user collection from the selected file
        }
    }
}
