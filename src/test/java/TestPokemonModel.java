import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import student.model.PokemonModel;
import student.model.NetUtils;
import student.model.PokeRecord;
import java.util.List;

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
        Integer expectedRecord = 6;
        List<PokeRecord> resultRecord = pokemonModel.getAllPokemonInTeam();
        assertEquals(expectedRecord, resultRecord.size());
    }
}