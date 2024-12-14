package group8.model.helpers;

import java.util.Set;
import java.util.stream.Collectors;
import group8.model.TriviaQuestion;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;
import group8.model.TriviaCollection;

import group8.model.Enums.Category;



/**
 * Filters class provides methods to filter TriviaQuestion objects based on
 * different criteria.
 */
public final class Filters {

    private Set<QuestionType> typeFilters;
    private Set<Difficulty> difficultyFilters;
    private Set<Category> categoryFilters;
    
    /**
     * Constructs a Filters object with specified sets of type, difficulty, and category filters.
     *
     * @param typeFilters       the set of question types to filter by
     * @param difficultyFilters  the set of difficulties to filter by
     * @param categoryFilters   the set of categories to filter by
     */
    public Filters(Set<QuestionType> typeFilters, Set<Difficulty> difficultyFilters, Set<Category> categoryFilters) {
        this.typeFilters = typeFilters;
        this.difficultyFilters = difficultyFilters;
        this.categoryFilters = categoryFilters;

    }

    /**
     * Applies the filters to a set of trivia questions and returns the filtered set.
     *
     * @param questions the set of trivia questions to filter
     * @return a set of trivia questions that match the filters
     */
    public Set<TriviaQuestion> applyFilters(Set<TriviaQuestion> questions) {
        return questions.stream()
                .filter(this::matchesType)
                .filter(this::matchesDifficulty)
                .filter(this::matchesCategory)
                .collect(Collectors.toSet());
    }

    /**
     * Checks if a trivia question matches the type filters.
     *
     * @param question the trivia question to check
     * @return true if the question matches the type filters, false otherwise
     */
    private boolean matchesType(TriviaQuestion question) {
        return typeFilters.isEmpty() || typeFilters.contains(question.type());
    }

    /**
     * Checks if a trivia question matches the difficulty filters.
     *
     * @param question the trivia question to check
     * @return true if the question matches the difficulty filters, false otherwise
     */
    private boolean matchesDifficulty(TriviaQuestion question) {
        return difficultyFilters.isEmpty() || difficultyFilters.contains(question.difficulty());
    }

    /**
     * Checks if a trivia question matches the category filters.
     *
     * @param question the trivia question to check
     * @return true if the question matches the category filters, false otherwise
     */
    private boolean matchesCategory(TriviaQuestion question) {
        return categoryFilters.isEmpty() || categoryFilters.contains(question.category());
    }

   
}
