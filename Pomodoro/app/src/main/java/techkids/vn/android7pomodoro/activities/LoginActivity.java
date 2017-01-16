package techkids.vn.android7pomodoro.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.settings.LoginCredentials;
import techkids.vn.android7pomodoro.settings.SharedPrefs;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.toString();
    private EditText etUsername;
    private EditText etPassword;
    private Button btRegister;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        skipLoginIfPossible();

        setContentView(R.layout.activity_login);

        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btRegister = (Button) this.findViewById(R.id.bt_register);
        btLogin = (Button) this.findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        SharedPreferences sharedPreferences = this.getSharedPreferences("setting",MODE_PRIVATE);

        SharedPrefs sharedPrefs = new SharedPrefs(this);
        sharedPrefs.put(new LoginCredentials("hieu","xxxx!"));
        Log.d(TAG, String.format("onCreate: %s",sharedPrefs.getLoginCredentials().toString() ));
    }

    private void skipLoginIfPossible() {
        if(SharedPrefs.getInstance().getLoginCredentials() != null){
            gotoTaskActivity();
        }
    }

    private void attemptLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.equals("admin") && password.equals("admin")) {
            // Notifications
            SharedPrefs.getInstance().put(new LoginCredentials(username,password));
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
            gotoTaskActivity();

        }else Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
    }
    private  void gotoTaskActivity(){
        Intent intent = new Intent(this,TaskActivity.class);
        startActivity(intent);
    }
}
