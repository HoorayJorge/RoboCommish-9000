import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public class Team implements Comparable<Team> {

    public int teamId;
    public String teamName;
    public String firstName;
    public String ownerName;
    public int overallRank;
    public int wins;
    public int losses;
    public int ties;

    public int playersCurrentlyPlaying;
    public int gameSecondsRemaining;
    public int isHome;
    public int playersYetToPlay;
    public double score;
    public double line;

    public Team(int teamId, String teamName, String ownerName, String firstName){

        this.teamId = teamId;
        this.teamName = teamName;
        this.firstName = firstName;
        this.ownerName = ownerName;
        this.overallRank = 12;
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;

    }

    public String toString(){
        return teamName;
    }

    public int compareTo(@NotNull Team thatTeam){
        Integer thisRank = this.overallRank;
        Integer thatRank = thatTeam.overallRank;
        return thisRank.compareTo(thatRank);
    }

    public void addScoringData(JsonObject scoringObj){

        playersCurrentlyPlaying = scoringObj.get("playersCurrentlyPlaying").getAsInt();
        gameSecondsRemaining = scoringObj.get("gameSecondsRemaining").getAsInt();
        isHome = scoringObj.get("isHome").getAsInt();
        playersYetToPlay = scoringObj.get("playersYetToPlay").getAsInt();
        score = scoringObj.get("score").getAsDouble();

    }

}
