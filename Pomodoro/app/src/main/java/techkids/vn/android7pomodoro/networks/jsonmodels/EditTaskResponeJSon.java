package techkids.vn.android7pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by minhh on 23/02/2017.
 */

public class EditTaskResponeJSon {
    @SerializedName("color")
    private String color;
    @SerializedName("due_date")
    private String duedate;
    @SerializedName("done")
    private boolean done;
    @SerializedName("name")
    private String name;
    @SerializedName("local_id")
    private String localid;
    @SerializedName("payment_per_hour")
    private float paymentPerHour;

    public EditTaskResponeJSon(String color, String duedate, boolean done, String name, String localid, float paymentPerHour) {
        this.color = color;
        this.duedate = duedate;
        this.done = done;
        this.name = name;
        this.localid = localid;
        this.paymentPerHour = paymentPerHour;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalid() {
        return localid;
    }

    public void setLocalid(String localid) {
        this.localid = localid;
    }

    public float getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(float paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    @Override
    public String toString() {
        return "EditTaskResponeJSon{" +
                "color='" + color + '\'' +
                ", duedate='" + duedate + '\'' +
                ", done=" + done +
                ", name='" + name + '\'' +
                ", localid='" + localid + '\'' +
                ", paymentPerHour=" + paymentPerHour +
                '}';
    }
}
