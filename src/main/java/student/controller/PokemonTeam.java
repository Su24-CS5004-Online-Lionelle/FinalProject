package student.controller;

import student.model.PokemonModel;
import student.model.PokeRecord;

import java.io.IOException;
import java.util.List;
import java.util.Scanner; // USED FOR TESTING. PLS REMOVE @ FINAL SUBMISSION.


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
                System.out.println("Your team:");
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
     * Checks if the team already has 6 Pokemon or if the Pokemon is already in the team.
     *
     * @param name The name of the Pokemon to add.
     */
    public void addPokemonToTeam(String name) {
        try {
            List<PokeRecord> team = model.getAllPokemonInTeam();

            if (team.size() >= 6) { // checks if team is full
                throw new Exception("Team is full. You cannot add more than 6 Pokemon.");
            }

            for (PokeRecord pokemon : team) { // checks if Pokemon is already in the team
                if (pokemon.name().equalsIgnoreCase(name)) {
                    throw new Exception(name + " is already in the team. You cannot add the same Pokemon twice.");
                }
            }

            PokeRecord pokemon = model.getPokemonByName(name); // adds Pokemon to team
            model.addPokemonToTeam(pokemon);
            System.out.println(pokemon.name() + " added to the team.");
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
            System.out.println(pokemon.name() + " removed from the team.");
        } catch (IOException e) {
            System.out.println("Error removing Pokemon: " + e.getMessage());
        }
    }

    // CREATED JUST TO TEST THIS CLASS. NOT PART OF THE ORIGINAL CODE. BUT U CAN UNCOMMENT TO TEST.
    // public static void main(String[] args) {
    //     PokemonTeam teamManager = new PokemonTeam();
    //     Scanner scanner = new Scanner(System.in);
    //     String command;

    //     while (true) {
    //         System.out.println("commands: list, team, add [name], remove [name], exit");
    //         System.out.print("enter command: ");
    //         command = scanner.nextLine().trim();

    //         if (command.equals("list")) {
    //             teamManager.displayAllPokemon();
    //         } else if (command.equals("team")) {
    //             teamManager.displayTeam();
    //         } else if (command.startsWith("add ")) {
    //             String name = command.substring(4).trim();
    //             teamManager.addPokemonToTeam(name);
    //         } else if (command.startsWith("remove ")) {
    //             String name = command.substring(7).trim();
    //             teamManager.removePokemonFromTeam(name);
    //         } else if (command.equals("exit")) {
    //             break;
    //         } else {
    //             System.out.println("invalid command.");
    //         }
    //     }

    //     scanner.close();
    // }
}

