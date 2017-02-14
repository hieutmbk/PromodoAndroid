package techkids.vn.android7pomodoro.databases.models;

/**
 * Created by minhh on 13/02/2017.
 */

public class TaskColor {
    private String color;

    public TaskColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TaskColor{" +
                "color='" + color + '\'' +
                '}';
    }
}
