package group8.model;

import group8.model.Enums.Category;
import group8.model.Enums.Difficulty;
import group8.model.Enums.QuestionType;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Question record to be used in collection objects.
 * 
 * @param type             the question type (multiple or true/false)
 * @param difficulty        the level of difficulty
 * @param category         the question category
 * @param question         the trivia question
 * @param correctAnswer    the correct answer
 * @param incorrectAnswers the incorrect answers
 */
public record TriviaQuestion(
        QuestionType type,
        Difficulty difficulty,
        Category category,
        String question,
        @JsonProperty("correct_answer") String correctAnswer,
        @JsonProperty("incorrect_answers") List<String> incorrectAnswers) implements Comparable<TriviaQuestion> {

        /**
         * Returns a string representation of the TriviaQuestion.
         *
         * @return a string representation of the TriviaQuestion.
         */
        @Override
        public String toString() {
                return String.format("Category: %s, Difficulty: %s, Type: %s, Question: %s, correctAnswer: %s, incorrectAnswer: %s",
                                category, difficulty, type, question, correctAnswer, incorrectAnswers );
        }

        /**
         * Compares this TriviaQuestion to another based on the question text.
         *
         * @param other the other TriviaQuestion to compare to.
         * @return a negative integer, zero, or a positive integer as this TriviaQuestion is less than, equal to, or greater than the specified TriviaQuestion.
         */
        @Override
        public int compareTo(TriviaQuestion other) {
                return this.question.compareTo(other.question);
        }
}
