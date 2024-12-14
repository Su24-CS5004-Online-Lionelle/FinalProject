package group8;

import group8.controller.MainController;
import group8.view.MainView;

import javax.swing.*;

/**
 * TriviaApp is the entry point for the Trivia Generator application.
 * It initializes the main controller and main view of the application.
 */
public class TriviaApp {

    /**
     * The main method to start the application.
     *
     * @param args command-line arguments (not used). 
     */
    public static void main(String[] args) {
        // Ensure GUI creation and updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                MainController controller = new MainController();
                new MainView(controller);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}