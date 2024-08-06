package group8.view.helpers;

/**
 * MainViewState class represents the state of filter selections in the trivia generator application.
 */
public class MainViewState {
    private boolean category1Selected = false;
    private boolean category2Selected = false;
    private boolean category3Selected = false;
    private boolean difficultyEasySelected = false;
    private boolean difficultyMediumSelected = false;
    private boolean difficultyHardSelected = false;
    private boolean typeMultipleChoiceSelected = false;
    private boolean typeTrueFalseSelected = false;

    public boolean isCategory1Selected() {
        return category1Selected;
    }

    public void setCategory1Selected(boolean category1Selected) {
        this.category1Selected = category1Selected;
    }

    public boolean isCategory2Selected() {
        return category2Selected;
    }

    public void setCategory2Selected(boolean category2Selected) {
        this.category2Selected = category2Selected;
    }

    public boolean isCategory3Selected() {
        return category3Selected;
    }

    public void setCategory3Selected(boolean category3Selected) {
        this.category3Selected = category3Selected;
    }

    public boolean isDifficultyEasySelected() {
        return difficultyEasySelected;
    }

    public void setDifficultyEasySelected(boolean difficultyEasySelected) {
        this.difficultyEasySelected = difficultyEasySelected;
    }

    public boolean isDifficultyMediumSelected() {
        return difficultyMediumSelected;
    }

    public void setDifficultyMediumSelected(boolean difficultyMediumSelected) {
        this.difficultyMediumSelected = difficultyMediumSelected;
    }

    public boolean isDifficultyHardSelected() {
        return difficultyHardSelected;
    }

    public void setDifficultyHardSelected(boolean difficultyHardSelected) {
        this.difficultyHardSelected = difficultyHardSelected;
    }

    public boolean isTypeMultipleChoiceSelected() {
        return typeMultipleChoiceSelected;
    }

    public void setTypeMultipleChoiceSelected(boolean typeMultipleChoiceSelected) {
        this.typeMultipleChoiceSelected = typeMultipleChoiceSelected;
    }

    public boolean isTypeTrueFalseSelected() {
        return typeTrueFalseSelected;
    }

    public void setTypeTrueFalseSelected(boolean typeTrueFalseSelected) {
        this.typeTrueFalseSelected = typeTrueFalseSelected;
    }
}
