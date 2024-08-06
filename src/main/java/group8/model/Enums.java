package group8.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Enums {

    public enum QuestionType {

        @JsonProperty("multiple")
        MULTIPLE("multiple"),
        
        @JsonProperty("boolean")
        BOOLEAN("boolean");
    
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

        @JsonProperty("Entertainment: Film")
        ENTERTAINMENT_FILM("Entertainment: Film"),
        
        @JsonProperty("Geography")
        GEOGRAPHY("Geography"),
        
        @JsonProperty("Entertainment: Cartoon & Animations")
        ENTERTAINMENT_CARTOON_ANIMATIONS("Entertainment: Cartoon &amp; Animations"),
        
        @JsonProperty("Art")
        ART("Art"),
        
        @JsonProperty("Entertainment: Music")
        ENTERTAINMENT_MUSIC("Entertainment: Music"),
        
        @JsonProperty("Entertainment: Books")
        ENTERTAINMENT_BOOKS("Entertainment: Books"),
        
        @JsonProperty("Science: Computers")
        SCIENCE_COMPUTERS("Science: Computers"),
        
        @JsonProperty("Science & Nature")
        SCIENCE_NATURE("Science &amp; Nature"),
        
        @JsonProperty("History")
        HISTORY("History"),
        
        @JsonProperty("Entertainment: Comics")
        ENTERTAINMENT_COMICS("Entertainment: Comics"),
        
        @JsonProperty("Vehicles")
        VEHICLES("Vehicles"),
        
        @JsonProperty("General Knowledge")
        GENERAL_KNOWLEDGE("General Knowledge"),
        
        @JsonProperty("Entertainment: Board Games")
        ENTERTAINMENT_BOARD_GAMES("Entertainment: Board Games"),
        
        @JsonProperty("Celebrities")
        CELEBRITIES("Celebrities"),
        
        @JsonProperty("Entertainment: Japanese Anime & Manga")
        ENTERTAINMENT_JAPANESE_ANIME_MANGA("Entertainment: Japanese Anime &amp; Manga"),
        
        @JsonProperty("Science: Mathematics")
        SCIENCE_MATHEMATICS("Science: Mathematics"),
        
        @JsonProperty("Animals")
        ANIMALS("Animals"),
        
        @JsonProperty("Entertainment: Television")
        ENTERTAINMENT_TELEVISION("Entertainment: Television"),
        
        @JsonProperty("Mythology")
        MYTHOLOGY("Mythology"),
        
        @JsonProperty("Entertainment: Musicals & Theatres")
        ENTERTAINMENT_MUSICALS_THEATRES("Entertainment: Musicals &amp; Theatres"),
        
        @JsonProperty("Politics")
        POLITICS("Politics"),
        
        @JsonProperty("Science: Gadgets")
        SCIENCE_GADGETS("Science: Gadgets"),
        
        @JsonProperty("Entertainment: Video Games")
        ENTERTAINMENT_VIDEO_GAMES("Entertainment: Video Games"),
        
        @JsonProperty("Sports")
        SPORTS("Sports");
    
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