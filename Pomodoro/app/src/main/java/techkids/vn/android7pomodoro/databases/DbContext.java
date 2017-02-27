package techkids.vn.android7pomodoro.databases;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.view.Display;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.databases.models.TaskColor;

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

    public void addOrUpdate(Task task){
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
    public  List<Task> allTask(){
        return realm.where(Task.class).findAll();
    }

//    public List<Task> allTaskNet(){
//            if(tasks != null){
//                tasks = new ArrayList<>();
//            }
//        return  tasks;
//    }
//
//    public void addTask(Task newTask) {
//        tasks.add(newTask);
//    }
//
//    public void edit(Task task) {
//        //TODO: Edit task in database
//    }
//    public void del(Task task){
//        tasks.remove(task);
//    }



}
