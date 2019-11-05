import net.dv8tion.jda.api.JDA;

public class EventChannel {
    private final JDA api;

    public EventChannel(JDA api) {
        this.api = api;
    }

    public void start() {
        new WeeklyReportService();
    }
}
