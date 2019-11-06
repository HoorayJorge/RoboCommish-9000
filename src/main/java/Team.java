public class Team {

    public int teamId;
    public String teamName;
    public String firstName;
    public String ownerName;

    public Team(int teamId, String teamName, String ownerName, String firstName){

        this.teamId = teamId;
        this.teamName = teamName;
        this.firstName = firstName;
        this.ownerName = ownerName;

    }

    public String toString(){
        return teamName;
    }

}
