package group8.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Enums {

    public enum QuestionType {

        @JsonProperty("boolean")
        BOOLEAN("boolean"),

        @JsonProperty("multiple")
        MULTIPLE("multiple");
        
     
        private final String value;
    
        // Constructor to initialize the string value
        QuestionType(String value) {
            this.value = value;
        }
    
        // Method to get the string value
        public String getValue() {
            return value;
        }

        // Static method to get an enum constant from a string value
        public static QuestionType fromValue(String value) {
            for (QuestionType type : QuestionType.values()) {
                if (type.value.equals(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }
    
    public enum Difficulty {

        @JsonProperty("easy")
        EASY("easy"),
        
        @JsonProperty("medium")
        MEDIUM("medium"),
        
        @JsonProperty("hard")
        HARD("hard");
    
        private final String value;
    
        // Constructor to initialize the string value
        Difficulty(String value) {
            this.value = value;
        }
    
        // Method to get the string value
        public String getValue() {
            return value;
        }
    
        // Static method to get an enum constant from a string value
        public static Difficulty fromValue(String value) {
            for (Difficulty difficulty : Difficulty.values()) {
                if (difficulty.value.equals(value)) {
                    return difficulty;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }

    public enum Category {

        @JsonProperty("Animals")
        ANIMALS("Animals"),

        @JsonProperty("Art")
        ART("Art"),

        @JsonProperty("Celebrities")
        CELEBRITIES("Celebrities"),

        @JsonProperty("Entertainment: Board Games")
        ENTERTAINMENT_BOARD_GAMES("Entertainment: Board Games"),

        @JsonProperty("Entertainment: Books")
        ENTERTAINMENT_BOOKS("Entertainment: Books"),

        @JsonProperty("Entertainment: Cartoon & Animations")
        ENTERTAINMENT_CARTOON_ANIMATIONS("Entertainment: Cartoon & Animations"),

        @JsonProperty("Entertainment: Comics")
        ENTERTAINMENT_COMICS("Entertainment: Comics"),

        @JsonProperty("Entertainment: Film")
        ENTERTAINMENT_FILM("Entertainment: Film"),

        @JsonProperty("Entertainment: Japanese Anime & Manga")
        ENTERTAINMENT_JAPANESE_ANIME_MANGA("Entertainment: Japanese Anime & Manga"),

        @JsonProperty("Entertainment: Music")
        ENTERTAINMENT_MUSIC("Entertainment: Music"),

        @JsonProperty("Entertainment: Musicals & Theatres")
        ENTERTAINMENT_MUSICALS_THEATRES("Entertainment: Musicals & Theatres"),

        @JsonProperty("Entertainment: Television")
        ENTERTAINMENT_TELEVISION("Entertainment: Television"),

        @JsonProperty("Entertainment: Video Games")
        ENTERTAINMENT_VIDEO_GAMES("Entertainment: Video Games"),

        @JsonProperty("General Knowledge")
        GENERAL_KNOWLEDGE("General Knowledge"),

        @JsonProperty("Geography")
        GEOGRAPHY("Geography"),

        @JsonProperty("History")
        HISTORY("History"),

        @JsonProperty("Mythology")
        MYTHOLOGY("Mythology"),

        @JsonProperty("Politics")
        POLITICS("Politics"),

        @JsonProperty("Science & Nature")
        SCIENCE_NATURE("Science & Nature"),

        @JsonProperty("Science: Computers")
        SCIENCE_COMPUTERS("Science: Computers"),

        @JsonProperty("Science: Gadgets")
        SCIENCE_GADGETS("Science: Gadgets"),

        @JsonProperty("Science: Mathematics")
        SCIENCE_MATHEMATICS("Science: Mathematics"),

        @JsonProperty("Sports")
        SPORTS("Sports"),

        @JsonProperty("Vehicles")
        VEHICLES("Vehicles");
    
        private final String value;
    
        // Constructor to initialize the string value
        Category(String value) {
            this.value = value;
        }
    
        // Method to get the string value
        public String getValue() {
            return value;
        }
    
        // Static method to get an enum constant from a string value
        public static Category fromValue(String value) {
            for (Category category : Category.values()) {
                if (category.value.equals(value)) {
                    return category;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }

    public enum Field {

        TYPE("type"),
        DIFFICULTY("difficulty"),
        CATEGORY("category");

        private final String value;

        // Constructor to initialize the string value
        Field(String value) {
            this.value = value;
        }

        // Method to get the string value
        public String getValue() {
            return value;
        }

        // Static method to get an enum constant from a string value
        public static Field fromValue(String value) {
            for (Field field : Field.values()) {
                if (field.value.equals(value)) {
                    return field;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }
    }
}