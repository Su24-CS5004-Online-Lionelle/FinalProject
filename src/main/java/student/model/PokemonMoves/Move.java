package student.model.PokemonMoves;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import student.model.NetUtils;
import student.model.PokeRecord;
import student.model.PokemonMoves.NetUtilsMoveDetails;

/**
 * Represents a Pokémon move, including its name, URL for more details, and detailed attributes of the move.
 * This class is designed to work with JSON data, specifically ignoring unknown properties
 * to enhance compatibility with various JSON structures. It encapsulates the basic information of a Pokémon move
 * and provides functionality to fetch detailed move attributes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Move {

    /**
     * The name of the move.
     */
    private String name;

    /**
     * The URL to fetch more details about the move.
     */
    private String url;

    /**
     * The detailed attributes of the move.
     */
    @JsonProperty("details")
    private MoveDetails details;

    /**
     * Constructs a new {@code Move} instance with specified name, URL, and details.
     * If the details are not provided or empty, it attempts to fetch the details.
     *
     * @param name The name of the move.
     * @param url The URL to fetch more details about the move.
     * @param details The detailed attributes of the move.
     */
    @JsonCreator
    public Move(@JsonProperty("name") String name, @JsonProperty("url") String url, @JsonProperty("details") MoveDetails details) {
        this.name = name;
        this.url = url;
        if (details != null) {
            this.details = details;
        } else {
            setMoveDetails();
        }
    }

    /**
     * Returns the name of the move.
     *
     * @return The name of the move.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the move.
     *
     * @param name The new name of the move.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the URL to fetch more details about the move. This method is ignored in JSON serialization and deserialization.
     *
     * @return The URL to fetch more details about the move.
     */
    @JsonIgnore
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL to fetch more details about the move.
     *
     * @param url The new URL to fetch more details about the move.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns the detailed attributes of the move.
     *
     * @return The detailed attributes of the move.
     */
    public MoveDetails getMoveDetails() {
        return details;
    }

    /**
     * Sets the detailed attributes of the move by fetching them using the move's URL.
     * If fetching fails, it prints the stack trace of the exception.
     */
    public void setMoveDetails() {
        try {
            this.details = fetchMoveDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches the detailed attributes of the move from its URL and populates the {@code MoveDetails} object.
     * This method is private and is used internally to set the move details.
     *
     * @return The fetched {@code MoveDetails} object.
     * @throws Exception If there is an error during the fetching process.
     * @throws IOException If there is an input/output error during the fetching process.
     */
    private MoveDetails fetchMoveDetails() throws Exception, IOException {
        MoveDetails moveDetails;

        InputStream moveDetailsStream = NetUtilsMoveDetails.getMoveDetails(getUrl());
        String moveDetailsString = new String(moveDetailsStream.readAllBytes()).replace("\n", "");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        TypeReference<MoveDetails> typeRef = new TypeReference<MoveDetails>() { };
        moveDetails = mapper.readValue(moveDetailsString, typeRef);
        return moveDetails;
    }
}
