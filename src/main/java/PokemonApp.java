import java.io.IOException;

import student.controller.EventController;
import student.view.PokedexView;

public class PokemonApp {
    public static void main (String[] args) throws IOException {
        PokedexView view = new PokedexView();
        EventController controller = new EventController(view);
    }
}
