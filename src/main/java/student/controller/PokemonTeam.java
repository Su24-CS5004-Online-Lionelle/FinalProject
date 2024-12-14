package student.controller;

import student.model.PokemonModel;
import student.model.PokeRecord;

import java.io.IOException;
import java.util.List;

/**
 * The PokemonTeam class allows you to manage a Pokemon team.
 * You can:
 * 1) display all Pokemon
 * 2) display your current team
 * 3) add Pokemon to the team
 * 4) remove Pokemon from the team
 */
public class PokemonTeam {
    private PokemonModel model;

    /**
     * Initializes a PokemonTeam object.
     */
    public PokemonTeam() {
        this.model = PokemonModel.getInstance();
    }

    /**
     * Displays all Pokemon available in the database.
     */
    public void displayAllPokemon() {
        try {
            List<PokeRecord> allPokemon = model.getAllPokemon();
            for (PokeRecord pokemon : allPokemon) {
                System.out.println(pokemon.name() + " (ID: " + pokemon.id() + ")");
            }
        } catch (IOException e) {
            System.out.println("Error getting Pokemon: " + e.getMessage());
        }
    }

    /**
     * Displays the current Pokemon team.
     */
    public void displayTeam() {
        try {
            List<PokeRecord> team = model.getAllPokemonInTeam();
            if (team.isEmpty()) {
                System.out.println("Your team is empty.");
            } else {
                for (PokeRecord pokemon : team) {
                    System.out.println(pokemon.name() + " (ID: " + pokemon.id() + ")");
                }
            }
        } catch (IOException e) {
            System.out.println("Error fetching team: " + e.getMessage());
        }
    }

    /**
     * Adds a Pokemon to the team.
     *
     * @param name The name of the Pokemon to add.
     */
    public void addPokemonToTeam(String name) {
        try {
            PokeRecord pokemon = model.getPokemonByName(name);
            model.addPokemonToTeam(pokemon);
        } catch (Exception e) {
            System.out.println("Error adding Pokemon: " + e.getMessage());
        }
    }

    /**
     * Removes a Pokemon from the team.
     *
     * @param name The name of the Pokemon to remove.
     */
    public void removePokemonFromTeam(String name) {
        try {
            PokeRecord pokemon = model.getPokemonFromTeamByName(name);
            model.removePokemonFromTeam(pokemon);
        } catch (IOException e) {
            System.out.println("Error removing Pokemon: " + e.getMessage());
        }
    }
}

