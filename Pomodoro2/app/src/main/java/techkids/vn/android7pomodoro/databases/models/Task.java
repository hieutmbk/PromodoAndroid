package techkids.vn.android7pomodoro.databases.models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by huynq on 2/8/17.
 */

public class Task extends RealmObject{
    @PrimaryKey
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("color")
    private String color;
    @SerializedName("payment_per_hour")
    private float paymentPerHour;
    @SerializedName("local_id")
    private String localID;
    private boolean isDone;

    public Task(){
        this(null,null,1.1f,null);
    }

    public Task(String name, String color, float paymentPerHour, String localID) {
        this.id = UUID.randomUUID().toString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
