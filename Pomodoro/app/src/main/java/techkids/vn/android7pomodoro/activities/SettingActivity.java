package techkids.vn.android7pomodoro.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.settings.LoginCredentials;
import techkids.vn.android7pomodoro.settings.SettingCredentials;
import techkids.vn.android7pomodoro.settings.SettingSharedPrefs;
import techkids.vn.android7pomodoro.settings.SharedPrefs;

public class SettingActivity extends AppCompatActivity {
    private static final String TAG = SettingActivity.class.toString();
    private TextView tvSeekBar1,tvSeekBar2,tvSeekBar3;
    private SeekBar sbSetting1,sbSetting2,sbSetting3;
    private Spinner spLongBreak;
    private int positon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getReferences();
        addListeners();
        setupUI();
    }

    private void setupUI() {
        Integer[] longbreak = new Integer[]{
               0,1,2,3,4,5
        };
        ArrayAdapter<Integer> longbreakadapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                longbreak
        );
        spLongBreak.setAdapter(longbreakadapter);


       if(SettingSharedPrefs.getInstance().getSettingCredentials() == null) {
            sbSetting1.setProgress(25);
            sbSetting2.setProgress(10);
            sbSetting3.setProgress(20);
           spLongBreak.post(new Runnable() {
               @Override
               public void run() {
                   spLongBreak.setSelection(0);
               }
           });

        }
        else {
           sbSetting1.setProgress(SettingSharedPrefs.getInstance().getSettingCredentials().getWorktime());
           sbSetting2.setProgress(SettingSharedPrefs.getInstance().getSettingCredentials().getBreaktime());
           sbSetting3.setProgress(SettingSharedPrefs.getInstance().getSettingCredentials().getLongbreaktime());
           spLongBreak.post(new Runnable() {
               @Override
               public void run() {
                   SharedPreferences sharedPreferences = getSharedPreferences("setting",MODE_PRIVATE);
                   int positon  = Integer.parseInt(sharedPreferences.getString("positon", null));
                   spLongBreak.setSelection(positon);
               }
           });
       }

    }

    private void addListeners() {
        sbSetting1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekBar1.setText(String.format("Work time %s mins",sbSetting1.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SettingSharedPrefs.getInstance().put(new SettingCredentials(sbSetting1.getProgress(),sbSetting2.getProgress(),sbSetting3.getProgress()));
            }
        });
        sbSetting2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekBar2.setText(String.format("Break %s mins",sbSetting2.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SettingSharedPrefs.getInstance().put(new SettingCredentials(sbSetting1.getProgress(),sbSetting2.getProgress(),sbSetting3.getProgress()));
            }
        });
        sbSetting3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekBar3.setText(String.format("Long break %s mins",sbSetting3.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SettingSharedPrefs.getInstance().put(new SettingCredentials(sbSetting1.getProgress(),sbSetting2.getProgress(),sbSetting3.getProgress()));

            }
        });

        spLongBreak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPreferences = getSharedPreferences("setting",MODE_PRIVATE);
                sharedPreferences.edit().putString("positon", String.valueOf(spLongBreak.getSelectedItemPosition())).commit();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getReferences() {
        tvSeekBar1 = (TextView) findViewById(R.id.tv_seekbar_progress_1);
        tvSeekBar2 = (TextView) findViewById(R.id.tv_seekbar_progress_2);
        tvSeekBar3 = (TextView) findViewById(R.id.tv_seekbar_progress_3);

        sbSetting1 = (SeekBar) findViewById(R.id.sb_simple_1);
        sbSetting2 = (SeekBar) findViewById(R.id.sb_simple_2);
        sbSetting3 = (SeekBar) findViewById(R.id.sb_simple_3);

        spLongBreak = (Spinner) findViewById(R.id.sp_longbreak);
    }
}
