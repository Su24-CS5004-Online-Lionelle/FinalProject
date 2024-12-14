import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import group8.model.APITriviaCollection;
import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.Field;
import group8.model.Enums.QuestionType;
import group8.model.ITriviaCollection;
import group8.model.TriviaQuestion;
import group8.model.helpers.Filters;

import org.junit.jupiter.api.BeforeAll;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFilters {
    private static ITriviaCollection mainCollection;
   
    @BeforeAll //only initializing collection once
    public static void setUpOnce() {
        TriviaQuestion question1 = new TriviaQuestion(
            QuestionType.BOOLEAN,
            Difficulty.HARD,
            Category.HISTORY,
            "The Berlin Wall fell in 1989.",
            "True",
            List.of("False"));

    TriviaQuestion question2 = new TriviaQuestion(
            QuestionType.MULTIPLE,
            Difficulty.EASY,
            Category.SPORTS,
            "Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997?",
            "Mike Tyson",
            List.of("Roy Jones Jr.", "Evander Holyfield", "Lennox Lewis"));

    TriviaQuestion question3 = new TriviaQuestion(
            QuestionType.MULTIPLE,
            Difficulty.MEDIUM,
            Category.ART,
            "The painting 'The Starry Night' by Vincent van Gogh was part of which art movement?",
            "Post-Impressionism",
            List.of("Romanticism", "Neoclassical", "Impressionism"));

    TriviaQuestion question4 = new TriviaQuestion(
            QuestionType.BOOLEAN,
            Difficulty.MEDIUM,
            Category.GEOGRAPHY,
            "Tokyo is the capital of Japan.",
            "True",
            List.of("False"));

    TriviaQuestion question5 = new TriviaQuestion(
            QuestionType.MULTIPLE,
            Difficulty.EASY,
            Category.HISTORY,
            "Who was the first President of the United States?",
            "George Washington",
            List.of("Thomas Jefferson", "John Adams", "James Madison"));

    TriviaQuestion question6 = new TriviaQuestion(
            QuestionType.BOOLEAN,
            Difficulty.EASY,
            Category.ART,
            "Leonardo da Vinci painted the Mona Lisa.",
            "True",
            List.of("False"));

    TriviaQuestion question7 = new TriviaQuestion(
            QuestionType.MULTIPLE,
            Difficulty.HARD,
            Category.GEOGRAPHY,
            "What is the capital of Iceland?",
            "Reykjavik",
            List.of("Oslo", "Helsinki", "Stockholm"));

    TriviaQuestion question8 = new TriviaQuestion(
            QuestionType.MULTIPLE,
            Difficulty.MEDIUM,
            Category.SPORTS,
            "Which NBA player won Most Valuable Player for the 1999-2000 season?",
            "Shaquille O'Neal",
            List.of("Allen Iverson", "Kobe Bryant", "Paul Pierce"));

    TriviaQuestion question9 = new TriviaQuestion(
            QuestionType.MULTIPLE,
            Difficulty.HARD,
            Category.HISTORY,
            "In what year did the Titanic sink?",
            "1912",
            List.of("1905", "1915", "1920"));

    TriviaQuestion question10 = new TriviaQuestion(
            QuestionType.BOOLEAN,
            Difficulty.EASY,
            Category.GEOGRAPHY,
            "Australia is both a country and a continent.",
            "True",
            List.of("False"));

        // Create a set of TriviaQuestion objects
    Set<TriviaQuestion> questions = new HashSet<>();

    questions.add(question1);
    questions.add(question2);
    questions.add(question3);
    questions.add(question4);
    questions.add(question5);
    questions.add(question6);
    questions.add(question7);
    questions.add(question8);
    questions.add(question9);
    questions.add(question10);

    mainCollection = new APITriviaCollection(questions); 
    }
 

    @Test
    public void testFiltersOneEachField() {

        Set<QuestionType> typeFilters = Set.of(QuestionType.BOOLEAN);
        Set<Difficulty> difficultyFilters = Set.of(Difficulty.HARD);
        Set<Category> categoryFilters = Set.of(Category.HISTORY);

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = mainCollection.filterQuestions(filters);

        assertEquals(filteredSet.size(), 1);
    }

    @Test
    public void testNoFilters() {

        Set<QuestionType> typeFilters = Set.of();
        Set<Difficulty> difficultyFilters = Set.of();
        Set<Category> categoryFilters = Set.of();

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = mainCollection.filterQuestions(filters);

        assertEquals(filteredSet.size(), 10);
    }

    @Test
    public void testAllFilters() {

        Set<QuestionType> typeFilters = Set.of(QuestionType.MULTIPLE, QuestionType.BOOLEAN);
        Set<Difficulty> difficultyFilters = Set.of(Difficulty.HARD, Difficulty.EASY, Difficulty.MEDIUM);
        Set<Category> categoryFilters = Set.of(Category.SPORTS, Category.ART, Category.CELEBRITIES, Category.GEOGRAPHY, Category.HISTORY);

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = mainCollection.filterQuestions(filters);

        assertEquals(filteredSet.size(), 10);
    }

    @Test
    public void testOneDifficultyFieldFilter() {
        Set<QuestionType> typeFilters = Set.of();
        Set<Difficulty> difficultyFilters = Set.of(Difficulty.HARD);
        Set<Category> categoryFilters = Set.of();

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = mainCollection.filterQuestions(filters);

        assertEquals(filteredSet.size(), 3);

        for (TriviaQuestion question : filteredSet) {
            assertEquals(Difficulty.HARD, question.difficulty());
        }
    }

    @Test
    public void testOneTypeField() {
        Set<QuestionType> typeFilters = Set.of(QuestionType.MULTIPLE);
        Set<Difficulty> difficultyFilters = Set.of();
        Set<Category> categoryFilters = Set.of();

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = mainCollection.filterQuestions(filters);

        assertEquals(filteredSet.size(), 6);

        for (TriviaQuestion question : filteredSet) {
            assertEquals(QuestionType.MULTIPLE, question.type());
        }
    }

    @Test
    public void testTwoDifficultyFilters() {
        Set<QuestionType> typeFilters = Set.of();
        Set<Difficulty> difficultyFilters = Set.of(Difficulty.HARD, Difficulty.EASY);
        Set<Category> categoryFilters = Set.of();

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = mainCollection.filterQuestions(filters);

        assertEquals(filteredSet.size(), 7);
    }

    @Test
    public void testThreeDifficultyFilters() {
        Set<QuestionType> typeFilters = Set.of();
        Set<Difficulty> difficultyFilters = Set.of(Difficulty.HARD, Difficulty.EASY, Difficulty.MEDIUM);
        Set<Category> categoryFilters = Set.of();

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = mainCollection.filterQuestions(filters);

        assertEquals(filteredSet.size(), 10);
    }

    @Test
    public void testNoMatching() {
        Set<QuestionType> typeFilters = Set.of();
        Set<Difficulty> difficultyFilters = Set.of(Difficulty.MEDIUM);
        Set<Category> categoryFilters = Set.of(Category.HISTORY);

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = mainCollection.filterQuestions(filters);

        assertEquals(filteredSet.size(), 0);
    }

    @Test
    public void testVarietyOfFilters() {
        Set<QuestionType> typeFilters = Set.of(QuestionType.MULTIPLE);
        Set<Difficulty> difficultyFilters = Set.of(Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EASY);
        Set<Category> categoryFilters = Set.of(Category.HISTORY, Category.ART);

        Filters filters = new Filters(typeFilters, difficultyFilters, categoryFilters);
        Set<TriviaQuestion> filteredSet = mainCollection.filterQuestions(filters);

        assertEquals(filteredSet.size(), 3);
    }

  
    
}
