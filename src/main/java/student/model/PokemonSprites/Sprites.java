package student.model.PokemonSprites;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the sprites for a Pokémon, including both front and back default images.
 * This class is designed to work with JSON data, ignoring unknown properties for flexibility.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprites {
    /**
     * The URL of the default back sprite image.
     */
    private String backDefault;

    /**
     * The URL of the default front sprite image.
     */
    private String frontDefault;

    /**
     * Constructs a new {@code Sprites} instance with specified back and front default images.
     *
     * @param backDefault The URL of the back default sprite image.
     * @param frontDefault The URL of the front default sprite image.
     */
    @JsonCreator
    public Sprites(@JsonProperty("back_default") String backDefault, @JsonProperty("front_default") String frontDefault) {
        this.backDefault = backDefault;
        this.frontDefault = frontDefault;
    }

    /**
     * Returns the URL of the back default sprite image.
     *
     * @return The URL of the back default sprite image.
     */
    public String getBackDefault() {
        return backDefault;
    }

    /**
     * Sets the URL of the back default sprite image.
     *
     * @param backDefault The new URL of the back default sprite image.
     */
    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    /**
     * Returns the URL of the front default sprite image.
     *
     * @return The URL of the front default sprite image.
     */
    public String getFrontDefault() {
        return frontDefault;
    }

    /**
     * Sets the URL of the front default sprite image.
     *
     * @param frontDefault The new URL of the front default sprite image.
     */
    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    /**
     * Returns a string representation of the {@code Sprites} instance, including both back and front default images.
     *
     * @return A string representation of the {@code Sprites} instance.
     */
    @Override
    public String toString() {
        return String.format("{%s, %s}", this.backDefault, this.frontDefault);
    }
}
