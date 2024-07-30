import group5.model.beans.MBeans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestData {

    static Stream<MBeans> getTestDataSet1() {
        List<MBeans> movies = new ArrayList<>();
        movies.add(new MBeans("Inception", 2010, "Movie", "PG-13",
                LocalDate.of(2010, 7, 16), 148,
                List.of("Action", "Sci-Fi"),
                List.of("Christopher Nolan"),
                List.of("Christopher Nolan"),
                List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"),
                "A thief who steals corporate secrets...",
                List.of("English"),
                List.of("USA"),
                "Won 4 Oscars.",
                "https://example.com/poster1.jpg",
                74, 8.8, 836800000,
                "tt1375666", false, -1.0));

        movies.add(new MBeans("The Matrix", 1999, "Movie", "R",
                LocalDate.of(1999, 3, 31), 136,
                List.of("Action", "Sci-Fi"),
                List.of("Lana Wachowski", "Lilly Wachowski"),
                List.of("Lana Wachowski", "Lilly Wachowski"),
                List.of("Keanu Reeves", "Laurence Fishburne"),
                "A computer hacker learns from mysterious rebels...",
                List.of("English"),
                List.of("USA"),
                "Won 4 Oscars.",
                "https://example.com/poster2.jpg",
                73, 8.7, 463517383,
                "tt0133093", false, -1.0));

        movies.add(new MBeans("Interstellar", 2014, "Movie", "PG-13",
                LocalDate.of(2014, 11, 7), 169,
                List.of("Adventure", "Drama", "Sci-Fi"),
                List.of("Christopher Nolan"),
                List.of("Jonathan Nolan", "Christopher Nolan"),
                List.of("Matthew McConaughey", "Anne Hathaway"),
                "A team of explorers travel through a wormhole...",
                List.of("English"),
                List.of("USA"),
                "Won 1 Oscar.",
                "https://example.com/poster3.jpg",
                74, 8.6, 701729206,
                "tt0816692", false, -1.0));

        movies.add(new MBeans("The Social Network", 2010, "Movie", "PG-13",
                LocalDate.of(2010, 10, 1), 120,
                List.of("Biography", "Drama"),
                List.of("David Fincher"),
                List.of("Aaron Sorkin"),
                List.of("Jesse Eisenberg", "Andrew Garfield"),
                "The story of Harvard student Mark Zuckerberg...",
                List.of("English"),
                List.of("USA"),
                "Won 3 Oscars.",
                "https://example.com/poster4.jpg",
                76, 7.7, 224920315,
                "tt1285016", false, -1.0));

        movies.add(new MBeans("Pandemic", 2007, "Series", "TV-14",
                LocalDate.of(2007, 1, 25), 120,
                List.of("Drama", "Thriller"),
                List.of("Armand Mastroianni"),
                List.of("Ron McGee", "Ed Napier"),
                List.of("Tiffani Thiessen", "French Stewart"),
                "The bird flu virus spreads through Los Angeles...",
                List.of("English"),
                List.of("USA"),
                "N/A",
                "https://example.com/poster5.jpg",
                45, 5.3, -1,
                "tt0826763", true, -1.0));

        return movies.stream();
    }
}
