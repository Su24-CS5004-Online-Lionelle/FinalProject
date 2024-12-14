import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;
import group8.model.TriviaQuestion;
import group8.model.FileUtilities;

class FileUtilitiesTest {

    // Stores list of questions
    private List<TriviaQuestion> testQuestions;
    // Buffer to capture output
    private ByteArrayOutputStream consoleOutput;
    // Used to reset print stream
    private final PrintStream originalOut = System.out;

    @TempDir
    Path tempDir;

    // Setting up a list of trivia questions.
    @BeforeEach
    public void setUp() {
        TriviaQuestion question1 = new TriviaQuestion(
                QuestionType.BOOLEAN, Difficulty.HARD, Category.HISTORY,
                "The Berlin Wall fell in 1989.", "True", List.of("False"));

        TriviaQuestion question2 = new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.EASY, Category.SPORTS,
                "Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997?",
                "Mike Tyson", List.of("Roy Jones Jr.", "Evander Holyfield", "Lennox Lewis"));

        TriviaQuestion question3 = new TriviaQuestion(
                QuestionType.MULTIPLE, Difficulty.MEDIUM, Category.ART,
                "The painting 'The Starry Night' by Vincent van Gogh was part of which art movement?",
                "Post-Impressionism", List.of("Romanticism", "Neoclassical", "Impressionism"));

        testQuestions = Arrays.asList(question1, question2, question3);

        // captures output
        consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
    }

    // resets output after each test
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testSaveTrivia() throws IOException {
        // creates temporary file paths for testing
        Path jsonPath = tempDir.resolve("test.json");
        Path questionsPath = tempDir.resolve("test_questions.txt");
        Path answersPath = tempDir.resolve("test_answers.txt");

        FileUtilities.saveTrivia(testQuestions,
                jsonPath.toString(),
                questionsPath.toString(),
                answersPath.toString());

        // Read the contents of the files for verification
        String jsonContent = Files.readString(jsonPath);
        String questionsContent = Files.readString(questionsPath);
        String answersContent = Files.readString(answersPath);

        // Temporarily restore the original output stream
        System.setOut(originalOut);
       System.out.println(questionsContent);
       System.out.println(answersContent);
       System.out.println(jsonContent);

        // Verify questions output
        assertTrue(questionsContent.contains("TRIVIA QUESTIONS:"));
        assertTrue(questionsContent.contains("1. The Berlin Wall fell in 1989"));
        assertTrue(questionsContent.contains("2. Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997"));
        assertTrue(questionsContent.contains("3. The painting 'The Starry Night' by Vincent van Gogh was part of which art movement"));

        // Verify answers output
        assertTrue(answersContent.contains("TRIVIA ANSWERS:"));
        assertTrue(answersContent.contains("1. Correct Answer: True"));
        assertTrue(answersContent.contains("   Incorrect Answers: False"));
        assertTrue(answersContent.contains("2. Correct Answer: Mike Tyson"));
        assertTrue(answersContent.contains("   Incorrect Answers: Roy Jones Jr., Evander Holyfield, Lennox Lewis"));
        assertTrue(answersContent.contains("3. Correct Answer: Post-Impressionism"));
        assertTrue(answersContent.contains("   Incorrect Answers: Romanticism, Neoclassical, Impressionism"));

        // Verify JSON output for one question1
        assertTrue(jsonContent.contains("\"type\" : \"boolean\""));
        assertTrue(jsonContent.contains("\"difficulty\" : \"hard\""));
        assertTrue(jsonContent.contains("\"category\" : \"history\""));
        assertTrue(jsonContent.contains("\"question\" : \"The Berlin Wall fell in 1989.\""));
        assertTrue(jsonContent.contains("\"correctAnswer\" : \"True\""));
    }

    @Test
    void testSaveTriviaNull() throws IOException {
        // creates temporary file paths for testing
        Path jsonPath = tempDir.resolve("test.json");
        Path questionsPath = tempDir.resolve("test_questions.txt");
        Path answersPath = tempDir.resolve("test_answers.txt");

        testQuestions = null;

        assertThrows(IllegalArgumentException.class, () -> {
            FileUtilities.saveTrivia(testQuestions,
                    jsonPath.toString(),
                    questionsPath.toString(),
                    answersPath.toString());
        });
    }

    @Test
    void testLoadTrivia() throws IOException {
        // creates temporary file paths for testing
        Path jsonPath = tempDir.resolve("test.json");

        // Save the questions to JSON
        FileUtilities.saveTrivia(testQuestions,
                jsonPath.toString(),
                tempDir.resolve("dummy_questions.txt").toString(),
                tempDir.resolve("dummy_answers.txt").toString());

        // Now use this JSON to test loading
        List<TriviaQuestion> loadedQuestions = FileUtilities.loadTrivia(jsonPath.toString());

        // Check the first question
        TriviaQuestion firstQuestion = loadedQuestions.get(0);
        assertEquals(QuestionType.BOOLEAN, firstQuestion.type());
        assertEquals(Difficulty.HARD, firstQuestion.difficulty());
        assertEquals(Category.HISTORY, firstQuestion.category());
        assertEquals("The Berlin Wall fell in 1989.", firstQuestion.question());
        assertEquals("True", firstQuestion.correctAnswer());
        assertEquals(List.of("False"), firstQuestion.incorrectAnswers());

        // Check the second question
        TriviaQuestion secondQuestion = loadedQuestions.get(1);
        assertEquals(QuestionType.MULTIPLE, secondQuestion.type());
        assertEquals(Difficulty.EASY, secondQuestion.difficulty());
        assertEquals(Category.SPORTS, secondQuestion.category());
        assertEquals("Which boxer was banned for taking a bite out of Evander Holyfield's ear in 1997?", secondQuestion.question());
        assertEquals("Mike Tyson", secondQuestion.correctAnswer());
        assertEquals(Arrays.asList("Roy Jones Jr.", "Evander Holyfield", "Lennox Lewis"), secondQuestion.incorrectAnswers());
    }

    @Test
    void testLoadTriviaNull() throws IOException {
        Path emptyFilePath = tempDir.resolve("empty.json");

        // Create an empty file
        new File(emptyFilePath.toString()).createNewFile();

        assertThrows(IllegalArgumentException.class, () -> {
            FileUtilities.loadTrivia(emptyFilePath.toString());
        });
    }
}
