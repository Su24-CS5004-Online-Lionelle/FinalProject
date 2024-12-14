import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import student.controller.PokedexController;
import student.model.PokeRecord;
import student.model.PokemonModel;
import java.util.Collections;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokedexFilterTest {

    private PokedexController pokedexController;

    /**
     * Set up the controller before each test.
     * 
     */
    @Before
    public void setUp() {
        // Initialize the controller
        pokedexController = new PokedexController();
    }

    /**
     * Tests retrieving of all pokemon.
     * 
     * @throws IOException if there is an issue retrieving pokemon data.
     */
    @Test
    public void testGetAllPokemon() throws IOException {
        List<PokeRecord> allPokemon = pokedexController.getAllPokemon();
        assertNotNull("Expects list of all pokemon", allPokemon);
    }

    /**
     * Tests filtering pokemon by their ID.
     * 
     * @throws IOException if there is an issue retrieving pokemon data.
     */
    @Test
    public void testFilterByID() throws IOException {
        List<PokeRecord> result = pokedexController.filterByID(25);
        assertTrue("Found pokemon with ID 25", result.size() > 0);
    }

    /**
     * Tests filtering pokemon by their name.
     * 
     * @throws IOException if there is an issue retrieving pokemon data.
     */
    @Test
    public void testFilterByName() throws IOException {
        List<PokeRecord> result = pokedexController.filterByName("pikachu");
        assertTrue("Found pokemon with the name pikachu", result.size() > 0);
    }

    /**
     * Tests filtering pokemon by a substring of their name.
     * 
     * @throws IOException if there is an issue retrieving pokemon data.
     */
    @Test
    public void testFilterByContains() throws IOException {
        List<PokeRecord> result = pokedexController.filterByContains("pika");
        assertTrue("Found pokemon with 'pika' in their name", result.size() > 0);
    }

    /**
     * Tests sorting pokemon by name in ascending order.
     * 
     * @throws IOException if there is an issue retrieving pokemon data.
     */
    @Test
    public void testSortByNameAscending() throws IOException {
        List<PokeRecord> sortedList = pokedexController.sortByName(true);
        assertTrue("Expects sorted list by name in ascending order", 
                    sortedList.get(0).name().compareToIgnoreCase(sortedList.get(1).name()) <= 0);
    }

    /**
     * Tests sorting pokemon by name in descending order.
     * 
     * @throws IOException if there is an issue retrieving pokemon data.
     */
    @Test
    public void testSortByNameDescending() throws IOException {
        List<PokeRecord> sortedList = pokedexController.sortByName(false);
        assertTrue("Expects sorted list by name in descending order", 
                    sortedList.get(0).name().compareToIgnoreCase(sortedList.get(1).name()) >= 0);
    }

    /**
     * Tests sorting pokemon by ID in ascending order.
     * 
     * @throws IOException if there is an issue retrieving pokemon data.
     */
    @Test
    public void testSortByIDAscending() throws IOException {
        List<PokeRecord> sortedList = pokedexController.sortByID(true);
        assertTrue("Expects sorted list by ID in ascending order", 
                    sortedList.get(0).id() <= sortedList.get(1).id());
    }

    /**
     * Tests sorting pokemon by ID in descending order.
     * 
     * @throws IOException if there is an issue retrieving pokemon data.
     */
    @Test
    public void testSortByIDDescending() throws IOException {
        List<PokeRecord> sortedList = pokedexController.sortByID(false);
        assertTrue("Expects sorted list by ID in descending order", 
                    sortedList.get(0).id() >= sortedList.get(1).id());
    }

    /**
     * Tests adding a pokemon to the team.
     * 
     * @throws Exception if there is an issue adding the pokemon to the team.
     */
    @Test
    public void testAddPokemonToTeam() throws Exception {
        PokeRecord mockRecord = new PokeRecord("Pikachu", 25, 1, 4, 60, 
                                                new ArrayList<>(), new ArrayList<>(), null);
        pokedexController.addPokemonToTeam(mockRecord);
        assertTrue("Pokemon added to the team", 
                    pokedexController.getAllPokemonInTeam().contains(mockRecord));
    }

    /**
     * Tests removing a pokemon from the team.
     * 
     * @throws Exception if there is an issue removing the pokemon from the team.
     */
    @Test
    public void testRemovePokemonFromTeam() throws Exception {
        PokeRecord mockRecord = new PokeRecord("Pikachu", 25, 1, 4, 60,
                                                new ArrayList<>(), new ArrayList<>(), null);
        pokedexController.addPokemonToTeam(mockRecord);
        pokedexController.removePokemonFromTeam(mockRecord);
        assertFalse("Pokemon removed from the team", 
                    pokedexController.getAllPokemonInTeam().contains(mockRecord));
    }

    /**
     * Test filtering by type with no types selected.
     * 
     * @throws IOException
     */
    @Test
    public void testFilterNoTypesSelected() throws IOException {
        // Test with no types selected
        List<PokeRecord> result = pokedexController.filterByTypes(new ArrayList<>());
        assertTrue("Expected no Pokémon when no types are selected", result.isEmpty());
    }

    /**
     * Test filtering by type with all types selected.
     * 
     * @throws IOException
     */
    @Test
    public void testFilterAllTypesSelected() throws IOException {
        // Test with all types selected
        List<String> allTypes = List.of(
            "Fire", "Water", "Grass", "Electric", "Ice", "Fighting", "Poison",
            "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dark",
            "Dragon", "Steel", "Fairy"
        );
        List<PokeRecord> result = pokedexController.filterByTypes(allTypes);
        assertFalse("Expected some Pokémon when all types are selected", result.isEmpty());
    }

    /**
     * Test filtering by type with an invalid type.
     * 
     * @throws IOException
     */
    @Test
    public void testFilterInvalidType() throws IOException {
        // Test with an invalid type
        List<String> invalidType = List.of("InvalidType");
        List<PokeRecord> result = pokedexController.filterByTypes(invalidType);
        assertTrue("Expected no Pokémon for an invalid type", result.isEmpty());
    }

    // Uncommented and fixed the filter by name test with empty name
    @Test
    public void testFilterEmptyName() throws IOException {
        List<PokeRecord> result = pokedexController.filterByName("");
        assertTrue("Expected no Pokémon for an empty name", result.isEmpty());
    }

    // Uncommented and fixed the filter by name test with special characters
    @Test
    public void testFilterNameWithSpecialCharacters() throws IOException {
        List<PokeRecord> result = pokedexController.filterByName("@#$%");
        assertTrue("Expected no Pokémon for a name with special characters", result.isEmpty());
    }

    
    @Test
    public void testFilterVeryLongName() throws IOException {
        String longName = "a".repeat(1000);
        List<PokeRecord> result = pokedexController.filterByName(longName);
        assertTrue("Expected no Pokémon for a very long name", result.isEmpty());
    }
}
