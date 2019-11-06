import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeeklyReportService {

    public WeeklyReportService() {
        this.startScheduler();
    }

    private void startScheduler() {
        Calendar with = Calendar.getInstance();
        Map<Integer, Integer> dayToDelay = new HashMap<Integer, Integer>();
        dayToDelay.put(Calendar.FRIDAY, 4);
        dayToDelay.put(Calendar.SATURDAY, 3);
        dayToDelay.put(Calendar.SUNDAY, 2);
        dayToDelay.put(Calendar.MONDAY, 1);
        dayToDelay.put(Calendar.TUESDAY, 0);
        dayToDelay.put(Calendar.WEDNESDAY, 6);
        dayToDelay.put(Calendar.THURSDAY, 5);
        int dayOfWeek = with.get(Calendar.DAY_OF_WEEK);
        int hour = with.get(Calendar.HOUR_OF_DAY);
        int delayInDays = dayToDelay.get(dayOfWeek);
        int delayInHours = 0;
        if (delayInDays == 6 && hour < 2) {
            delayInHours = 2 - hour;
        } else {
            delayInHours = delayInDays * 24 + ((24 - hour) + 2);
        }
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new WeeklyTask(), delayInHours,
                179, TimeUnit.HOURS);
    }
}