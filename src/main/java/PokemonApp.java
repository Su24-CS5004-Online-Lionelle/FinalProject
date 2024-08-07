import java.io.InputStream; // Add this import statement
import java.io.InputStreamReader; // Add this import statement
import org.json.simple.parser.JSONParser; // Add this import statement
import org.json.simple.JSONObject; // Add this import statement
import java.io.IOException; // Add this import statement
import org.json.simple.parser.ParseException; // Add this import statement

import student.controller.EventController;
import student.view.PokedexView;

public class PokemonApp {
    public static void main (String[] args) throws IOException {
        PokedexView view = new PokedexView();
        EventController controller = new EventController(view);
    }
}
