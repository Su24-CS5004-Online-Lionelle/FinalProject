package student.model.PokemonTypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Represents the type of a Pokémon, encapsulating the type's name.
 * This class is designed to work with JSON data, specifically ignoring unknown properties
 * to enhance compatibility with various JSON structures. It encapsulates the basic information of a Pokémon type,
 * mainly its name.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {

    /**
     * The name of the type.
     */
    @JsonProperty("name")
    private String typeName;

    /**
     * Constructs a new {@code Type} instance with a specified type name.
     * This constructor is annotated with {@code @JsonCreator} to indicate that it is used
     * for deserialization of JSON data into a {@code Type} object, where the {@code typeName}
     * is extracted from the "name" property of the JSON object.
     *
     * @param typeName The name of the type.
     */
    @JsonCreator
    public Type(@JsonProperty("name") String typeName) {
        this.typeName = typeName;
    }

    /**
     * Returns the name of the type.
     *
     * @return The name of the type.
     */
    public String getTypeName() {
        return this.typeName;
    }
}
