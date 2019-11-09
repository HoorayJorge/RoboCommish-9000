import java.io.FileNotFoundException;
import java.util.HashMap;

public class Main {
    public static void main(String args[]) throws InterruptedException, FileNotFoundException {

        BuildTeamList();
        //PlayerList.readPlayerData();
        //System.out.println(PlayerList.thePlayerList.get(0));
        new WeeklyReportService();
        BotBuilder.api.awaitReady();

    }

    public static void BuildTeamList(){
        TeamList.teamMap.put(1, new Team(1, "My Bucket", "Jason Botta", "Jason B"));
        TeamList.teamMap.put(2, new Team(2, "Weeb Gang", "George Beatty", "George"));
        TeamList.teamMap.put(3, new Team(3, "Catching Babies", "Colin Davis", "Colin"));
        TeamList.teamMap.put(4, new Team(4, "Mark's Ballers", "Mark Eddinger", "Mark"));
        TeamList.teamMap.put(5, new Team(5, "Rollin' with Mahomies", "Kevin Rape", "Kevin"));
        TeamList.teamMap.put(6, new Team(6, "Forgetting Brandon Marshall", "Josh Butella", "Josh"));
        TeamList.teamMap.put(7, new Team(7, "Gruden's Grindr", "Jason Hutson", "Jason H"));
        TeamList.teamMap.put(8, new Team(8, "Snake Bitten", "Tom Bohr", "Tom"));
        TeamList.teamMap.put(9, new Team(9, "Barkley Wine", "Alex Grier", "Jimmy"));
        TeamList.teamMap.put(10, new Team(10, "Half a Graham", "Jordon Goth", "Jordan"));
        TeamList.teamMap.put(11, new Team(11, "Just Burfict", "Valerie Hutson", "Valerie"));
        TeamList.teamMap.put(12, new Team(12, "Masterdeflator", "Justin Milkovich", "Justin"));

        Division.divisionOne.put(1, TeamList.teamMap.get(1));
        Division.divisionOne.put(2, TeamList.teamMap.get(2));
        Division.divisionOne.put(7, TeamList.teamMap.get(7));
        Division.divisionOne.put(9, TeamList.teamMap.get(9));
        Division.divisionOne.put(10, TeamList.teamMap.get(10));
        Division.divisionOne.put(11, TeamList.teamMap.get(11));

        Division.divisionTwo.put(3, TeamList.teamMap.get(3));
        Division.divisionTwo.put(4, TeamList.teamMap.get(4));
        Division.divisionTwo.put(5, TeamList.teamMap.get(5));
        Division.divisionTwo.put(6, TeamList.teamMap.get(6));
        Division.divisionTwo.put(8, TeamList.teamMap.get(8));
        Division.divisionTwo.put(12, TeamList.teamMap.get(12));

        for (HashMap.Entry entry : Division.divisionTwo.entrySet()){
            Division.divisionTwoList.add((Team) entry.getValue());
        }

        for (HashMap.Entry entry : Division.divisionOne.entrySet()){
            Division.divisionOneList.add((Team) entry.getValue());
        }

        for (HashMap.Entry entry : TeamList.teamMap.entrySet()){
            TeamList.teamMapNames.put(entry.getValue().toString().toLowerCase(), (Team) entry.getValue());
        }
    }
}
