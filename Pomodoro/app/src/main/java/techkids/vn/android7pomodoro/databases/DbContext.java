package techkids.vn.android7pomodoro.databases;

import java.util.ArrayList;
import java.util.List;

import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.databases.models.TaskColor;

/**
 * Created by minhh on 08/02/2017.
 */

public class DbContext {
    public final static DbContext instance = new DbContext();
    public List<Task> allTask(){
        //Fake data (dummy data)
        //1: Create array list
        ArrayList<Task> tasks = new ArrayList<>();
        //2: Add some task and return
        tasks.add(new Task("Study recycler view","#B71C1C"));
        tasks.add(new Task("Study recycler view","#6A1B9A"));
        tasks.add(new Task("Study recycler view","#00E676"));
        tasks.add(new Task("Study recycler view","#E65100"));
        tasks.add(new Task("Study recycler view","#4E342E"));
        return tasks;
    }
    public static String[] colors = {"#B71C1C","#6A1B9A","#00E676","#E65100","#4E342E","#76FF03","#E65100","#455A64"};
}
