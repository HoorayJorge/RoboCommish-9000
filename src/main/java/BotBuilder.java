import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class BotBuilder {

    public static JDA api;

        static{
            try {
                api = new JDABuilder(AccountType.BOT)
                        .setToken(Token.token)
                        .addEventListeners(new MessageListener())
                        //TODO: Fix weekly announcement
                        .addEventListeners(new ListenerAdapter() {
                            @Override
                            public void onReady(ReadyEvent event) {
                                new EventChannel(event.getJDA()).start(); // starts your channel with the ready event
                            }
                        }).build().awaitReady();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (LoginException e) {
                e.printStackTrace();
            }
        }
    //public static JDA api = (JDA) new JDABuilder(Token.token);
}
