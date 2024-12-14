import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import student.model.PokemonModel;
import student.model.NetUtils;
import student.model.PokeRecord;
import java.util.List;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;

/** Class that tests PokemonModel. */
public class TestPokemonModel {

    /** Initiate model.*/
   private PokemonModel pokemonModel = PokemonModel.getInstance();

   /**
    * Use valid Pokemon name to get the record directly from API.
    *
    * @throws Exception
    */
    @Test
    public void testGetRecordFromAPI() throws Exception {
        PokeRecord record = pokemonModel.getRecordFromAPI("pikachu");
        String expectedName = "pikachu";
        String resultName = record.name();
        assertEquals(expectedName, resultName);
        Integer expectedID = 25;
        Integer resultID = record.id();
        assertEquals(expectedID, resultID);
        Integer expectedHeight = 4;
        Integer resultHeight = record.height();
        assertEquals(expectedHeight, resultHeight);
        Integer expectedWeight = 60;
        Integer resultWeight = record.weight();
        assertEquals(expectedWeight, resultWeight);
        String expectedType = "electric";
        String resultType = record.types().get(0).toString();
        assertEquals(expectedType, resultType);
        String expectedMove = "mega-punch";
        String resultMove = record.moves().get(0).getMove().getName();
        assertEquals(expectedMove, resultMove);
    }

    /**
    * Use valid Pokemon id to get the record directly from API.
    *
    * @throws Exception
    */
    @Test
    public void testGetRecordFromAPI2() throws Exception {
        PokeRecord record = pokemonModel.getRecordFromAPI(25);
        String expectedName = "pikachu";
        String resultName = record.name();
        assertEquals(expectedName, resultName);
        Integer expectedID = 25;
        Integer resultID = record.id();
        assertEquals(expectedID, resultID);
        Integer expectedHeight = 4;
        Integer resultHeight = record.height();
        assertEquals(expectedHeight, resultHeight);
        Integer expectedWeight = 60;
        Integer resultWeight = record.weight();
        assertEquals(expectedWeight, resultWeight);
        String expectedType = "electric";
        String resultType = record.types().get(0).toString();
        assertEquals(expectedType, resultType);
        String expectedMove = "mega-punch";
        String resultMove = record.moves().get(0).getMove().getName();
        assertEquals(expectedMove, resultMove);
    }

    /**
    * Use unvalid Pokemon name to get no record directly from API but get exception.
    *
    */
    @Test
    public void testGetRecordFromAPIWithInvalidName() {
        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            pokemonModel.getRecordFromAPI("abc");
        });
        // Verify the exception message
        assertTrue(exception.getMessage().contains("No content to map due to end-of-input"));
    }

    /**
    * Use unvalid Pokemon id to get no record directly from API but get exception.
    *
    */
    @Test
    public void testGetRecordFromAPIWithInvalidID() {
        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            pokemonModel.getRecordFromAPI(-2);
        });
        // Verify the exception message
        assertTrue(exception.getMessage().contains("No content to map due to end-of-input"));
    }

    /**
    * Use valid Pokemon name to get the record directly from the database.
    *
    * @throws Exception
    */
    @Test
    public void testGetPokemonByName() throws Exception {
        PokeRecord record = pokemonModel.getPokemonByName("pikachu");
        String expectedName = "pikachu";
        String resultName = record.name();
        assertEquals(expectedName, resultName);
        Integer expectedID = 25;
        Integer resultID = record.id();
        assertEquals(expectedID, resultID);
        Integer expectedHeight = 4;
        Integer resultHeight = record.height();
        assertEquals(expectedHeight, resultHeight);
        Integer expectedWeight = 60;
        Integer resultWeight = record.weight();
        assertEquals(expectedWeight, resultWeight);
        String expectedType = "electric";
        String resultType = record.types().get(0).toString();
        assertEquals(expectedType, resultType);
        String expectedMove = "mega-punch";
        String resultMove = record.moves().get(0).getMove().getName();
        assertEquals(expectedMove, resultMove);
    }

    /**
    * Use valid Pokemon id to get the record directly from the database.
    *
    * @throws Exception
    */
    @Test
    public void testGetPokemonByID() throws Exception {
        PokeRecord record = pokemonModel.getPokemonByID(25);
        String expectedName = "pikachu";
        String resultName = record.name();
        assertEquals(expectedName, resultName);
        Integer expectedID = 25;
        Integer resultID = record.id();
        assertEquals(expectedID, resultID);
        Integer expectedHeight = 4;
        Integer resultHeight = record.height();
        assertEquals(expectedHeight, resultHeight);
        Integer expectedWeight = 60;
        Integer resultWeight = record.weight();
        assertEquals(expectedWeight, resultWeight);
        String expectedType = "electric";
        String resultType = record.types().get(0).toString();
        assertEquals(expectedType, resultType);
        String expectedMove = "mega-punch";
        String resultMove = record.moves().get(0).getMove().getName();
        assertEquals(expectedMove, resultMove);
    }

    /**
    * Use unvalid Pokemon name to get no Pokemon record directly from the database.
    *
    * @throws Exception
    */
    @Test
    public void testGetPokemonByName2() throws Exception {
        try {
            pokemonModel.getPokemonByName("abc");
        } catch (IOException e) {
            assertEquals("Pokemon not found.",  e.getMessage());
        }
    }

    /**
    * Use unvalid Pokemon id to get no Pokemon record directly from the database.
    *
    * @throws Exception
    */
    @Test
    public void testGetPokemonByID2() throws Exception {
        PrintStream std = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        pokemonModel.getPokemonByID(200);
        System.setOut(std);
        assertEquals("", outContent.toString());
    }

    /**
    * Use valid Pokemon name to get the record directly from the team file.
    *
    * @throws Exception
    */
    @Test
    public void testGetPokemonFromTeamByName() throws Exception {
        PokeRecord record = pokemonModel.getPokemonFromTeamByName("pikachu");
        String expectedName = "pikachu";
        String resultName = record.name();
        assertEquals(expectedName, resultName);
        Integer expectedID = 25;
        Integer resultID = record.id();
        assertEquals(expectedID, resultID);
        Integer expectedHeight = 4;
        Integer resultHeight = record.height();
        assertEquals(expectedHeight, resultHeight);
        Integer expectedWeight = 60;
        Integer resultWeight = record.weight();
        assertEquals(expectedWeight, resultWeight);
        String expectedType = "electric";
        String resultType = record.types().get(0).toString();
        assertEquals(expectedType, resultType);
        String expectedMove = "mega-punch";
        String resultMove = record.moves().get(0).getMove().getName();
        assertEquals(expectedMove, resultMove);
    }

    /**
    * Use valid Pokemon id to get the record directly from the team file.
    *
    * @throws Exception
    */
    @Test
    public void testGetPokemonFromTeamByID() throws Exception {
        PokeRecord record = pokemonModel.getPokemonFromTeamByID(25);
        String expectedName = "pikachu";
        String resultName = record.name();
        assertEquals(expectedName, resultName);
        Integer expectedID = 25;
        Integer resultID = record.id();
        assertEquals(expectedID, resultID);
        Integer expectedHeight = 4;
        Integer resultHeight = record.height();
        assertEquals(expectedHeight, resultHeight);
        Integer expectedWeight = 60;
        Integer resultWeight = record.weight();
        assertEquals(expectedWeight, resultWeight);
        String expectedType = "electric";
        String resultType = record.types().get(0).toString();
        assertEquals(expectedType, resultType);
        String expectedMove = "mega-punch";
        String resultMove = record.moves().get(0).getMove().getName();
        assertEquals(expectedMove, resultMove);
    }

    /**
    * Use unvalid Pokemon name to get the record directly from the team file but get exception.
    *
    * @throws Exception
    */
    @Test
    public void testGetPokemonFromTeamByName2() throws Exception {
        try {
            pokemonModel.getPokemonFromTeamByName("abc");
        } catch (IOException e) {
            assertEquals("Pokemon not in team.",  e.getMessage());
        }
    }

    /**
    * Use unvalid Pokemon name to get the record directly from the team file but get exception.
    *
    * @throws Exception
    */
    @Test
    public void testGetPokemonFromTeamByID2() throws Exception {
        try {
            pokemonModel.getPokemonFromTeamByID(200);
        } catch (IOException e) {
            assertEquals("Pokemon not in team.",  e.getMessage());
        }
    }

    /**
    * Use valid Pokemon name to get the cry directly from the API.
    *
    * @throws Exception
    */
    @Test
    public void testGetCryFromAPI() throws Exception {
        String expectedCry = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/25.ogg";
        String resultCry = pokemonModel.getCryFromAPI("pikachu");
        assertEquals(expectedCry, resultCry);
    }

    /**
    * Use unvalid Pokemon name to get no cry link directly from API.
    *
    */
    @Test
    public void testGetCryFromAPIWithInvalidName() throws Exception {
        PrintStream std = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        pokemonModel.getCryFromAPI("abc");
        System.setOut(std);
        assertEquals("", outContent.toString());
    }

    /**
    * Get all Pokemon record from database.
    *
    * @throws Exception
    */
    @Test
    public void testGetAllPokemon() throws Exception {
        Integer expectedRecord = 151;
        List<PokeRecord> resultRecord = pokemonModel.getAllPokemon();
        assertEquals(expectedRecord, resultRecord.size());
    }

    /**
    * Get all Pokemon record from team file.
    *
    * @throws Exception
    */
    @Test
    public void testGetAllPokemonInTeam() throws Exception {
        Integer expectedRecord = 5;
        List<PokeRecord> resultRecord = pokemonModel.getAllPokemonInTeam();
        assertEquals(expectedRecord, resultRecord.size());
    }

    /** 
     * Test saving existed Pokemon to database.
     * 
     * @throws Exception
     */
    @Test
    public void testSaveRecord() throws Exception {
        try {
            PokeRecord pikachu = pokemonModel.getPokemonFromTeamByID(25);
            pokemonModel.saveRecord(pikachu);
        } catch (Exception e) {
            assertEquals("Pokemon is already in the database.",  e.getMessage());
        }
    }

    /** 
     * Test saving new Pokemon to database.
     * 
     * @throws Exception
     */
    @Test
    public void testSaveRecord2() throws Exception {
        try {
            pokemonModel.getPokemonByName("bayleef");
        } catch (IOException e) {
            assertEquals("Pokemon not found.",  e.getMessage());
        }
        PokeRecord bayleef = pokemonModel.getRecordFromAPI("bayleef");
        pokemonModel.saveRecord(bayleef);
        String expectedName = "bayleef";
        String resultName = bayleef.name();
        assertEquals(expectedName, resultName);
        Integer expectedID = 153;
        Integer resultID = bayleef.id();
        assertEquals(expectedID, resultID);
        Integer expectedHeight = 12;
        Integer resultHeight = bayleef.height();
        assertEquals(expectedHeight, resultHeight);
        Integer expectedWeight = 158;
        Integer resultWeight = bayleef.weight();
        assertEquals(expectedWeight, resultWeight);
        String expectedType = "grass";
        String resultType = bayleef.types().get(0).toString();
        assertEquals(expectedType, resultType);
        String expectedMove = "swords-dance";
        String resultMove = bayleef.moves().get(0).getMove().getName();
        assertEquals(expectedMove, resultMove);
        // Remove the Pokemon from database.
        File databaseFile = new File("src/main/resources/database/pokerecords.json");
        ObjectMapper mapper = new ObjectMapper();
        List<PokeRecord> records = new ArrayList<>();
        records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
        PokeRecord pokemonToRemove = records.stream()
            .filter(item -> item.name().equals(bayleef.name())).toList().get(0);
            records.remove(pokemonToRemove);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try (FileOutputStream fos = new FileOutputStream("src/main/resources/database/pokerecords.json")) {
            mapper.writeValue(fos, records);
        } catch (Exception  e) {
            System.out.println("Database not found.");
        }
    }


    /**
     * Test adding and removing Pokemon to the team file.
     * 
     * @throws Exception
     */
    @Test
    public void testAddAndRemovePokemonToTeam() throws Exception {
        PokeRecord squirtle = pokemonModel.getPokemonByName("squirtle");
        /** Test adding the Pokemon. */
        pokemonModel.addPokemonToTeam(squirtle);
        PokeRecord squirtleTeam = pokemonModel.getPokemonFromTeamByName("squirtle");
        String expectedName = "squirtle";
        String resultName = squirtleTeam.name();
        assertEquals(expectedName, resultName);
        Integer expectedID = 7;
        Integer resultID = squirtleTeam.id();
        assertEquals(expectedID, resultID);
        Integer expectedHeight = 5;
        Integer resultHeight = squirtleTeam.height();
        assertEquals(expectedHeight, resultHeight);
        Integer expectedWeight = 90;
        Integer resultWeight = squirtleTeam.weight();
        assertEquals(expectedWeight, resultWeight);
        String expectedType = "water";
        String resultType = squirtleTeam.types().get(0).toString();
        assertEquals(expectedType, resultType);
        String expectedMove = "mega-punch";
        String resultMove = squirtleTeam.moves().get(0).getMove().getName();
        assertEquals(expectedMove, resultMove);

        /** Test removing the Pokemon. */
        pokemonModel.removePokemonFromTeam(squirtle);
        try {
            pokemonModel.getPokemonFromTeamByName("squirtle");
        } catch (IOException e) {
            assertEquals("Pokemon not in team.",  e.getMessage());
        }
    }

    /** 
     * Test writing out the team file.
     * 
     * @throws Exception
     */
    @TempDir
    static Path tempDir;
    @Test
    public void TestWriteJsonData() throws Exception {
        // Create team file into tempDir
        Path team = tempDir.resolve("team.json");
        List<PokeRecord> teamList = pokemonModel.getAllPokemonInTeam();
        pokemonModel.writeJsonData(teamList, team.toString());
        String expectedTeamFile = Files
                .readString(Paths.get("src/main/resources/database/teamrecords.json"));
        String actualTeamFile = Files.readString(team);
        assertEquals(expectedTeamFile, actualTeamFile);
        Files.delete(team);
    }

    /** 
     * Test whether the pokemon is in the team file.
     * 
     * @throws Exception
     */
    @Test
    public void testIsPokemonInTeam() throws Exception {
        PokeRecord pikachu = pokemonModel.getPokemonByName("pikachu");
        boolean expected = true;
        boolean actual = pokemonModel.isPokemonInTeam(pikachu);
        assertEquals(expected, actual);
        PokeRecord squirtle = pokemonModel.getPokemonByName("squirtle");
        boolean expected2 = false;
        boolean actual2 = pokemonModel.isPokemonInTeam(squirtle);
        assertEquals(expected2, actual2);
    }
}
