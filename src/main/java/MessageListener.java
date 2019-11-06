import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.io.IOException;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@Nonnull PrivateMessageReceivedEvent event) {
        if (event.getAuthor().isBot()){
            return;
        }

        System.out.println("Private message received from "
                + event.getAuthor().getName() + ": "
                + event.getMessage().getContentDisplay()
        );

        /*if(event.getMessage().getContentRaw().equals("!dap")){
            BotBuilder.api
                    .getTextChannelById(BotBuilder.testChannel)
                    .sendMessage("\uD83E\uDD1C\uD83C\uDFFF"+"\uD83D\uDCA5"+"\uD83E\uDD1B\uD83C\uDFFC").queue();
        }else{
            */event.getChannel().sendMessage("I'm sorry, "
                    +event.getAuthor().getName()
                    +".  I'm afraid I can't do that.").queue();
        //}
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event){
        JsonObject rootObj;
        JsonArray standingsObj;

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!standings")){
            try {
                rootObj = InputController.downloadAPIData("https://www79.myfantasyleague.com/2019/export?TYPE=leagueStandings&L="
                        +InputController.leagueId+"&APIKEY="+InputController.apiKey+"&JSON=1");
                standingsObj = rootObj.getAsJsonObject("leagueStandings").getAsJsonArray("franchise").getAsJsonArray();

                for (int i = 0; i < standingsObj.size(); i++){
                    int id = standingsObj.get(i).getAsJsonObject().get("id").getAsInt();
                    event.getChannel().sendMessage( (i+1)+". "+TeamList.teamMap.get(id)).queue();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
