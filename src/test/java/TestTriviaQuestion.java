import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;
import group8.model.TriviaQuestion;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTriviaQuestion {

    @Test
    public void testTriviaQuestionGetters() {
        // Create a TriviaQuestion instance
        TriviaQuestion question = new TriviaQuestion(
                QuestionType.MULTIPLE,
                Difficulty.EASY,
                Category.SPORTS,
                "Which country hosted the 2018 FIFA World Cup?",
                "Russia",
                List.of("Germany", "United States", "Saudi Arabia"));

        // Test getters
        assertEquals(QuestionType.MULTIPLE, question.type());
        assertEquals(Difficulty.EASY, question.difficulty());
        assertEquals(Category.SPORTS, question.category());
        assertEquals("Which country hosted the 2018 FIFA World Cup?", question.question());
        assertEquals("Russia", question.correctAnswer());
        assertEquals(List.of("Germany", "United States", "Saudi Arabia"), question.incorrectAnswers());
    }

    @Test
    public void testTriviaQuestionEquals() {
        // Testing equals method on two identical question instances
        TriviaQuestion question1 = new TriviaQuestion(
                QuestionType.MULTIPLE,
                Difficulty.EASY,
                Category.SPORTS,
                "Which country hosted the 2018 FIFA World Cup?",
                "Russia",
                List.of("Germany", "United States", "Saudi Arabia"));

        TriviaQuestion question2 = new TriviaQuestion(
                QuestionType.MULTIPLE,
                Difficulty.EASY,
                Category.SPORTS,
                "Which country hosted the 2018 FIFA World Cup?",
                "Russia",
                List.of("Germany", "United States", "Saudi Arabia"));

        assertEquals(question1, question2);

        // Testing equals method on two different question instances
        TriviaQuestion question3 = new TriviaQuestion(
                QuestionType.BOOLEAN,
                Difficulty.HARD,
                Category.HISTORY,
                "The Berlin Wall fell in 1989.",
                "True",
                List.of("False"));

        assertNotEquals(question1, question3);
    }
}