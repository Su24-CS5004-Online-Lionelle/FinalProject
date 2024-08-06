package student;

import student.model.PokemonModel;

import java.io.IOException;
import java.net.UnknownHostException;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import student.model.PokeRecord;

public class PokemonApp {
    public static void main (String[] args) {
        PokemonModel model = PokemonModel.getInstance();
        try {
            // for quick testing
            System.out.println(model.getPokemonByName("mewtwo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
