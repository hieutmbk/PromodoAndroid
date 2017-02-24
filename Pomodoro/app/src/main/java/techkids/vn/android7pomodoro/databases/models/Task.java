package techkids.vn.android7pomodoro.databases.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huynq on 2/8/17.
 */

public class Task {
    private String name;
    private String color;
    private float paymentPerHour;
    private String localID;
    private boolean isDone;

    public Task(String name, String color, float paymentPerHour, String localID) {
        this.name = name;
        this.color = color;
        this.paymentPerHour = paymentPerHour;
        this.localID = localID;
        this.isDone = false;
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void flipDone() {
        isDone = !isDone;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", paymentPerHour=" + paymentPerHour +
                ", localID='" + localID + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
