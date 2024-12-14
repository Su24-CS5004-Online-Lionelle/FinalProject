package student.controller;

import student.model.PokemonModel;
import student.model.PokemonTypes.PokemonType;
import student.model.PokeRecord;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.ArrayList;
import java.net.UnknownHostException;

/**
 * The PokedexController class provides methods to filter and sort Pokémon data.
 */
public class PokedexController {
    private PokemonModel model;

    /**
     * Constructs a PokedexController with the given PokemonModel.
     *
     * @param model The model containing Pokémon data.
     */
    public PokedexController(){
        this.model = PokemonModel.getInstance();
    }

    /**
     * Returns a list of all Pokémon.
     *
     * @return A list of all Pokémon.
     */
    public List<PokeRecord> getAllPokemon(){
        try {
            return model.getAllPokemon();
        } catch (IOException e) {
            // Handle the IOException here
            e.printStackTrace();
            return new ArrayList<>(); // Or any other appropriate error handling
        }
    }

    /**
     * Filters Pokémon by ID.
     *
     * @param id The ID to filter by.
     * @return A list of Pokémon with the given ID.
     * @throws IOException
     */
    public List<PokeRecord> filterByID(int id) throws IOException {
        return model.getAllPokemon().stream()
            .filter(pokemon -> pokemon.id() == id)
            .collect(Collectors.toList());
    }

    /**
     * Filters Pokémon by name.
     *
     * @param name The name to filter by.
     * @return A list of Pokémon with the given name.
     * @throws IOException
     */
    public List<PokeRecord> filterByName(String name) throws IOException {
        return model.getAllPokemon().stream()
            .filter(pokemon -> pokemon.name().equalsIgnoreCase(name))
            .collect(Collectors.toList());
    }

    /**
     * Filters Pokémon by contains.
     *
     * @param name The input to filter by.
     * @return A list of Pokémon where the name contains the input.
     * @throws IOException
     */
    public List<PokeRecord> filterByContains(String input) throws IOException {
        return model.getAllPokemon().stream()
            .filter(pokemon -> pokemon.name().toLowerCase().contains(input.toLowerCase())
                                || String.valueOf(pokemon.id()).contains(input))
            .collect(Collectors.toList());
    }

    /**
     * Filters Pokémon by type.
     *
     * @param type The type to filter by.
     * @return A list of Pokémon with the given type.
     * @throws IOException
     */
    public List<PokeRecord> filterByTypes(List<String> types) throws IOException {
        List<String> lowerCaseTypes = types.stream()
        .map(String::toLowerCase)
        .collect(Collectors.toList());

        // Get all Pokémon in the list
        List<PokeRecord> pokemonList = model.getAllPokemon();

        // Make a list to store the Pokémon that pass the filter
        List<PokeRecord> filteredPokemonList = new ArrayList<>();

        // Iterate through each Pokémon
        for (PokeRecord pokemon : pokemonList) {
        // Convert Pokémon types to lowercase and check if any match the filter
        boolean matches = pokemon.types().stream()
        .map(type -> type.toString().toLowerCase()) // Using toString() to get the type name
        .anyMatch(lowerCaseTypes::contains);

        // If the Pokémon has at least one matching type, add it to the list
        if (matches) {
        filteredPokemonList.add(pokemon);
        }
        }

        // Return the filtered Pokémon list
        return filteredPokemonList;
    }
        
    /**
     * Sorts Pokémon by name.
     *
     * @param ascending Whether to sort in ascending order.
     * @return A list of Pokémon sorted by name.
     * @throws IOException
     */
    public List<PokeRecord> sortByName(boolean ascending) throws IOException {
        return model.getAllPokemon().stream()
                .sorted((p1, p2) -> ascending ? p1.name().compareToIgnoreCase(p2.name()) : p2.name().compareToIgnoreCase(p1.name()))
                .collect(Collectors.toList());
    }

    /**
     * Sorts Pokémon by ID.
     *
     * @param ascending Whether to sort in ascending order.
     * @return A list of Pokémon sorted by ID.
     * @throws IOException
     */
    public List<PokeRecord> sortByID(boolean ascending) throws IOException {
        return model.getAllPokemon().stream()
                .sorted((p1, p2) -> ascending ? Integer.compare(p1.id(), p2.id()) : Integer.compare(p2.id(), p1.id()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all Pokémon records from the team.
     *
     * @return A list of PokeRecord objects representing all Pokémon in the team.
     * @throws IOException
     */
    public List<PokeRecord> getAllPokemonInTeam() throws IOException {
        return model.getAllPokemonInTeam();
    }

    /**
     * Checks if in team.
     *
     * @return true or false
     * @throws IOException
     */
    public boolean isPokemonInTeam(PokeRecord pokemon) throws IOException {
        return model.isPokemonInTeam(pokemon);
    }

    /**
     * Adds a Pokemon to the team database.
     *
     * @param pokemon the PokeRecord of the Pokemon to be added to the team
     * @throws Exception if the team already has 6 Pokemon.
     */
    public void addPokemonToTeam(PokeRecord pokemon) throws Exception {
        List<PokeRecord> team = model.getAllPokemonInTeam();
        if (team.size() >= 6) {
            throw new Exception("Team is full. You cannot have more than 6 Pokemon in a team.");
        }
        model.addPokemonToTeam(pokemon);
    }

    /**
     * Removes a Pokemon from the team database.
     *
     * @param pokemon the PokeRecord of the Pokemon to be removed from the team
     */
    public void removePokemonFromTeam(PokeRecord pokemon) {
        model.removePokemonFromTeam(pokemon);
    }

    /**
     * Get the cry of the Pokemon.
     *
     * @param name The name of the Pokemon.
     * 
     * @return The string that contains the link of cry.
     * 
     * @throws Exception,UnknownHostException
     */
    public String getCryByName(String name) throws Exception, UnknownHostException {
        return model.getCryFromAPI(name);
    }

    /**
     * Exports the Pokémon team data to a specified JSON file.
     * 
     * @param filePath The path of the file where the Pokémon team data will be saved.
     * @throws IOException If an I/O error occurs during the file writing process.
     */
    public void ExportTeamToFile(String filePath) throws IOException {
        List<PokeRecord> team = model.getAllPokemonInTeam();
        model.writeJsonData(team, filePath);
    }
}
