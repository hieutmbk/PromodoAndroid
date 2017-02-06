package techkids.vn.android7pomodoro.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by minhh on 14/01/2017.
 */

public class SharedPrefs {
    private static final String SHARE_PREFS_NAME = "SP";
    private static final String LOGIN_KEY = "Login";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private static SharedPrefs instance;

    public static SharedPrefs getInstance(){
        return instance;
    }

    public static  void init(Context context){
        instance = new SharedPrefs(context);
    }

    public SharedPrefs(Context context){
        this.sharedPreferences = context.getSharedPreferences(
                SHARE_PREFS_NAME,
                Context.MODE_PRIVATE

        );
        gson = new Gson();
    }
    public void put(LoginCredentials loginCredentials){
        //loginCredentials => json string
        String loginJSON = gson.toJson(loginCredentials);
        //put
        this.sharedPreferences.edit().putString(LOGIN_KEY,loginJSON).commit();
    }
    public LoginCredentials getLoginCredentials(){
        //1.Get String
        String loginJSON = this.sharedPreferences.getString(LOGIN_KEY,null);
        //2.Convert string to object
        if(loginJSON == null) return  null;
        LoginCredentials loginCredentials = gson.fromJson(loginJSON,LoginCredentials.class);
        return loginCredentials;
    }
    public String getAccessToken(){
        LoginCredentials loginCredentials = getLoginCredentials();
        if(loginCredentials != null){
            return getAccessToken();
        }
        return null;
    }
}
