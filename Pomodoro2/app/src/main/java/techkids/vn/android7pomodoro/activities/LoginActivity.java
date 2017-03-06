package techkids.vn.android7pomodoro.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.networks.NetContext;
import techkids.vn.android7pomodoro.networks.jsonmodels.LoginBodyJson;
import techkids.vn.android7pomodoro.networks.jsonmodels.LoginResponseJson;
import techkids.vn.android7pomodoro.networks.services.LoginService;
import techkids.vn.android7pomodoro.settings.LoginCredentials;
import techkids.vn.android7pomodoro.settings.SharedPrefs;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText etUsername;
    private EditText etPassword;
    private Button btRegister;
    private Button btLogin;

    Retrofit retrofit;

    private String username;
    private String password;
    private String token;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(LoginActivity.this);
        //skipLoginIfPossible();

        setContentView(R.layout.activity_login);

        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btRegister = (Button) this.findViewById(R.id.bt_register);
        btLogin = (Button) this.findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
                progressDialog.setTitle("Login");
                progressDialog.setMessage("Loging ....pls..wait..");
                progressDialog.show();
            }
        });

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d(TAG, "onEditorAction");
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    attemptLogin();
                    Log.d(TAG, "onEditorAction: doneaction");
                    return false;
                }
                return false;
            }
        });

        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(etUsername.length() == 0){
                    etUsername.setError("Tên đăng nhập không được để trống");
                }
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(etPassword.length() == 0){
                    etPassword.setError("Mật khẩu không được để trống");
                }
            }
        });


    }

    private void sendLogin(String username, String password) {
        //1 : Create retrofit (BaseURL)
         LoginService loginService = NetContext.instance.createLoginService();
        // data & format
        // format => MediaType
        // data => json
        MediaType jsonMediaType = MediaType.parse("application/json");
        String loginJson = (new Gson()).toJson(new LoginBodyJson(username, password));

        RequestBody loginBody = RequestBody.create(jsonMediaType, loginJson);

        //3: Create Call
        Call<LoginResponseJson> loginCall = loginService.login(loginBody);

        loginCall.enqueue(new Callback<LoginResponseJson>() {
                    @Override
                    public void onResponse(Call<LoginResponseJson> call, Response<LoginResponseJson> response) {
                        LoginResponseJson loginResponseJson = response.body();
                        if (loginResponseJson == null) {
                            Log.d(TAG, "onResponse: Could not parse body");
                            etUsername.setError("Tên đăng nhập hoặc mật khẩu sai");
                        } else {
                            Log.d(TAG, String.format("onResponse, oh yeah: %s", loginResponseJson));
                            if (response.code() == 200) {
                                token = loginResponseJson.getAccessToken();

                                onLoginSuccess();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseJson> call, Throwable t) {
                        Log.d(TAG, String.format("onFailure: %s", t));
                        Toast.makeText(LoginActivity.this,"Mất kết nối",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }

    private void skipLoginIfPossible() {
        if (SharedPrefs.getInstance().getAccessToken() != null) {
            gotoTaskActivity();
        }
    }

    private void onLoginSuccess() {
        SharedPrefs.getInstance().put(new LoginCredentials(username, password, token));
        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        gotoTaskActivity();
    }


    private void attemptLogin() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        sendLogin(username, password);
    }

    private void gotoTaskActivity() {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}