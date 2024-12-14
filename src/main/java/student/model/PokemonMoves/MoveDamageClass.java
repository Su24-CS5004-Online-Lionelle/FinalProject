package student.model.PokemonMoves;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.j2objc.annotations.J2ObjCIncompatible;

/**
 * Represents the damage class of a Pokémon's move, categorizing the move into different types based on the damage it deals.
 * This class is designed to work with JSON data, specifically ignoring unknown properties
 * to enhance compatibility with various JSON structures. It encapsulates the damage type of a move.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveDamageClass {
    /**
     * The name of the damage type.
     */
    @JsonProperty("name")
    private String damageType;

    /**
     * Default constructor for creating an instance without initializing the damage type.
     * This is useful for deserialization purposes.
     */
    public MoveDamageClass() {}

    /**
     * Constructs a new {@code MoveDamageClass} instance with a specified damage type.
     *
     * @param damageType The name of the damage type.
     */
    public MoveDamageClass(String damageType) {
        this.damageType = damageType;
    }

    /**
     * Returns the name of the damage type.
     *
     * @return The name of the damage type.
     */
    public String getDamageType() {
        return damageType;
    }

    /**
     * Sets the name of the damage type.
     *
     * @param damageType The new name of the damage type.
     */
    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    /**
     * Returns a string representation of the {@code MoveDamageClass} instance,
     * which is the name of the damage type.
     *
     * @return A string representation of the {@code MoveDamageClass} instance.
     */
    @Override
    public String toString() {
        return this.damageType;
    }
}
