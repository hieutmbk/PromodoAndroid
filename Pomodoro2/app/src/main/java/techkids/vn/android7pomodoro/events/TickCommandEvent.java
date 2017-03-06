package techkids.vn.android7pomodoro.events;

/**
 * Created by minhh on 06/03/2017.
 */

public class TickCommandEvent {
    private long millis;

    public TickCommandEvent(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }
}
