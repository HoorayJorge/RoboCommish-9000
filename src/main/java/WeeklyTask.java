import net.dv8tion.jda.api.JDABuilder;

public class WeeklyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("start of weekly report");
        BotBuilder.api.getTextChannelById(640896377488146438L).sendMessage("This is a test message!");
        System.out.println("end of weekly report");
    }
}