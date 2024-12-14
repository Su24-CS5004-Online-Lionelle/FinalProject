package group8.view.helpers;

import group8.model.TriviaQuestion;

import javax.swing.*;
import java.awt.*;

/**
 * QuestionRenderer is a custom ListCellRenderer for displaying TriviaQuestion objects in a JList.
 * It formats and styles the display of each trivia question in the list.
 */
public class QuestionRenderer extends JLabel implements ListCellRenderer<TriviaQuestion> {

    /**
     * Constructs a QuestionRenderer and sets the label to be opaque.
     */
    public QuestionRenderer() {
        setOpaque(true);
    }

    /**
     * Returns a component configured to display the specified trivia question.
     *
     * @param list         the JList.
     * @param question     the trivia question to display.
     * @param index        the cell index.
     * @param isSelected   true if the specified cell is selected.
     * @param cellHasFocus true if the specified cell has the focus.
     * @return a component whose paint() method will render the specified value.
     */
    @Override
    public Component getListCellRendererComponent(
            JList<? extends TriviaQuestion> list,
            TriviaQuestion question,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        // Customize how the question is displayed
        String displayText = String.format("<html>" +
                        "<b>Category:</b> %s  |  <b>Difficulty:</b> %s  |  <b>Type:</b> %s <br/>" +
                        "<span style='font-size:11px;'>%s</span><br/><br/>" +
                        "</html>",
                formatCategory(question.category().toString()),
                formatDifficulty(question.difficulty().toString()),
                formatType(question.type().toString()),
                question.question());
        setText(displayText);

        // Customize the background and foreground colors based on selection
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }

    /**
     * Formats the category string by capitalizing the first letter and making the rest lowercase.
     *
     * @param category the category string to format.
     * @return the formatted category string.
     */
    private String formatCategory(String category) {
        return category.substring(0, 1).toUpperCase() + category.substring(1).toLowerCase();
    }

    /**
     * Formats the difficulty string by capitalizing the first letter and making the rest lowercase.
     *
     * @param difficulty the difficulty string to format.
     * @return the formatted difficulty string.
     */
    private String formatDifficulty(String difficulty) {
        return difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1).toLowerCase();
    }

    /**
     * Formats the type string by capitalizing the first letter and making the rest lowercase.
     *
     * @param type the type string to format.
     * @return the formatted type string.
     */
    private String formatType(String type) {
        return type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
    }
}

