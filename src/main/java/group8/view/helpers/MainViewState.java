package group8.view.helpers;

import java.util.HashMap;
import java.util.Map;

import group8.model.Enums;
import group8.model.Enums.Field;

/**
 * MainViewState class represents the state of filter selections in the trivia
 * generator application.
 */
public class MainViewState {
    /** A map to store the selected state of categories. */
    private Map<String, Boolean> categorySelectedMap;
    /** Boolean flag indicating if 'Easy' difficulty is selected. */
    private boolean difficultyEasySelected = false;
    /** Boolean flag indicating if 'Medium' difficulty is selected. */
    private boolean difficultyMediumSelected = false;
    /** Boolean flag indicating if 'Hard' difficulty is selected. */
    private boolean difficultyHardSelected = false;
    /** Boolean flag indicating if 'Multiple Choice' type is selected. */
    private boolean typeMultipleChoiceSelected = false;
    /** Boolean flag indicating if 'True/False' type is selected. */
    private boolean typeTrueFalseSelected = false;
    /** Field Enum indicating which field is selected to sort by. */
    private Enums.Field sortSelected = Field.CATEGORY;
    /** Boolean flag indicating which direction of sort is selected. */
    private boolean sortDirection = true;

    /**
     * Constructs a MainViewState object and initializes the category selection map.
     */
    public MainViewState() {
        categorySelectedMap = new HashMap<>();
    }

    /**
     * Checks if a category is selected.
     *
     * @param category the category to check
     * @return true if the category is selected, false otherwise
     */
    public boolean isCategorySelected(String category) {
        return categorySelectedMap.getOrDefault(category, false);
    }

    /**
     * Sets the selected state of a category.
     *
     * @param category the category to set
     * @param selected the selected state
     */
    public void setCategorySelected(String category, boolean selected) {
        categorySelectedMap.put(category, selected);
    }

    /**
     * Checks if the 'Easy' difficulty is selected.
     *
     * @return true if 'Easy' difficulty is selected, false otherwise
     */
    public boolean isDifficultyEasySelected() {
        return difficultyEasySelected;
    }

    /**
     * Sets the selected state of 'Easy' difficulty.
     *
     * @param difficultyEasySelected the selected state
     */
    public void setDifficultyEasySelected(boolean difficultyEasySelected) {
        this.difficultyEasySelected = difficultyEasySelected;
    }

    /**
     * Checks if the 'Medium' difficulty is selected.
     *
     * @return true if 'Medium' difficulty is selected, false otherwise
     */
    public boolean isDifficultyMediumSelected() {
        return difficultyMediumSelected;
    }

    /**
     * Sets the selected state of 'Medium' difficulty.
     *
     * @param difficultyMediumSelected the selected state
     */
    public void setDifficultyMediumSelected(boolean difficultyMediumSelected) {
        this.difficultyMediumSelected = difficultyMediumSelected;
    }

    /**
     * Checks if the 'Hard' difficulty is selected.
     *
     * @return true if 'Hard' difficulty is selected, false otherwise
     */
    public boolean isDifficultyHardSelected() {
        return difficultyHardSelected;
    }

    /**
     * Sets the selected state of 'Hard' difficulty.
     *
     * @param difficultyHardSelected the selected state
     */
    public void setDifficultyHardSelected(boolean difficultyHardSelected) {
        this.difficultyHardSelected = difficultyHardSelected;
    }

    /**
     * Checks if the 'Multiple Choice' type is selected.
     *
     * @return true if 'Multiple Choice' type is selected, false otherwise
     */
    public boolean isTypeMultipleChoiceSelected() {
        return typeMultipleChoiceSelected;
    }

    /**
     * Sets the selected state of 'Multiple Choice' type.
     *
     * @param typeMultipleChoiceSelected the selected state
     */
    public void setTypeMultipleChoiceSelected(boolean typeMultipleChoiceSelected) {
        this.typeMultipleChoiceSelected = typeMultipleChoiceSelected;
    }

    /**
     * Checks if the 'True/False' type is selected.
     *
     * @return true if 'True/False' type is selected, false otherwise
     */
    public boolean isTypeTrueFalseSelected() {
        return typeTrueFalseSelected;
    }

    /**
     * Sets the selected state of 'True/False' type.
     *
     * @param typeTrueFalseSelected the selected state
     */
    public void setTypeTrueFalseSelected(boolean typeTrueFalseSelected) {
        this.typeTrueFalseSelected = typeTrueFalseSelected;
    }

    /**
     * Gets the field that is currently selected to be sorted on.
     * 
     * @return the field that is currently selected to be sorted on.
     */
    public Enums.Field getSortSelected() {
        return sortSelected;
    }

    /**
     * Sets the field that is selected to be sorted on.
     * 
     * @param sortSelected
     */
    public void setSortSelected(Enums.Field sortSelected) {
        this.sortSelected = sortSelected;
    }

    /**
     * Gets the boolean representing the sort direction selected.
     * True for ascending, False for descending.
     * 
     * @return boolean representing the sort direction selected.
     */
    public boolean getSortDirection() {
        return sortDirection;
    }

    /**
     * Sets the boolean representing the sort direction selected.
     * True for ascending, False for descending.
     * 
     * @param sortDirection
     */
    public void setSortDirection(boolean sortDirection) {
        this.sortDirection = sortDirection;
    }

    /**
     * Gets the map of category selection states.
     *
     * @return the map of category selection states
     */
    public Map<String, Boolean> getCategorySelectedMap() {
        return categorySelectedMap;
    }

    /**
     * Resets all filter selections to false.
     */
    public void resetFilters() {
        for (String category : categorySelectedMap.keySet()) {
            categorySelectedMap.put(category, false);
        }
        difficultyEasySelected = false;
        difficultyMediumSelected = false;
        difficultyHardSelected = false;
        typeMultipleChoiceSelected = false;
        typeTrueFalseSelected = false;
    }
    
}
