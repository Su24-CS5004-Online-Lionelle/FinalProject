package group8.view.helpers;

import group8.model.Enums;
import group8.model.TriviaQuestion;
import group8.view.MainView;
import group8.controller.MainController;
import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

/**
 * FilterActionListener class handles the action of displaying a filter dialog
 * and applying selected filters to the trivia questions list.
 */
public class FilterActionListener implements ActionListener {
    /** The main application frame. */
    private final JFrame frame;
    /** The main view of the application. */
    private final MainView mainView;
    /** The state of the main view containing filter selections. */
    private final MainViewState state;
    /** The Main Controller of the application. */
    private final MainController controller;
   

    /**
     * Constructs a FilterActionListener with the specified frame, main view, state, and main controller.
     *
     * @param frame the main application frame
     * @param mainView the main view of the application
     * @param state the state of the main view containing filter selections
     * @param controller the main controller of the application
     */
    public FilterActionListener(JFrame frame, MainView mainView, MainViewState state, MainController controller) {
        this.frame = frame;
        this.mainView = mainView;
        this.state = state;
        this.controller = controller;
    }

    /**
     * Applies the selected filters and updates the trivia question list in the main view.
     */
    public void applyFilters() {
        Set<Enums.Category> selectedCategories = new HashSet<>();
        for (Map.Entry<String, Boolean> entry : state.getCategorySelectedMap().entrySet()) {
            if (entry.getValue()) {
                selectedCategories.add(Category.fromValue(entry.getKey()));
            }
        }

        Set<Enums.Difficulty> selectedDifficulties = new HashSet<>();
        if (state.isDifficultyEasySelected())
            selectedDifficulties.add(Difficulty.EASY);
        if (state.isDifficultyMediumSelected())
            selectedDifficulties.add(Difficulty.MEDIUM);
        if (state.isDifficultyHardSelected())
            selectedDifficulties.add(Difficulty.HARD);

        Set<Enums.QuestionType> selectedTypes = new HashSet<>();
        if (state.isTypeMultipleChoiceSelected())
            selectedTypes.add(QuestionType.MULTIPLE);
        if (state.isTypeTrueFalseSelected())
            selectedTypes.add(QuestionType.BOOLEAN);

        List<TriviaQuestion> questions = controller.getFormattedApiQuestions(selectedTypes, selectedDifficulties, selectedCategories);
        mainView.updateApiListModel(questions);
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

        Set<Enums.Category> uniqueCategories = controller.getAllCategories();

        if (uniqueCategories.isEmpty()) {
            JLabel noCategoriesLabel = new JLabel("No categories available");
            filterOptionsPanel.add(noCategoriesLabel);
        } else {
            for (Enums.Category category : uniqueCategories) {
                JCheckBox categoryOption = new JCheckBox(category.getValue());
                categoryOption.setSelected(state.isCategorySelected(category.getValue()));
                categoryOption.addActionListener(ev -> state.setCategorySelected(category.getValue(), categoryOption.isSelected()));
                filterOptionsPanel.add(categoryOption);
            }
        }

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
        difficultyOption1.addActionListener(ev -> state.setDifficultyEasySelected(difficultyOption1.isSelected()));
        difficultyOption2.addActionListener(ev -> state.setDifficultyMediumSelected(difficultyOption2.isSelected()));
        difficultyOption3.addActionListener(ev -> state.setDifficultyHardSelected(difficultyOption3.isSelected()));
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
        typeOption1.addActionListener(ev -> state.setTypeMultipleChoiceSelected(typeOption1.isSelected()));
        typeOption2.addActionListener(ev -> state.setTypeTrueFalseSelected(typeOption2.isSelected()));
        filterOptionsPanel.add(typeOption1);
        filterOptionsPanel.add(typeOption2);

        JButton applyFiltersButton = new JButton("Apply Filters");
        applyFiltersButton.addActionListener(ev -> {
            applyFilters();
            filterDialog.dispose();
        });

        filterDialog.add(filterOptionsPanel, BorderLayout.CENTER);
        filterDialog.add(applyFiltersButton, BorderLayout.SOUTH);

        filterDialog.setLocationRelativeTo(frame);
        filterDialog.setVisible(true);
    }
}

