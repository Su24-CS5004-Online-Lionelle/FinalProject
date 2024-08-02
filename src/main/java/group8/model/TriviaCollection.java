package group8.model;
import group8.model.Enums.Field;
import group8.model.helpers.Filters;
import group8.model.helpers.QuestionSortStrategy;
import group8.model.helpers.Sorter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Abstract class representing a collection of trivia questions.
 */
public abstract class TriviaCollection implements ITriviaCollection {
    /**
     * The collection of trivia questions.
     */
    private TreeSet<TriviaQuestion> originalCollection;

    private List<TriviaQuestion> listToSort;

    /**
     * Default constructor that initializes an empty collection of trivia questions.
     */
    public TriviaCollection() {
        this.originalCollection = new TreeSet<>(Comparator.comparing(TriviaQuestion::question, 
                String.CASE_INSENSITIVE_ORDER));

        this.listToSort = new ArrayList<>(originalCollection);

    }

    /**
     * Constructor that initializes the collection with a given set of trivia
     * questions.
     *
     * @param questions the initial collection of trivia questions to be added to
     *                  this collection
     */
    public TriviaCollection(Collection<TriviaQuestion> questions) {
        this.originalCollection = new TreeSet<>(Comparator.comparing(TriviaQuestion::question, 
                String.CASE_INSENSITIVE_ORDER));
        this.originalCollection.addAll(questions);

        this.listToSort = new ArrayList<>(originalCollection);
    }

     /**
     * Get all trivia questions in the collection.
     *
     * @return a set of TriviaQuestion
     */
    public Set<TriviaQuestion> getAllQuestions() {
        return originalCollection;
    };

    /**
     * Add a trivia question to the collection.
     *
     * @param question the trivia question to add
     */
    public void addQuestion(TriviaQuestion question) {
        originalCollection.add(question);
    };

    /**
     * Remove a trivia question from the collection.
     *
     * @param question the trivia question to remove
     */
    public void removeQuestion(TriviaQuestion question) {
        originalCollection.remove(question);
    }

    /**
     * Filter the trivia questions in the collection based on a filter.
     *
     * @param filter the filter criteria
     */
    public Set<TriviaQuestion> filterQuestions(Filters filters) {
        return filters.applyFilters(originalCollection);
    }   

    /**
     * Sort the trivia questions in the collection based on a sort criterion.
     *
     * @param sort the sort criterion
     */
    public List<TriviaQuestion> sortQuestions(Sorter sort) {
       return sort.sortCollection(originalCollection);
    }

    /**
     * Reset the trivia question collection to its initial state.
     */
    public void reset() {
        originalCollection = new TreeSet<>();
    }

}
