package techkids.vn.android7pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by minhh on 24/02/2017.
 */

public class DeleteTaskResponeJson {
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private int code;

    public DeleteTaskResponeJson(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DeleteTaskResponeJson{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
