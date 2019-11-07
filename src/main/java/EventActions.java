import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

public class EventActions {

    public static void teams(MessageReceivedEvent event){
        String tmp;
        tmp = "Teams\n";

        for (HashMap.Entry entry : TeamList.teamMap.entrySet()){
            tmp = tmp.concat(entry.getValue().toString()+"\n");
        }
        event.getChannel().sendMessage(tmp).queue();
    }

    public static void standings(MessageReceivedEvent event){
        JsonObject rootObj;
        JsonArray standingsObj;

        try {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void picks(MessageReceivedEvent event, String parts[]){

        if(parts.length>1) {
            if (TeamList.teamMapNames.containsKey(parts[1].toLowerCase())) {
                System.out.println(TeamList.teamMapNames.get(parts[1].toLowerCase()).teamName);
            } else {
                System.out.println("Team not found.  Please use the exact team name listed with the !teams command.");
            }
        }else{
            System.out.println("Please enter a team: !picks <Team Name>.  A list of teams can be found with the !teams command.");
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
}
