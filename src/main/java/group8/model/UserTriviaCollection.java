package group8.model;

import java.util.*;

public class UserTriviaCollection extends TriviaCollection {
    private Set<TriviaQuestion> userCollection;

    /**
     * Default constructor that initializes an empty collection of trivia questions.
     */
    public UserTriviaCollection() {
        super();
        this.userCollection = this.originalCollection;
    }

    /**
     * Constructor that initializes the collection with a given set of trivia
     * questions.
     *
     * @param questions the initial collection of trivia questions to be added to
     *                  this collection
     */
    public UserTriviaCollection(Collection<TriviaQuestion> questions) {
        super(questions);
        this.userCollection = this.originalCollection;
    }

    /**
     * Add a trivia questions to the collection.
     *
     * @param questions the trivia questions to add
     */
    @Override
    public void addQuestions(Collection<TriviaQuestion> questions) {
        userCollection.addAll(questions);
    }

}
