package techkids.vn.android7pomodoro.databases;

import java.util.ArrayList;
import java.util.List;

import techkids.vn.android7pomodoro.databases.models.Task;

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
        tasks.add(new Task(" ","#B71C1C"));
        tasks.add(new Task(" ","#6A1B9A"));
        tasks.add(new Task(" ","#00E676"));
        tasks.add(new Task(" ","#E65100"));
        tasks.add(new Task(" ","#4E342E"));
        return tasks;
    }
}
