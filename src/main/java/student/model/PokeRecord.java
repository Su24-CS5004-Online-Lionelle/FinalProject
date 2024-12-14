package student.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import student.model.PokemonMoves.PokemonMove;
import student.model.PokemonSprites.PokemonSprites;
import student.model.PokemonSprites.Sprites;
import student.model.PokemonTypes.PokemonType;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Primary record to pass around between objects. Is immutable and uses Jackson annotations for
 * serialization.
 *
 * @param name the name of the pokemon
 * @param id the id of the pokemon
 * @param order order for sorting.
 * @param height the height of the pokemon
 * @param weight the weight of the pokemon

 * @param moves A list of moves along with learn methods and level details pertaining to specific version groups
 *
* @return the record
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name", "id", "species", "height", "weight", "moves", "types", "sprites"})
public record PokeRecord(
    /**
     * The name of the Pokémon.
     */
    @JsonProperty("name") String name,
    /**
     * The unique identifier for the Pokémon.
     */
    @JsonProperty("id") int id,
     /**
     * The order of the Pokémon in the Pokédex.
     * For our case, ordering by ID is safer because it doesn't skip numbers.
     */
    @JsonProperty("order") int order,
     /**
     * The height of the Pokémon in decimeters.
     * To convert to feet, divide by 3.048.
     * To convert to inches, multiply by 3.937.
     *
     */
    @JsonProperty("height") int height,
    /**
     * The weight of the Pokémon in hectograms.
     * To convert to pounds, divide by 4.536.
     */
    @JsonProperty("weight") int weight,
    /**
     * A list of moves the Pokémon can learn, contains damage, accuracy, pp, and damage class.
     */
    @JsonProperty("moves") List<PokemonMove> moves,
    /**
     * Pokémon type, may contain more than one.
     */
    @JsonProperty("types") List<PokemonType> types,
    /**
     * The sprites of the Pokémon, front and back.
     */
    @JsonProperty("sprites") Sprites sprites) {
}
