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

}
