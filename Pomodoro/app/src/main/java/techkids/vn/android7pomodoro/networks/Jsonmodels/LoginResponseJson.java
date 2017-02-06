package techkids.vn.android7pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by minhh on 18/01/2017.
 */

public class LoginResponseJson {
    @SerializedName("access_token")
    private String accsessToken;

    public String getAccsessToken() {
        return accsessToken;
    }
}
