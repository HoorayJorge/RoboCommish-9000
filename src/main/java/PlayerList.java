import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerList {

    public static ArrayList<Player> thePlayerList = new ArrayList<Player>();
    public static HashMap<Integer, Player> thePlayerMap = new HashMap<>();

    public PlayerList(){

    }

    /*public static void readPlayerData() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader jr = new JsonReader(new FileReader("Output.json"));

        JsonArray arrayObj = gson.fromJson(jr, JsonArray.class);

        for (int i = 0; i < arrayObj.size(); i++){
            thePlayerList.add(gson.fromJson(arrayObj.get(i).getAsJsonObject(), Player.class));
        }

        for (int i = 0; i < thePlayerList.size(); i++){
            thePlayerMap.put(thePlayerList.get(i).id, thePlayerList.get(i));
        }
    }*/

}
