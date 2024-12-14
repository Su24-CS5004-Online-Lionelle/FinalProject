import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
