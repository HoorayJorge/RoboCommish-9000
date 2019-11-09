import com.google.gson.JsonObject;

public class Player {

    public String position;
    public String name;
    public int id;
    public String team;

    public int gameSecondsRemaining;
    public String status;
    public String updatedStats;
    public double score;

    public Player(){

    }

    @Override
    public String toString(){
        return name;
    }

}