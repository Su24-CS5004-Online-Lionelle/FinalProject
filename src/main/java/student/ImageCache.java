package student;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

import student.controller.PokedexController;
import student.model.PokeRecord;

public class ImageCache {
    private static final String CACHE_DIR = "cache/";
    private static final HashMap<String, String> cache = new HashMap<>();

    static {
        // Create cache directory if it doesn't exist
        File cacheDir = new File(CACHE_DIR);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
    }

    public static String getCachedImage(String imageUrl, String pokemonName) throws IOException {
        // Check if the image is already cached
        if (cache.containsKey(imageUrl)) {
            return cache.get(imageUrl);
        }

        // Download and cache the image
        BufferedImage image = ImageIO.read(new URL(imageUrl));
        String fileName = CACHE_DIR + pokemonName + ".png";
        File outputFile = new File(fileName);
        ImageIO.write(image, "png", outputFile);

        // Store the file path in the cache
        cache.put(imageUrl, fileName);

        return fileName;
    }

    public static void main(String[] args) {
        PokedexController controller = new PokedexController();
        try {
            for (PokeRecord pokemon : controller.getAllPokemon()) {
                String imageUrl = pokemon.sprites().getFrontDefault();
                String cachedImagePath = getCachedImage(imageUrl, pokemon.name());
                System.out.println("Image cached at: " + cachedImagePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
