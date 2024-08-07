package student.model.PokemonTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Pokémon's type, encapsulating the type details.
 * This class is designed to work with JSON data, specifically ignoring unknown properties
 * to enhance compatibility with various JSON structures. It serves as a wrapper for the {@link Type} class,
 * providing functionalities to access type details.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonType {
    /**
     * The {@link Type} object representing the Pokémon's type details.
     */
    @JsonProperty("type")
    private Type type;

    /**
     * Default constructor for creating an instance without initializing the type.
     * This is useful for deserialization purposes.
     */
    public PokemonType() {}

    /**
     * Constructs a new {@code PokemonType} instance with a specified type.
     *
     * @param type The {@link Type} object representing the Pokémon's type details.
     */
    public PokemonType(Type type) {
        this.type = type;
    }

    /**
     * Returns a string representation of the {@code PokemonType} instance,
     * which is the name of the Pokémon's type.
     *
     * @return A string representation of the {@code PokemonType} instance.
     */
    @Override
    public String toString() {
        return type.getTypeName();
    }
}
