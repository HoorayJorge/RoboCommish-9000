import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class InputController {

    public static String apiKey = Token.apiKey;
    public static String leagueId = Token.leagueId;

    public InputController(){

    }

    public static JsonObject downloadAPIData( String sURL) throws IOException {

        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject();
        return rootobj;

    }

    public static JsonArray readFileData(String filename) throws FileNotFoundException {
        JsonParser jp = new JsonParser();
        FileReader file = new FileReader(filename);
        JsonElement root = jp.parse(new JsonReader(file));
        JsonArray rootObj = root.getAsJsonArray();
        return rootObj;
    }

    public static void serializePlayerJson(){

    }

    public static void deserializePlayerJson(){

    }

    public static void buildPlayerList(){

    }

}