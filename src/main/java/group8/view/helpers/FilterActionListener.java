package group8.view.helpers;

import group8.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for filtering questions in the trivia generator application.
 */
public class FilterActionListener implements ActionListener {
    /** The main application frame. */
    private final JFrame frame;
    /** The main view of the application. */
    private final MainView mainView;
    /** The state of the main view containing filter selections. */
    private final MainViewState state;

    /**
     * Constructs a FilterActionListener with the specified frame, main view, and state.
     *
     * @param frame the main application frame
     * @param mainView the main view of the application
     * @param state the state of the main view containing filter selections
     */
    public FilterActionListener(JFrame frame, MainView mainView, MainViewState state) {
        this.frame = frame;
        this.mainView = mainView;
        this.state = state;
    }

    /**
     * Invoked when an action occurs. Displays the filter dialog and applies the selected filters.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog filterDialog = new JDialog(frame, "Filter Options", true);
        filterDialog.setSize(300, 400);
        filterDialog.setLayout(new BorderLayout());

        JPanel filterOptionsPanel = new JPanel();
        filterOptionsPanel.setLayout(new BoxLayout(filterOptionsPanel, BoxLayout.Y_AXIS));

        // Category Filters
        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        categoryLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Add padding
        filterOptionsPanel.add(categoryLabel);

        JCheckBox categoryOption1 = new JCheckBox("Category 1");
        JCheckBox categoryOption2 = new JCheckBox("Category 2");
        JCheckBox categoryOption3 = new JCheckBox("Category 3");
        categoryOption1.setSelected(state.isCategory1Selected());
        categoryOption2.setSelected(state.isCategory2Selected());
        categoryOption3.setSelected(state.isCategory3Selected());
        filterOptionsPanel.add(categoryOption1);
        filterOptionsPanel.add(categoryOption2);
        filterOptionsPanel.add(categoryOption3);

        // Difficulty Filters
        JLabel difficultyLabel = new JLabel("Difficulty");
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        difficultyLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Add padding
        filterOptionsPanel.add(difficultyLabel);

        JCheckBox difficultyOption1 = new JCheckBox("Easy");
        JCheckBox difficultyOption2 = new JCheckBox("Medium");
        JCheckBox difficultyOption3 = new JCheckBox("Hard");
        difficultyOption1.setSelected(state.isDifficultyEasySelected());
        difficultyOption2.setSelected(state.isDifficultyMediumSelected());
        difficultyOption3.setSelected(state.isDifficultyHardSelected());
        filterOptionsPanel.add(difficultyOption1);
        filterOptionsPanel.add(difficultyOption2);
        filterOptionsPanel.add(difficultyOption3);

        // Question Type Filters
        JLabel typeLabel = new JLabel("Question Type");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        typeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Add padding
        filterOptionsPanel.add(typeLabel);

        JCheckBox typeOption1 = new JCheckBox("Multiple Choice");
        JCheckBox typeOption2 = new JCheckBox("True/False");
        typeOption1.setSelected(state.isTypeMultipleChoiceSelected());
        typeOption2.setSelected(state.isTypeTrueFalseSelected());
        filterOptionsPanel.add(typeOption1);
        filterOptionsPanel.add(typeOption2);

        JButton applyFiltersButton = new JButton("Apply Filters");
        applyFiltersButton.addActionListener(ev -> {
            // Collect filter parameters
            state.setCategory1Selected(categoryOption1.isSelected());
            state.setCategory2Selected(categoryOption2.isSelected());
            state.setCategory3Selected(categoryOption3.isSelected());
            state.setDifficultyEasySelected(difficultyOption1.isSelected());
            state.setDifficultyMediumSelected(difficultyOption2.isSelected());
            state.setDifficultyHardSelected(difficultyOption3.isSelected());
            state.setTypeMultipleChoiceSelected(typeOption1.isSelected());
            state.setTypeTrueFalseSelected(typeOption2.isSelected());

            // Logic to apply filters based on checkbox selections
            filterDialog.dispose();
        });

        filterDialog.add(filterOptionsPanel, BorderLayout.CENTER);
        filterDialog.add(applyFiltersButton, BorderLayout.SOUTH);

        filterDialog.setLocationRelativeTo(frame);
        filterDialog.setVisible(true);
    }
}
