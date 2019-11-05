import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

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
}
