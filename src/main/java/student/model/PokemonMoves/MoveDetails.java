package student.model.PokemonMoves;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.j2objc.annotations.J2ObjCIncompatible;

/**
 * Represents the detailed attributes of a Pokémon's move, including accuracy, damage class, power, and PP (Power Points).
 * This class is designed to work with JSON data, specifically ignoring unknown properties
 * to enhance compatibility with various JSON structures. It encapsulates the details fetched from another API call
 * for each move, representing an inner JSON object of the move object of each Pokémon in the moves list.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveDetails {
    /**
     * The accuracy of the move, indicating how often the move hits.
     */
    @JsonProperty("accuracy")
    private int accuracy;

    /**
     * The damage class of the move, categorizing the move into physical, special, or non-damaging moves.
     */
    @JsonProperty("damage_class")
    private MoveDamageClass damageClass;

    /**
     * The power of the move, determining how much damage the move can inflict.
     */
    @JsonProperty("power")
    private int power;

    /**
     * The PP (Power Points) of the move, indicating the number of times a move can be used.
     */
    @JsonProperty("pp")
    private int pp;

    /**
     * Default constructor for creating an instance without initializing the fields.
     * This is useful for deserialization purposes.
     */
    public MoveDetails() {}

    /**
     * Constructs a new {@code MoveDetails} instance with specified accuracy, damage class, power, and PP.
     *
     * @param accuracy The accuracy of the move.
     * @param damageClass The damage class of the move.
     * @param power The power of the move.
     * @param pp The PP (Power Points) of the move.
     */
    public MoveDetails(int accuracy, MoveDamageClass damageClass, int power, int pp) {
        this.accuracy = accuracy;
        this.damageClass = damageClass;
        this.power = power;
        this.pp = pp;
    }

    /**
     * Returns the accuracy of the move.
     *
     * @return The accuracy of the move.
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Sets the accuracy of the move.
     *
     * @param accuracy The new accuracy of the move.
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Returns the damage class of the move.
     *
     * @return The damage class of the move.
     */
    public MoveDamageClass getDamageClass() {
        return damageClass;
    }

    /**
     * Sets the damage class of the move.
     *
     * @param damageClass The new damage class of the move.
     */
    public void setDamageClass(MoveDamageClass damageClass) {
        this.damageClass = damageClass;
    }

    /**
     * Returns the power of the move.
     *
     * @return The power of the move.
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the power of the move.
     *
     * @param power The new power of the move.
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Returns the PP (Power Points) of the move.
     *
     * @return The PP of the move.
     */
    public int getPp() {
        return pp;
    }

    /**
     * Sets the PP (Power Points) of the move.
     *
     * @param pp The new PP of the move.
     */
    public void setPp(int pp) {
        this.pp = pp;
    }

    /**
     * Returns a string representation of the {@code MoveDetails} instance,
     * including accuracy, damage class, power, and PP.
     *
     * @return A string representation of the {@code MoveDetails} instance.
     */
    @Override
    public String toString() {
        return String.format("MoveDetails : {accuracy=%d, damageClass=%s, power=%d, pp=%d}",
        accuracy,
        damageClass, // Assuming MoveDamageClass has a meaningful toString method
        power,
        pp);
    }
}
