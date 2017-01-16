package techkids.vn.android7pomodoro;

import android.app.Application;
import android.util.Log;

import techkids.vn.android7pomodoro.settings.SettingSharedPrefs;
import techkids.vn.android7pomodoro.settings.SharedPrefs;

/**
 * Created by minhh on 14/01/2017.
 */

public class PomodoroApplication extends Application {
    private static final String TAG = PomodoroApplication.class.toString();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, String.format("onCreate "));
        SharedPrefs.init(this);
        SettingSharedPrefs.init(this);

    }
}
