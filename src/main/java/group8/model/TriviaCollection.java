package group8.model;

import group8.model.Enums.Field;
import group8.model.helpers.Filters;
import group8.model.helpers.Sorter;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Abstract class representing a collection of trivia questions.
 */
public abstract class TriviaCollection implements ITriviaCollection {
    /**
     * The collection of trivia questions.
     */
    protected Set<TriviaQuestion> originalCollection;

    /**
     * Default constructor that initializes an empty collection of trivia questions.
     */
    public TriviaCollection() {
        this.originalCollection = new TreeSet<>(Comparator.comparing(TriviaQuestion::question,
                String.CASE_INSENSITIVE_ORDER));
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
    }

    /**
     * Get all trivia questions in the collection.
     *
     * @return a set of TriviaQuestion
     */
    public Set<TriviaQuestion> getAllQuestions() {
        return originalCollection;
    }

    /**
     * Add a trivia question to the collection.
     *
     * @param question the trivia question to add
     */
    public void addQuestion(TriviaQuestion question) {
        originalCollection.add(question);
    }

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
     * @param filters the filter criteria
     * @return a set of filtered TriviaQuestion
     */
    public Set<TriviaQuestion> filterQuestions(Filters filters) {
        return filters.applyFilters(originalCollection);
    }

    /**
     * Sort the trivia questions in the collection based on a sort criterion.
     *
     * @param field     The field to sort by.
     * @param ascending If true, sorts in ascending order; if false, in descending
     *                  order.
     * @return a list of sorted TriviaQuestion
     */
    public List<TriviaQuestion> sortQuestions(Field field, boolean ascending) {
        List<TriviaQuestion> sortedList = originalCollection.stream()
                .sorted(Sorter.getSort(field, ascending))
                .collect(Collectors.toList());
        return sortedList;
    }

    /**
     * Reset the trivia question collection to its initial state.
     */
    public void reset() {
        originalCollection.clear();
    }

    /**
     * Check if the collection contains a specific trivia question.
     *
     * @param question the trivia question to check
     * @return true if the question is in the collection, false otherwise
     */
    public boolean contains(TriviaQuestion question) {
        return originalCollection.contains(question);
    }
}
