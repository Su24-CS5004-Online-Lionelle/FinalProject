package student.model.PokemonMoves;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.j2objc.annotations.J2ObjCIncompatible;


/**
 * Represents a Pokémon's move, encapsulating the move details.
 * This class is designed to work with JSON data, specifically ignoring unknown properties
 * to enhance compatibility with various JSON structures. It serves as a wrapper for the {@link Move} class,
 * providing functionalities to access move details.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonMove {
    /**
     * The {@link Move} object representing the move's details.
     */
    @JsonProperty("move")
    private Move move;

    /**
     * Default constructor for creating an instance without initializing the move.
     * This is useful for deserialization purposes.
     */
    public PokemonMove() {}

    /**
     * Constructs a new {@code PokemonMove} instance with a specified move.
     *
     * @param move The {@link Move} object representing the move's details.
     */
    public PokemonMove(Move move) {
        this.move = move;
    }

    /**
     * Returns the {@link Move} object representing the move's details.
     *
     * @return The {@link Move} object.
     */
    public Move getMove() {
        return this.move;
    }

    /**
     * Returns a string representation of the {@code PokemonMove} instance,
     * which includes the move's name and its details.
     *
     * @return A string representation of the {@code PokemonMove} instance.
     */
    @Override
    public String toString() {
        return String.format("{%s : {%s}}", this.move.getName(), this.move.getMoveDetails());
    }
}
