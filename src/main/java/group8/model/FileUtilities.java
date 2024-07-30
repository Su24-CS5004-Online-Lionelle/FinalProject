package group8.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.Collection;

public final class FileUtilities {

    /** Creates default JSON wrapper with pretty print indent enabled. */
    private static final ObjectMapper mapper = new ObjectMapper().
            enable(SerializationFeature.INDENT_OUTPUT);

    /** Header for questions text file. */
    private static final String QUESTION_HEADER = "TRIVIA QUESTIONS:";

    /** Header for answers text file. */
    private static final String ANSWER_HEADER = "TRIVIA ANSWERS:";


    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private FileUtilities() {
        // Prevents instantiation
    }

    /**
     * Saves trivia questions to output paths. In both JSON format and readable print format.
     * @param questions List of trivia questions to save.
     * @param jsonFilePath Filepath to store JSON file.
     * @param questionsFilePath Filepath to store trivia question text file.
     * @param answersFilePath Filepath to store trivia answer text file.
     * @throws IOException If unable to save files.
     */
    public static void saveTrivia(Collection<TriviaQuestion> questions, String jsonFilePath,
                                  String questionsFilePath, String answersFilePath) throws IOException {
        saveAsJson(questions, jsonFilePath); // Saves to JSON format
        saveAsText(questions, questionsFilePath, answersFilePath); // Saves to question/answer text format
    }

    /**
     * Loads Trivia questions from a JSON file.
     * @param jsonFilePath File path for JSON file containing the trivia questions.
     * @return A list of trivia questions objects.
     * @throws IOException If unable to read files.
     */
    public static List<TriviaQuestion> loadTrivia(String jsonFilePath) throws IOException {
        try {
            // Reads JSON, and convert it to a list of objects
            return mapper.readValue(new File(jsonFilePath),
                    mapper.getTypeFactory().constructCollectionType(List.class, TriviaQuestion.class));
        } catch (IOException e) {
            throw new IOException("Unable to load JSON file", e);
        }
    }

    /**
     * Saves a collection of Trivia questions objects to a JSON file
     * @param questions The collection of trivia questions to save.
     * @param filePath File path for JSON file to be saved.
     * @throws IOException If unable to write to file.
     */
    private static void saveAsJson(Collection<TriviaQuestion> questions, String filePath) throws IOException {
        try { // Writes to questions to file path
            mapper.writeValue(new File(filePath), questions);
            System.out.println("Success! Questions saved as JSON to " + filePath);
        } catch (IOException e) {
            throw new IOException("Unable to save JSON file", e);
        }
    }


    private static void saveAsText(Collection<TriviaQuestion> questions, String questionsFilePath, String answersFilePath) throws IOException {
        try (PrintWriter questionOut = new PrintWriter(questionsFilePath);
             PrintWriter answerOut = new PrintWriter(answersFilePath)) {
            // Adding headers
            questionOut.println(QUESTION_HEADER);
            answerOut.println(ANSWER_HEADER);
            // Iterate through questions, and writes to both files
            int i = 1;
            for (TriviaQuestion question : questions) {
                // Writes question
                questionOut.println(i + ". " + question.questionText());
                questionOut.println(); // Newline to split questions
                // Writes answer
                answerOut.println(i + ". Correct Answer: " + question.correctAnswer());
                // Writes incorrect answer. Joins the list of incorrect answer to a string
                String incorrectAnswersString = String.join(", ", question.incorrectAnswers());
                answerOut.println("   Incorrect Answers: " + incorrectAnswersString);
                answerOut.println(); // Newline to split answers
                i++; // Increments question
            }
            System.out.println("Success! Questions saved to " + questionsFilePath);
            System.out.println("Success! Answers saved to " + answersFilePath);
        } catch (IOException e) {
            throw new IOException("Unable to text answer and question files", e);
        }
    }
}
