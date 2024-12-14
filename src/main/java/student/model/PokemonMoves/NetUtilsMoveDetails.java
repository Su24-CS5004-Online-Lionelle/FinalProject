package student.model.PokemonMoves;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class NetUtilsMoveDetails {

    /**
     * Prevent instantiation.
     */
    private NetUtilsMoveDetails() {
        // Prevent instantiation
    }


    /**
     * Gets the contents of a URL as an InputStream.
     *
     * @param urlStr the URL to get the contents of
     * @return the contents of the URL as an InputStream, or the null InputStream if the connection
     *         fails
     *
     */
    public static InputStream getUrlContents(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            // con.setRequestProperty("Content-Type", "application/xml");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                            + "(KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
            int status = con.getResponseCode();
            if (status == 200) {
                return con.getInputStream();
            } else {
                System.err.println("Failed to connect to " + urlStr);
            }

        } catch (Exception ex) {
            System.err.println("Failed to connect to " + urlStr);
        }
        return InputStream.nullInputStream();
    }


    /**
     * Gets IP details using the ipapi.co API. Defaults to XML format.
     *
     * @param name The pokemon name to look up.
     * @return the contents of the URL as an InputStream, or the null InputStream if the connection
     */
    public static InputStream getMoveDetails(String url) {
        return getUrlContents(url);
    }
}
