package student.model.PokemonSprites;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the sprites container for a Pokémon, encapsulating the sprites details.
 * This class is designed to work with JSON data, specifically ignoring unknown properties
 * to enhance compatibility with various JSON structures.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonSprites {
    /**
     * The {@link Sprites} object containing various sprite images for the Pokémon.
     */
    @JsonProperty("sprites")
    private Sprites sprites;

    /**
     * Constructs a new {@code PokemonSprites} instance with specified sprites.
     *
     * @param sprites The {@link Sprites} object containing the sprite images.
     */
    @JsonCreator
    public PokemonSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    /**
     * Returns the {@link Sprites} object containing the sprite images.
     *
     * @return The {@link Sprites} object.
     */
    public Sprites getSprites() {
        return sprites;
    }

    /**
     * Sets the {@link Sprites} object containing the sprite images.
     *
     * @param sprites The new {@link Sprites} object to set.
     */
    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}
