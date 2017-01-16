package techkids.vn.android7pomodoro.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by minhh on 16/01/2017.
 */

public class SettingSharedPrefs {
    private static final String SHARE_PREFS_NAME = "ST";
    private static final String SETTING_KEY = "Time";
    private SharedPreferences settingSharedPrefs;
    private Gson gson;

    private static SettingSharedPrefs instance;

    public static SettingSharedPrefs getInstance() {
        return instance;
    }
    public static  void init(Context context){
        instance = new SettingSharedPrefs(context);
    }

    public SettingSharedPrefs(Context context){
        this.settingSharedPrefs = context.getSharedPreferences(
                SHARE_PREFS_NAME,
                Context.MODE_PRIVATE
        );
        gson = new Gson();
    }

    public void put(SettingCredentials settingCredentials){
        String timeJSON = gson.toJson(settingCredentials);

        this.settingSharedPrefs.edit().putString(SETTING_KEY,timeJSON).commit();
    }
    public SettingCredentials getSettingCredentials(){
        String settingJSON = this.settingSharedPrefs.getString(SETTING_KEY, null);
        if(settingJSON==null) return null;
        SettingCredentials settingDetail = (new Gson()).fromJson(settingJSON, SettingCredentials.class);
        return settingDetail;
    }
}
