package techkids.vn.android7pomodoro.databases;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by huynq on 2/8/17.
 */

public class DbContext {

    private Realm realm;

    public static final DbContext instance = new DbContext();

    public void initRealm(Context context){
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }
    public Task add(Task task){
        realm.beginTransaction();
        Task addedTask = realm.copyToRealm(task);
        realm.commitTransaction();
        return  addedTask;
    }

    public void Edit(Task old,String name,String color,float paymentPerHour){
        realm.beginTransaction();
        old.setName(name);
        old.setColor(color);
        old.setPaymentPerHour(paymentPerHour);
        realm.commitTransaction();
    }
    public void addorUpdate(Task task){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(task);
        realm.commitTransaction();
    }
    public void delete(Task task){
        realm.beginTransaction();
        Task existingTask = realm.where(Task .class).equalTo("id", task.getId()).findFirst();
        existingTask.deleteFromRealm();
        realm.commitTransaction();
    }
    public void  deleteALl(){
        realm.beginTransaction();
        realm.delete(Task.class);
        realm.commitTransaction();
    }
    public  List<Task> allTask(){
        return realm.where(Task.class).findAll();
    }


}
