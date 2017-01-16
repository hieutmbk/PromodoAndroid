package techkids.vn.android7pomodoro.settings;

import android.content.Context;

/**
 * Created by minhh on 16/01/2017.
 */

public class SettingCredentials {
    private int worktime;
    private int breaktime;
    private int longbreaktime;

    public SettingCredentials(int worktime, int breaktime, int longbreaktime) {
        this.worktime = worktime;
        this.breaktime = breaktime;
        this.longbreaktime = longbreaktime;
    }

    public int getWorktime() {
        return worktime;
    }

    public void setWorktime(int worktime) {
        this.worktime = worktime;
    }

    public int getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(int breaktime) {
        this.breaktime = breaktime;
    }

    public int getLongbreaktime() {
        return longbreaktime;
    }

    public void setLongbreaktime(int longbreaktime) {
        this.longbreaktime = longbreaktime;
    }
}
