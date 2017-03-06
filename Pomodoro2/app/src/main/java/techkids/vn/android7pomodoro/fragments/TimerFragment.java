package techkids.vn.android7pomodoro.fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.events.TickCommandEvent;
import techkids.vn.android7pomodoro.events.TimerCommand;
import techkids.vn.android7pomodoro.events.TimerCommandEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {
    private static final String TAG = TimerFragment.class.toString();
    CountDownTimer timer;
    @BindView(R.id.bt_pause)
    Button btPause;
    @BindView(R.id.donut_progress)
    DonutProgress donutProgress;
    @BindView(R.id.tv_timer)
    TextView tvTimer;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_timer, container, false);
        setupUI(view);
        return  view;


    }
    public  void setupUI(View view){
        ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countdown();
                if(btPause.getText().equals("Pause")) {
                    TimerCommandEvent eventPause = new TimerCommandEvent(TimerCommand.STOP_TIMER);
                    EventBus.getDefault().post(eventPause);
                    btPause.setText("Resume");
                }
                else if (btPause.getText().equals("Resume")){
                    TimerCommandEvent eventResume= new TimerCommandEvent(TimerCommand.RESUME_TIMER);
                    EventBus.getDefault().post(eventResume);
                    btPause.setText("Pause");
                }
            }
        });

    }
    @Subscribe
    public void onSetupProgress(TickCommandEvent tick){
        donutProgress.setProgress((int) tick.getMillis());
        tvTimer.setText(String.format("%d", (int) tick.getMillis()));
        if (tick.getMillis() == 0) tvTimer.setText("DONE");

    }
    private void countdown() {
        timer = new CountDownTimer(60*1*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, String.format("onTick: %s", millisUntilFinished/1000));
                donutProgress.setMax(180);
                donutProgress.setProgress((int)(millisUntilFinished/1000));
                int minute = (int) (millisUntilFinished/60000);
                int second = (int) (millisUntilFinished/1000 - minute*60);
                tvTimer.setText(String.format("%d:%02d", minute,second));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("0:00");
            }
        };
        timer.start();
    }
}
