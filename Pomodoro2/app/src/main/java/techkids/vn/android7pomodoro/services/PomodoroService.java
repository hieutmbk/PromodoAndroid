package techkids.vn.android7pomodoro.services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.events.TickCommandEvent;
import techkids.vn.android7pomodoro.events.TimerCommand;
import techkids.vn.android7pomodoro.events.TimerCommandEvent;

/**
 * Created by minhh on 04/03/2017.
 */

public class PomodoroService extends Service{
    @BindView(R.id.tv_timer)
    TextView tvTimer;
    @BindView(R.id.donut_progress)
    DonutProgress donutProgress;
    private int milliLeft;
    private CountDownTimer countDownTimer;
    private static final String TAG = "Pomodoro Service";

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onCommand(TimerCommandEvent event) {
        Log.d(TAG, "onCommand: hura");
        switch (event.getCommand()){
            case START_TIMER: startTimer(100000); break;
            case STOP_TIMER: onPause(); break;
            case RESUME_TIMER: onResume(); break;
        }
    }
    private void startTimer(long timeLengthMilli){
        countDownTimer = new CountDownTimer(timeLengthMilli,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, String.format("onTick: %s", millisUntilFinished/1000));
                milliLeft = (int) millisUntilFinished;
                TickCommandEvent tick = new TickCommandEvent((int) (millisUntilFinished/1000));
                EventBus.getDefault().post(tick);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish: Done");
            }
        };
        countDownTimer.start();
    }
    public void onPause(){
        countDownTimer.cancel();
    }
    public void onResume(){
        startTimer(milliLeft);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
