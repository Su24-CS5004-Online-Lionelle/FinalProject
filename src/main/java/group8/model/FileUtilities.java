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

    /** Default filename for JSON save file. */
    private static final String JSON_FILE = "trivia.json";

    /** Default filename for questions text file. */
    private static final String QUESTION_FILE = "questions.txt";

    /** Default filename for answers text file. */
    private static final String ANSWER_FILE = "answers.txt";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private FileUtilities() {
        // Prevents instantiation
    }

    /**
     * Saves trivia questions to output paths. In both JSON format and readable print format.
     * @param questions List of trivia questions to save.
     * @param folderName folder location to save the JSON, question and answer text file.
     * @throws IOException If unable to save files.
     */
    public static void saveTrivia(Collection<TriviaQuestion> questions, String folderName) throws IOException {
        // Null check
        if (questions == null || questions.isEmpty()) {
            throw new IllegalArgumentException("No questions to save. The collection is empty.");
        }

        // Create folder if it doesn't exist
        File folder = new File(folderName);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("Failed to create directory: " + folderName);
            }
        }

        // Update file paths
        String jsonFilePath = folderName + File.separator + JSON_FILE;
        String questionsFilePath = folderName + File.separator + QUESTION_FILE;
        String answersFilePath = folderName + File.separator + ANSWER_FILE;

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
        File file = new File(jsonFilePath);
        // Checks that file is not empty
        if (file.length() == 0) {
            throw new IllegalArgumentException("The JSON file is empty: " + jsonFilePath);
        }
        try {
            // Reads JSON, and convert it to a list of objects
            return mapper.readValue(file,
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
            questionOut.println();
            answerOut.println(ANSWER_HEADER);
            answerOut.println();
            // Iterate through questions, and writes to both files
            int i = 1;
            for (TriviaQuestion question : questions) {
                // Writes question
                questionOut.println(i + ". " + question.question());
                questionOut.println(); // Newline to split questions
                // Writes answer
                answerOut.println(i + ". Correct Answer: " + question.correctAnswer());
                // Writes incorrect answer. Convert answer to string
                String incorrectAnswersString = String.join(", ", question.incorrectAnswers());
                answerOut.println("   Incorrect Answers: " + incorrectAnswersString);
                answerOut.println(); // Newline to split answers
                i++; // Increments trivia
            }
            System.out.println("Success! Questions saved to " + questionsFilePath);
            System.out.println("Success! Answers saved to " + answersFilePath);
        } catch (IOException e) {
            throw new IOException("Unable to text answer and question files", e);
        }
    }
}
