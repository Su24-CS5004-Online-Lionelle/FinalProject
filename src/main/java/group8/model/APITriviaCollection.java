package group8.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class APITriviaCollection extends TriviaCollection{
    private Set<TriviaQuestion> apiCollection;

    /**
     * Default constructor that initializes an empty collection of trivia questions.
     */
    public APITriviaCollection() {
        super();
        this.apiCollection = this.originalCollection;
    }

    /**
     * Constructor that initializes the collection with a given set of trivia
     * questions.
     *
     * @param questions the initial collection of trivia questions to be added to
     *                  this collection
     */
    public APITriviaCollection(Collection<TriviaQuestion> questions) {
        super(questions);
        this.apiCollection = this.originalCollection;
    }

    /**
     * Add a trivia questions to the collection.
     *
     * @param questions the trivia questions to add
     */
    @Override
    public void addQuestions(Collection<TriviaQuestion> questions) {
        apiCollection.addAll(questions);
    }

    public Set<Enums.Category> getAllCategories() {
        Set<Enums.Category> categories = new HashSet<>();
        for (TriviaQuestion question : apiCollection) {
            categories.add(question.category());
        }
        return categories;
    }

}
