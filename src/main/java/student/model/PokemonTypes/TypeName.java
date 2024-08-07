package student.model.PokemonTypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a generic type with a name, designed to work with JSON data.
 * This class uses annotations to control JSON serialization and deserialization behaviors,
 * specifically ignoring unknown JSON properties to enhance compatibility and flexibility.
 * It encapsulates a single property, 'type', which is intended to represent a generic type name.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeName {

    /**
     * The name of the type.
     */
    private String type;

    /**
     * Constructs a new {@code TypeName} instance with a specified type name.
     * This constructor is annotated with {@code @JsonCreator} to indicate that it is used
     * for deserialization of JSON data into a {@code TypeName} object, where the {@code type}
     * is extracted from the "name" property of the JSON object.
     *
     * @param type The name of the type.
     */
    @JsonCreator
    public TypeName(@JsonProperty("name") String type) {
        this.type = type;
    }

    /**
     * Returns the name of the type.
     *
     * @return The name of the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the name of the type.
     *
     * @param type The new name of the type.
     */
    public void setType(String type) {
        this.type = type;
    }
}
