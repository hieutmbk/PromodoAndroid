package com.example.minhh.commomviews;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private ImageView imv1;
    private Spinner spFruit;
    private EditText etSimple;
    private Button btnTest;
    private CheckBox cbFA;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private RadioButton rbUndefined;
    private RatingBar rtbSimple;
    private SeekBar sbSimple;
    private TextView tvSeekBarProgress;
    private SearchView svSimple;
    private ProgressBar pbSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferences();
        setupUI();
        addListeners();
    }

    private void addListeners() {
        spFruit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,String.format("%s %d","spFruit.onItemSelected",position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG,"onNothingSelected");
            }
        });
        etSimple.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG,String.format("beforeTextChanged: CharSequence %s,start %s,count %s,after %s",s,start,count,after));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG,String.format("onTextChanged: CharSequence %s,start %s,count %s,after %s",s,start,before,count));
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG,String.format(String.format("afterTextChanged %s",s)));
            }
        });

        sbSimple.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG,String.format("SeekBar: onProgressChanged: %s, %s",progress,fromUser));
                tvSeekBarProgress.setText(String.format("%s/%s",progress,seekBar.getMax()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG,"onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG,String.format("onStopTrackingTouch %s",seekBar.getProgress()));
            }
        });
        svSimple.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, String.format("SearchView: onQueryTextSubmit %s",query));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, String.format("SearchView: onQueryTextChange %s",newText));
                return false;
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Read
                Log.d(TAG,String.format("%s:%s",cbFA.isChecked() ));
                //Write
                cbFA.setChecked(!cbFA.isChecked());
                //
                Log.d(TAG,rbMale.isChecked()?"Male":rbFemale.isChecked()?"Female":"Undefined");

                Log.d(TAG,String.format("rating %s",rtbSimple.getRating()));
                //SeekBar Read
                Log.d(TAG,String.format("seekbar : %s",sbSimple.getProgress()));

                sbSimple.setProgress(sbSimple.getProgress() + 10);

                svSimple.clearFocus();
                svSimple.setQuery("",false);
                svSimple.setIconified(true);

                pbSimple.setProgress(pbSimple.getProgress() + 10);
            }
        });
        cbFA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG,String.format("cbFA.onCheckedChanged : %s",isChecked));
            }
        });
    }

    public void getReferences() {
        imv1 = (ImageView) findViewById(R.id.imv1);
        spFruit = (Spinner) findViewById(R.id.sp_fruit);
        etSimple = (EditText) findViewById(R.id.et_simple);
        btnTest = (Button) findViewById(R.id.btn_test);
        cbFA = (CheckBox) findViewById(R.id.chFA);

        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rbUndefined = (RadioButton) findViewById(R.id.rb_undefined);

        rtbSimple = (RatingBar) findViewById(R.id.rtb_simple);
        sbSimple = (SeekBar) findViewById(R.id.sb_simple);
        tvSeekBarProgress = (TextView) findViewById(R.id.tv_seekbar_progress);
        svSimple = (SearchView) findViewById(R.id.sv_simpe);
        pbSimple = (ProgressBar) findViewById(R.id.pb_simple);
    }

    private void setupUI() {
        imv1.setImageResource(R.drawable.anh1);

        String[] fruits =  new String[] {
                "Apple",
                "Orange",
                "Kiwi",
                "Pinaple"
        };

        ArrayAdapter<String> fruitArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                fruits
        );

        spFruit.setAdapter(fruitArrayAdapter);
        spFruit.post(new Runnable() {
            @Override
            public void run() {
                spFruit.setSelection(2);
            }
        });
        rbMale.setChecked(true);
    }
}
