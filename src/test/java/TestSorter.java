import group8.model.TriviaQuestion;
import group8.model.Enums.Field;
import group8.model.Enums.QuestionType;
import group8.model.Enums.Difficulty;
import group8.model.Enums.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import group8.model.APITriviaCollection;
import group8.model.ITriviaCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class TestSorter {
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
    public void testSortByDifficultyAscending() {
        List<TriviaQuestion> sortedQuestions = mainCollection.sortQuestions(Field.DIFFICULTY, true);
        assertEquals(Difficulty.EASY, sortedQuestions.get(0).difficulty());
        assertEquals(Difficulty.EASY, sortedQuestions.get(1).difficulty());
        assertEquals(Difficulty.EASY, sortedQuestions.get(2).difficulty());
        assertEquals(Difficulty.EASY, sortedQuestions.get(3).difficulty());
        assertEquals(Difficulty.MEDIUM, sortedQuestions.get(4).difficulty());
        assertEquals(Difficulty.MEDIUM, sortedQuestions.get(5).difficulty());
        assertEquals(Difficulty.MEDIUM, sortedQuestions.get(6).difficulty());
        assertEquals(Difficulty.HARD, sortedQuestions.get(7).difficulty());
        assertEquals(Difficulty.HARD, sortedQuestions.get(8).difficulty());
        assertEquals(Difficulty.HARD, sortedQuestions.get(9).difficulty());

        for (int i = 0; i < sortedQuestions.size() - 1; i++) {
            if (sortedQuestions.get(i).difficulty() == sortedQuestions.get(i + 1).difficulty()) {
                assertTrue(sortedQuestions.get(i).question().compareToIgnoreCase(sortedQuestions.get(i + 1).question()) <= 0);
            }
        }
    }

    @Test
    public void testSortByDifficultyDescending() {
        List<TriviaQuestion> sortedQuestions = mainCollection.sortQuestions(Field.DIFFICULTY, false);
        assertEquals(Difficulty.HARD, sortedQuestions.get(0).difficulty());
        assertEquals(Difficulty.HARD, sortedQuestions.get(1).difficulty());
        assertEquals(Difficulty.HARD, sortedQuestions.get(2).difficulty());
        assertEquals(Difficulty.MEDIUM, sortedQuestions.get(3).difficulty());
        assertEquals(Difficulty.MEDIUM, sortedQuestions.get(4).difficulty());
        assertEquals(Difficulty.MEDIUM, sortedQuestions.get(5).difficulty());
        assertEquals(Difficulty.EASY, sortedQuestions.get(6).difficulty());
        assertEquals(Difficulty.EASY, sortedQuestions.get(7).difficulty());
        assertEquals(Difficulty.EASY, sortedQuestions.get(8).difficulty());
        assertEquals(Difficulty.EASY, sortedQuestions.get(9).difficulty());

        for (int i = 0; i < sortedQuestions.size() - 1; i++) {
            if (sortedQuestions.get(i).difficulty() == sortedQuestions.get(i + 1).difficulty()) {
                assertTrue(sortedQuestions.get(i).question()
                        .compareToIgnoreCase(sortedQuestions.get(i + 1).question()) <= 0);
            }
        }

    }

    @Test
    public void testSortByCategoryAscending() {
        List<TriviaQuestion> sortedQuestions = mainCollection.sortQuestions(Field.CATEGORY, true);
        assertEquals(Category.ART, sortedQuestions.get(0).category());
        assertEquals(Category.ART, sortedQuestions.get(1).category());
        assertEquals(Category.GEOGRAPHY, sortedQuestions.get(2).category());
        assertEquals(Category.GEOGRAPHY, sortedQuestions.get(3).category());
        assertEquals(Category.GEOGRAPHY, sortedQuestions.get(4).category());
        assertEquals(Category.HISTORY, sortedQuestions.get(5).category());
        assertEquals(Category.HISTORY, sortedQuestions.get(6).category());
        assertEquals(Category.HISTORY, sortedQuestions.get(7).category());
        assertEquals(Category.SPORTS, sortedQuestions.get(8).category());
        assertEquals(Category.SPORTS, sortedQuestions.get(9).category());

        for (int i = 0; i < sortedQuestions.size() - 1; i++) {
            if (sortedQuestions.get(i).category() == sortedQuestions.get(i + 1).category()) {
                assertTrue(sortedQuestions.get(i).question()
                        .compareToIgnoreCase(sortedQuestions.get(i + 1).question()) <= 0);
            }
        }
    }

    @Test
    public void testSortByCategoryDescending() {
        List<TriviaQuestion> sortedQuestions = mainCollection.sortQuestions(Field.CATEGORY, false);
        assertEquals(Category.ART, sortedQuestions.get(9).category());
        assertEquals(Category.ART, sortedQuestions.get(8).category());
        assertEquals(Category.GEOGRAPHY, sortedQuestions.get(7).category());
        assertEquals(Category.GEOGRAPHY, sortedQuestions.get(6).category());
        assertEquals(Category.GEOGRAPHY, sortedQuestions.get(5).category());
        assertEquals(Category.HISTORY, sortedQuestions.get(4).category());
        assertEquals(Category.HISTORY, sortedQuestions.get(3).category());
        assertEquals(Category.HISTORY, sortedQuestions.get(2).category());
        assertEquals(Category.SPORTS, sortedQuestions.get(1).category());
        assertEquals(Category.SPORTS, sortedQuestions.get(0).category());

        for (int i = 0; i < sortedQuestions.size() - 1; i++) {
            if (sortedQuestions.get(i).category() == sortedQuestions.get(i + 1).category()) {
                assertTrue(sortedQuestions.get(i).question()
                        .compareToIgnoreCase(sortedQuestions.get(i + 1).question()) <= 0);
            }
        }
    }

    @Test
    public void testSortByTypeAscending() {
        List<TriviaQuestion> sortedQuestions = mainCollection.sortQuestions(Field.TYPE, true);
        assertEquals(QuestionType.BOOLEAN, sortedQuestions.get(0).type());
        assertEquals(QuestionType.BOOLEAN, sortedQuestions.get(1).type());
        assertEquals(QuestionType.BOOLEAN, sortedQuestions.get(2).type());
        assertEquals(QuestionType.BOOLEAN, sortedQuestions.get(3).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(4).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(5).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(6).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(7).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(8).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(9).type());

        for (int i = 0; i < sortedQuestions.size() - 1; i++) {
            if (sortedQuestions.get(i).type() == sortedQuestions.get(i + 1).type()) {
                assertTrue(sortedQuestions.get(i).question()
                        .compareToIgnoreCase(sortedQuestions.get(i + 1).question()) <= 0);
            }
        }
    }

    @Test
    public void testSortByTypeDescending() {
        List<TriviaQuestion> sortedQuestions = mainCollection.sortQuestions(Field.TYPE, false);
        assertEquals(QuestionType.BOOLEAN, sortedQuestions.get(9).type());
        assertEquals(QuestionType.BOOLEAN, sortedQuestions.get(8).type());
        assertEquals(QuestionType.BOOLEAN, sortedQuestions.get(7).type());
        assertEquals(QuestionType.BOOLEAN, sortedQuestions.get(6).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(5).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(4).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(3).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(2).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(1).type());
        assertEquals(QuestionType.MULTIPLE, sortedQuestions.get(0).type());

        for (int i = 0; i < sortedQuestions.size() - 1; i++) {
            if (sortedQuestions.get(i).type() == sortedQuestions.get(i + 1).type()) {
                assertTrue(sortedQuestions.get(i).question()
                        .compareToIgnoreCase(sortedQuestions.get(i + 1).question()) <= 0);
            }
        }
    }

}
