public class WeeklyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("start of weekly report");
        BotBuilder.api.getTextChannelById(BotBuilder.testChannel).sendMessage("This is a test message!").queue();
        System.out.println("end of weekly report");
    }
}