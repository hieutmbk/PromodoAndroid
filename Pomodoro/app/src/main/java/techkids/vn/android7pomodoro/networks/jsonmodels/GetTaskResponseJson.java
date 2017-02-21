package techkids.vn.android7pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by minhh on 21/02/2017.
 */

public class GetTaskResponseJson {
    @SerializedName("name")
    private String name;
    @SerializedName("color")
    private String color;
    @SerializedName("payment_per_hour")
    private float paymentPerHour;

    public GetTaskResponseJson(String name, String color, float paymentPerHour) {
        this.name = name;
        this.color = color;
        this.paymentPerHour = paymentPerHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(float paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    @Override
    public String toString() {
        return "GetTaskResponseJson{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", paymentPerHour=" + paymentPerHour +
                '}';
    }
}
