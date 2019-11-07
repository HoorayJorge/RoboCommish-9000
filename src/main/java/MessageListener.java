import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@Nonnull PrivateMessageReceivedEvent event) {

        EventActions.privateMessageReceived(event);

    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event){
        String parts[] = event.getMessage().getContentRaw().split(" ", 2);

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!standings")){

            EventActions.standings(event);

        }else if (event.getMessage().getContentRaw().equalsIgnoreCase("!teams")){

            EventActions.teams(event);

        }else if (parts[0].equalsIgnoreCase("!picks")){

            EventActions.picks(event, parts);

        }
    }
}
