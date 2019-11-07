public class WeeklyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("start of weekly report");
        BotBuilder.api.getTextChannelById(BotBuilder.testChannel).sendMessage("@jorgejorge scheduled test message successful").queue();
        System.out.println("end of weekly report");
    }
}