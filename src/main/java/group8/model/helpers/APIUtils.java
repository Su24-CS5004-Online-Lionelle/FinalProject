package group8.model.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import group8.model.Enums;
import group8.model.TriviaQuestion;

/**
 * Utility class for interacting with the Open Trivia Database API.
 */
public class APIUtils {

    /** URL for Open Trivia database. */
    private static final String BASE_URL = "https://opentdb.com/";

    /** URL for Open Trivia categories. */
    private static final String CATEGORY_URL = "https://opentdb.com/api_category.php";

    /** API call limit. */
    private static final int BATCH_SIZE = 50; // Number of questions per batch

    /** Object converting between Java objects and JSON. */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /** Session token for API. */
    private static String sessionToken;    

    /** Category mapping for API. */
    private static Map<String, String> categoryMap;

    /** Maximum number of API retries. */
    private static final int MAX_RETRIES = 5;

    /** Delay (milliseconds) before retry. */
    private static final int RETRY_DELAY_MS = 5500;

    /** Constructor. */
    public APIUtils() {
        // Empty constructor.
    }

    /**
     * Fetches trivia questions in batches, with a 5 second delay between batches.
     * Returns a combined list of questions, based on the requested amount.
     * Typically used for requests over the call limit (i.e., 50).
     *
     * @param amount - number of questions to fetch.
     * @param category - category of questions.
     * @param difficulty - difficulty level of questions.
     * @param type - type of questions (e.g., multiple choice).
     * @return Combined list of trivia questions.
     * @throws Exception if an error occurs during the requests or conversion.
     */
    public static List<TriviaQuestion> getBatchedQuestions(int amount, Enums.Category category, Enums.Difficulty difficulty, Enums.QuestionType type) throws Exception {

        // Check for valid argument.
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid Question Amount: Requires positive integer.");
        }

        List<TriviaQuestion> allQuestions = new ArrayList<>();
        int totalFetched = 0;

        // Calculate the number of batches needed.
        int numberOfBatches = (int) Math.ceil((double) amount / BATCH_SIZE);

        for (int batch = 0; batch < numberOfBatches; batch++) {
            // Calculate the number of questions needed for this batch.
            int batchAmount = Math.min(BATCH_SIZE, amount - totalFetched);

            // Fetch and add questions for the current batch.
            List<TriviaQuestion> batchQuestions = getSingleQuestions(batchAmount, category, difficulty, type);
            allQuestions.addAll(batchQuestions);
            totalFetched += batchQuestions.size();

        }

        return allQuestions;
    }

    /**
     * Fetches and converts trivia questions; handles API errors based on the response code.
     *
     * @param amount - number of questions to fetch.
     * @param category - category of questions.
     * @param difficulty - difficulty level of questions.
     * @param type - type of questions (e.g., multiple choice).
     * @return List of TriviaQuestion records.
     * @throws Exception if an error occurs during the request or conversion.
     */
    public static List<TriviaQuestion> getSingleQuestions(int amount, Enums.Category category, Enums.Difficulty difficulty, Enums.QuestionType type) throws Exception {

        if (sessionToken == null) {
            throw new TriviaApiException("Token Not Set: Request token to start new session.");
        }

        if (amount > 50) {
            throw new TriviaApiException("Single Request Too Large: API only handles 50 questions per call.");
        }

        JsonNode questionsJsonNode = fetchQuestions(amount, category != null ? categoryMap.get(category.getValue()) : "", difficulty != null ? difficulty.getValue() : "", type != null ? type.getValue() : "");
        int responseCode = questionsJsonNode.get("response_code").asInt();

        switch (responseCode) {
            case 0:
                return convertQuestions(questionsJsonNode);
            case 1:
                throw new TriviaApiException("No Results: The API doesn't have enough questions for your query.");
            case 2:
                throw new TriviaApiException("Invalid Parameter: Contains an invalid parameter.");
            case 3:
                throw new TriviaApiException("Token Not Found: Session Token does not exist.");
            case 4:
                throw new TriviaApiException("Token Empty: Session Token has returned all possible questions for the specified query. Resetting the Token is necessary."); // Unlikely, but should handle.
            case 5:
                throw new TriviaApiException("Rate Limit: Too many requests have occurred. Each IP can only access the API once every 5 seconds.");
            default:
                throw new TriviaApiException("Unknown Error: An unknown error occurred with response code " + responseCode);
        }

    }

    /**
     * Converts a JsonNode containing trivia questions to a list of TriviaQuestion records.
     *
     * @param jsonNode - JsonNode containing the API response.
     * @return List of TriviaQuestion records.
     * @throws Exception if an error occurs during conversion.
     */
    public static List<TriviaQuestion> convertQuestions(JsonNode jsonNode) throws Exception {

        JsonNode resultsNode = jsonNode.get("results");
        if (resultsNode == null || !resultsNode.isArray()) {
            throw new IllegalArgumentException("Invalid JSON format: 'results' field is missing or not an array");
        }

        List<TriviaQuestion> triviaQuestions = new ArrayList<>();

        // Iterate over each node in the results array
        for (JsonNode node : resultsNode) {
            // Format the question field
            String formattedQuestion = htmlConverter(node.get("question").asText());
            ((ObjectNode) node).put("question", formattedQuestion);

            // Format the category field.
            String formattedCategory = htmlConverter(node.get("category").asText());
            ((ObjectNode) node).put("category", formattedCategory);

            // Deserialize the node into a TriviaQuestion object.
            TriviaQuestion triviaQuestion = objectMapper.treeToValue(node, TriviaQuestion.class);
            triviaQuestions.add(triviaQuestion);
        }

        return triviaQuestions;

    }

    /**
     * Fetches trivia questions from the Open Trivia Database API.
     *
     * @param amount - number of questions to fetch.
     * @param category - category of questions.
     * @param difficulty - difficulty level of questions.
     * @param type - type of questions (e.g., multiple choice).
     * @return JsonNode containing the API response.
     * @throws Exception if an error occurs during the request.
     */
    public static JsonNode fetchQuestions(int amount, String category, String difficulty, String type) throws Exception {

        String urlString = BASE_URL + "api.php?amount=" + amount +
                            (category != null ? "&category=" + category: "") +
                            (difficulty != null ? "&difficulty=" + difficulty: "") +
                            (type != null ? "&type=" + type: "") +
                            (sessionToken != null ? "&token=" + sessionToken: "");
        return sendGetRequest(urlString);

    }

    /**
     * Resets the current session token in the Open Trivia Database API.
     *
     * @throws Exception if an error occurs during the request.
     */
    public static void resetToken() throws Exception {
        if (sessionToken == null) {
            throw new TriviaApiException("No session token to reset. Please request a new token first.");
        }

        String urlString = BASE_URL + "api_token.php?command=reset&token=" + sessionToken;
        JsonNode response = sendGetRequest(urlString);
        int responseCode = response.get("response_code").asInt();

        if (responseCode == 0) {
            sessionToken = response.get("token").asText();
        } else {
            throw new TriviaApiException("Failed to reset token. Response code: " + responseCode);
        }
    }

    /**
     * Requests a new session token from the Open Trivia Database API.
     *
     * @throws Exception if an error occurs during the request.
     */
    public static void requestToken() throws Exception {
        String urlString = BASE_URL + "api_token.php?command=request";
        JsonNode response = sendGetRequest(urlString);
        int responseCode = response.get("response_code").asInt();

        if (responseCode == 0) {
            sessionToken = response.get("token").asText();
        } else {
            throw new TriviaApiException("Failed to request new token. Response code: " + responseCode);
        }
    }

    /**
     * Obtains question categories from API.
     * 
     * @return Map<String, String> mapping of category name to identifier.
     * @throws Exception if an error occurs during the request.
     */
    public static void requestCategories() throws Exception {
        String urlString = CATEGORY_URL;
        JsonNode categoriesJsonNode = sendGetRequest(urlString);

        Map<String, String> map = new HashMap<>();
        JsonNode triviaCategories = categoriesJsonNode.get("trivia_categories");

        if (triviaCategories != null && triviaCategories.isArray()) {
            for (JsonNode categoryNode : triviaCategories) {
                String categoryName = htmlConverter(categoryNode.get("name").asText());
                String categoryId = categoryNode.get("id").asText();
                map.put(categoryName, categoryId);
            }
        } else {
            throw new IllegalArgumentException("Invalid JSON format: 'trivia_categories' field is missing or not an array");
        }

        categoryMap = map;
    }

    /**
     * Sends a GET request to the specified URL and returns the response as a JsonNode.
     *
     * @param urlString - URL to send the GET request to.
     * @return JsonNode containing the response.
     * @throws Exception if an error occurs during the request.
     */
    private static JsonNode sendGetRequest(String urlString) throws Exception {

        int attempts = 0;

        while (attempts < MAX_RETRIES) {

            try {
                
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }

                    in.close();

                    return objectMapper.readTree(content.toString());

                } else if (responseCode == 429) {

                    attempts++;

                    if (attempts < MAX_RETRIES) {
                        Thread.sleep(RETRY_DELAY_MS);
                    } 

                } 

            } catch (Exception e) {

                attempts++;

                if (attempts < MAX_RETRIES) {
                    Thread.sleep(RETRY_DELAY_MS);
                }
            }
        }

        throw new Exception("Failed to send GET request after " + MAX_RETRIES + " attempts");

    }

    /**
     * Custom exception for API-related errors.
     */
    public static class TriviaApiException extends Exception {

        public TriviaApiException(String message) {
            super(message);
        }
    }

    public static String getSessionToken() {
        return sessionToken;
    }

    public static Map<String, String> getCategoryMap() {
        return categoryMap;
    }

    /**
     * Displays collections of Trivia questions in Category: Questions manner.
     *
     * @param questions collection of Trivia questions
     * @return List of Strings with the proper format for display
     */
    public static List<String> getFormattedQuestions(Collection<TriviaQuestion> questions) {
        List<String> formattedQuestions = new ArrayList<>();

        // Iterates through each TriviaQuestion to convert to a list of strings of question for display
        // Format example: GENERAL_KNOWLEDGE: The retail disc of Tony Hawk's Pro Skater 5 only comes with the tutorial.
        for (TriviaQuestion question : questions) {
            String nonHtmlQuestion = htmlConverter(question.question());
            String formattedQuestion = question.category().toString() + ": \"" + nonHtmlQuestion + "\"";
            formattedQuestions.add(formattedQuestion);
        }

        return formattedQuestions;
    }

    /**
     * Helper method that converts HTML coding to string counterpart.
     * @param input String containing HTML coding
     * @return String with HTML coding converted to its corresponding counterpart.
     */
    public static String htmlConverter(String input) {
        return input.replace("&quot;", "\"")
                .replace("&#039;", "'")
                .replace("&apos;", "'")
                .replace("&amp;", "&")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&ldquo;", "\u201C")
                .replace("&rdquo;", "\u201D")
                .replace("&lsquo;", "\u2018")
                .replace("&rsquo;", "\u2019");
    }

}
