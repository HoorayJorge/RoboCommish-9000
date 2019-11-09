import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class EventActions {

    public static void teams(MessageReceivedEvent event){
        String tmp;
        tmp = "Teams\n";

        for (HashMap.Entry entry : TeamList.teamMap.entrySet()){
            tmp = tmp.concat(entry.getValue().toString()+"\n");
        }
        event.getChannel().sendMessage(tmp).queue();
    }

    public static void standings(MessageReceivedEvent event) throws IOException {
        JsonObject rootObj;
        JsonArray standingsObj;


            rootObj = InputController.downloadAPIData("https://www79.myfantasyleague.com/2019/export?TYPE=leagueStandings&L="
                    +InputController.leagueId+"&APIKEY="+InputController.apiKey+"&JSON=1");
            standingsObj = rootObj.getAsJsonObject("leagueStandings").getAsJsonArray("franchise").getAsJsonArray();

            for (int i = 0; i < standingsObj.size(); i++){
                int id = standingsObj.get(i).getAsJsonObject().get("id").getAsInt();
                TeamList.teamMap.get(id).overallRank = i+1;
                TeamList.teamMap.get(id).wins = standingsObj.get(i).getAsJsonObject().get("h2hw").getAsInt();
                TeamList.teamMap.get(id).losses = standingsObj.get(i).getAsJsonObject().get("h2hl").getAsInt();
                TeamList.teamMap.get(id).ties = standingsObj.get(i).getAsJsonObject().get("h2ht").getAsInt();
            }

            Collections.sort(Division.divisionOneList);
            Collections.sort(Division.divisionTwoList);

            String tmp1 = "Standings\nDivision 1"
                    +"\n1. "+Division.divisionOneList.get(0)+" "
                    +Division.divisionOneList.get(0).wins+"-"
                    +Division.divisionOneList.get(0).losses+"-"
                    +Division.divisionOneList.get(0).ties
                    +"\n2. "+Division.divisionOneList.get(1)+" "
                    +Division.divisionOneList.get(1).wins+"-"
                    +Division.divisionOneList.get(1).losses+"-"
                    +Division.divisionOneList.get(1).ties
                    +"\n3. "+Division.divisionOneList.get(2)+" "
                    +Division.divisionOneList.get(2).wins+"-"
                    +Division.divisionOneList.get(2).losses+"-"
                    +Division.divisionOneList.get(2).ties
                    +"\n4. "+Division.divisionOneList.get(3)+" "
                    +Division.divisionOneList.get(3).wins+"-"
                    +Division.divisionOneList.get(3).losses+"-"
                    +Division.divisionOneList.get(3).ties
                    +"\n5. "+Division.divisionOneList.get(4)+" "
                    +Division.divisionOneList.get(4).wins+"-"
                    +Division.divisionOneList.get(4).losses+"-"
                    +Division.divisionOneList.get(4).ties
                    +"\n6. "+Division.divisionOneList.get(5)+" "
                    +Division.divisionOneList.get(5).wins+"-"
                    +Division.divisionOneList.get(5).losses+"-"
                    +Division.divisionOneList.get(5).ties;

            String tmp2 = "Standings\nDivision 2"
                    +"\n1. "+Division.divisionTwoList.get(0)+" "
                    +Division.divisionTwoList.get(0).wins+"-"
                    +Division.divisionTwoList.get(0).losses+"-"
                    +Division.divisionTwoList.get(0).ties
                    +"\n2. "+Division.divisionTwoList.get(1)+" "
                    +Division.divisionTwoList.get(1).wins+"-"
                    +Division.divisionTwoList.get(1).losses+"-"
                    +Division.divisionTwoList.get(1).ties
                    +"\n3. "+Division.divisionTwoList.get(2)+" "
                    +Division.divisionTwoList.get(2).wins+"-"
                    +Division.divisionTwoList.get(2).losses+"-"
                    +Division.divisionTwoList.get(2).ties
                    +"\n4. "+Division.divisionTwoList.get(3)+" "
                    +Division.divisionTwoList.get(3).wins+"-"
                    +Division.divisionTwoList.get(3).losses+"-"
                    +Division.divisionTwoList.get(3).ties
                    +"\n5. "+Division.divisionTwoList.get(4)+" "
                    +Division.divisionTwoList.get(4).wins+"-"
                    +Division.divisionTwoList.get(4).losses+"-"
                    +Division.divisionTwoList.get(4).ties
                    +"\n6. "+Division.divisionTwoList.get(5)+" "
                    +Division.divisionTwoList.get(5).wins+"-"
                    +Division.divisionTwoList.get(5).losses+"-"
                    +Division.divisionTwoList.get(5).ties;

            event.getChannel().sendMessage(tmp1).queue();
            event.getChannel().sendMessage(tmp2).queue();

    }

    public static void picks(MessageReceivedEvent event, String parts[]) throws IOException {
        JsonObject rootObj;
        JsonArray picksObj;
        JsonArray teamObj;
        ArrayList<Pick> teamPicks;

        if(parts.length>1) {
            if (TeamList.teamMapNames.containsKey(parts[1].toLowerCase())) {

                rootObj = InputController.downloadAPIData("https://www79.myfantasyleague.com/2019/export?TYPE=futureDraftPicks&L="
                        +InputController.leagueId+"&APIKEY="
                        +InputController.apiKey+"&JSON=1");
                picksObj = rootObj.getAsJsonObject("futureDraftPicks")
                        .getAsJsonArray("franchise")
                        .getAsJsonArray();
                teamObj = picksObj.get(TeamList.teamMapNames
                        .get(parts[1]
                                .toLowerCase())
                        .teamId-1)
                        .getAsJsonObject()
                        .get("futureDraftPick")
                        .getAsJsonArray();

                String tmp = "Picks\n";

                teamPicks = new ArrayList<>();
                for (int i = 0; i < teamObj.size(); i++){
                    teamPicks.add(new Pick(teamObj.get(i).getAsJsonObject()
                            .get("round").getAsInt(),
                            TeamList.teamMap.get(teamObj.get(i).getAsJsonObject()
                                    .get("originalPickFor").getAsInt()).teamName,
                            teamObj.get(i).getAsJsonObject().get("year").getAsInt()));
                }

                Collections.sort(teamPicks);

                for (int i = 0; i < teamPicks.size(); i++){
                    tmp = tmp.concat(teamPicks.get(i).toString()+"\n");
                }

                event.getChannel().sendMessage(tmp).queue();

            } else {
                event.getChannel().sendMessage("Team not found.  Please use the exact team name listed with the !teams command.").queue();
            }
        }else{
            event.getChannel().sendMessage("Please enter a team: !picks <Team Name>.  A list of teams can be found with the !teams command.").queue();
        }

    }

    public static void privateMessageReceived(PrivateMessageReceivedEvent event){
        if (event.getAuthor().isBot()){
            return;
        }

        System.out.println("Private message received from "
                + event.getAuthor().getName() + ": "
                + event.getMessage().getContentDisplay()
        );

        event.getChannel().sendMessage("I'm sorry, "
                +event.getAuthor().getName()
                +".  I'm afraid I can't do that.").queue();
    }

    public static void commands(MessageReceivedEvent event){
        String tmp = "Commands:\n !commands\n !teams\n !standings\n !picks <Team Name>\n !score <Team Name>";
        event.getChannel().sendMessage(tmp).queue();
    }

    public static void score(MessageReceivedEvent event, String parts[]) throws IOException {
        JsonObject scoringObj;
        JsonObject matchupObj;

        if(parts.length>1) {
            if (TeamList.teamMapNames.containsKey(parts[1].toLowerCase())) {

                Team enteredTeam = TeamList.teamMapNames.get(parts[1].toLowerCase());
                Team teamOne;
                Team teamTwo;
                int week;
                JsonArray arrayObj;

                HashMap<Integer, JsonObject> teamObjectsMap = new HashMap<>();
                StringBuilder message = new StringBuilder();
                ArrayList<Team> matchupTeams = new ArrayList<>();

                scoringObj = InputController.downloadAPIData("https://www79.myfantasyleague.com/2019/export?TYPE=liveScoring&L="
                        + InputController.leagueId+"&APIKEY="
                        +InputController.apiKey+"=&DETAILS=0&JSON=1");

                week = scoringObj.getAsJsonObject("liveScoring").getAsJsonPrimitive("week").getAsInt();

                matchupObj = InputController.downloadAPIData("https://www79.myfantasyleague.com/2019/export?TYPE=schedule&L="
                        +InputController.leagueId+"&APIKEY="
                        +InputController.apiKey+"&W="
                        +week+"&F="
                        +String.format("%04d", enteredTeam.teamId)+"&JSON=1");

                int teamOneInt = matchupObj.getAsJsonObject("schedule")
                        .getAsJsonObject("weeklySchedule")
                        .getAsJsonObject("matchup")
                        .getAsJsonArray("franchise")
                        .get(0).getAsJsonObject()
                        .getAsJsonPrimitive("id").getAsInt();

                teamOne = TeamList.teamMap.get(teamOneInt);
                teamOne.line = matchupObj.getAsJsonObject("schedule")
                        .getAsJsonObject("weeklySchedule")
                        .getAsJsonObject("matchup")
                        .getAsJsonArray("franchise")
                        .get(0).getAsJsonObject()
                        .getAsJsonPrimitive("spread").getAsDouble();

                int teamTwoInt = matchupObj.getAsJsonObject("schedule")
                        .getAsJsonObject("weeklySchedule")
                        .getAsJsonObject("matchup")
                        .getAsJsonArray("franchise")
                        .get(1).getAsJsonObject()
                        .getAsJsonPrimitive("id").getAsInt();

                teamTwo = TeamList.teamMap.get(teamTwoInt);
                teamTwo.line = matchupObj.getAsJsonObject("schedule")
                        .getAsJsonObject("weeklySchedule")
                        .getAsJsonObject("matchup")
                        .getAsJsonArray("franchise")
                        .get(1).getAsJsonObject()
                        .getAsJsonPrimitive("spread").getAsDouble();

                arrayObj = scoringObj
                        .getAsJsonObject("liveScoring")
                        .getAsJsonArray("matchup").getAsJsonArray();

                for (int i = 0; i < arrayObj.size(); i++){

                    for (int j = 0; j < arrayObj
                            .get(i)
                            .getAsJsonObject()
                            .getAsJsonArray("franchise")
                            .size(); j++){

                        JsonObject tmp = arrayObj
                                .get(i)
                                .getAsJsonObject()
                                .getAsJsonArray("franchise")
                                .get(j)
                                .getAsJsonObject();

                        teamObjectsMap.put(tmp.get("id").getAsInt(), tmp);
                    }
                }

                teamOne.addScoringData(teamObjectsMap.get(teamOneInt));
                teamTwo.addScoringData(teamObjectsMap.get(teamTwoInt));

                matchupTeams.add(teamOne);
                matchupTeams.add(teamTwo);

                DecimalFormat df = new DecimalFormat("#,###,##0.00");
                message.append("Score\n");
                message.append(" ---------- \n");
                for (int i = 0; i < matchupTeams.size(); i++){
                    message.append(matchupTeams.get(i).teamName
                            +" -- "+df.format(matchupTeams.get(i).score)+"\n");
                    message.append("Yet to Play: "+matchupTeams.get(i).playersYetToPlay+"\n");
                    message.append("Playing: "+matchupTeams.get(i).playersCurrentlyPlaying+"\n");
                    message.append("Playtime Left: "+matchupTeams.get(i).gameSecondsRemaining+"\n");
                    message.append("Line: "+df.format(matchupTeams.get(i).line)+"\n");
                    message.append(" ---------- \n");
                }

                event.getChannel().sendMessage(message.toString()).queue();

            } else {
                event.getChannel().sendMessage("Team not found.  Please use the exact team name listed with the !teams command.").queue();
            }
        }else{
            event.getChannel().sendMessage("Please enter a team: !score <Team Name>.  A list of teams can be found with the !teams command.").queue();
        }
    }
}
