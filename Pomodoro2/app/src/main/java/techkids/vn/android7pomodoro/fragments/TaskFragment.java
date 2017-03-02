package techkids.vn.android7pomodoro.fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.activities.TaskActivity;
import techkids.vn.android7pomodoro.adapters.TaskAdapter;
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.fragments.strategies.AddTaskAction;
import techkids.vn.android7pomodoro.fragments.strategies.EditTaskAction;
import techkids.vn.android7pomodoro.networks.CheckInternet;
import techkids.vn.android7pomodoro.networks.NetContext;
import techkids.vn.android7pomodoro.networks.jsonmodels.EditTaskBodyJSon;
import techkids.vn.android7pomodoro.networks.services.DeleteTaskService;
import techkids.vn.android7pomodoro.networks.services.GetAllTasksService;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {

    @BindView(R.id.rv_task)
    RecyclerView rvTask;

    private TaskAdapter taskAdapter;

    private static String TAG = "TaskFragment";
    ProgressDialog progressDialog;


    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_task, container, false);
        setupUI(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        taskAdapter = new TaskAdapter();
        rvTask.setAdapter(taskAdapter);
        rvTask.setLayoutManager(new LinearLayoutManager(this.getContext()));

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Load Data");
        progressDialog.show();

        taskAdapter.setTaskItemClickListener(new TaskAdapter.TaskItemClickListener() {
            @Override
            public void onItemClick(final Task task) {
                Log.d(TAG, String.format("onItemClick: %s", task));
                TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
                taskDetailFragment.setLocalID(task.getLocalID());

                taskDetailFragment.setTitle("Edit task");
                taskDetailFragment.setTask(task);
                taskDetailFragment.setTaskAction(new EditTaskAction());

                //TODO: Make TaskActivity and Fragment independent
                ((TaskActivity)getActivity()).replaceFragment(taskDetailFragment, true);
            }
        });
        taskAdapter.setTaskItemLongClickListener(new TaskAdapter.TaskItemLongClickListener() {
            @Override
            public void onItemLongClick(final Task task) {
                final AlertDialog.Builder delDialog = new AlertDialog.Builder(TaskFragment.this.getContext());
                delDialog.setTitle("Delete?");
                delDialog.setMessage("Are you sure you want to delete?");
                delDialog.setPositiveButton("Yes", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                       deleteTask(task);
                        DbContext.instance.delete(task);
                        taskAdapter.notifyDataSetChanged();
                    }});
                delDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                delDialog.show();
            }
        });

        taskAdapter.setTaskTimerListener(new TaskAdapter.TaskTimerListener() {
            @Override
            public void onStart(Task task) {
                Log.d(TAG, "onStart: starting timer");
                ((TaskActivity)getActivity()).replaceFragment(new TimerFragment(), true);
            }
        });


        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setTitle(R.string.tasks);

        //menu options

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        rvTask.addItemDecoration(dividerItemDecoration);

        setHasOptionsMenu(true);

        if(CheckInternet.isConnected(this.getContext()) == true){
            getAllTasks();
        }
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        TaskDetailFragment taskDetailFragment = new TaskDetailFragment();
        taskDetailFragment.setTitle("Add new task");
        taskDetailFragment.setTaskAction(new AddTaskAction());


        //TODO: Make TaskActivity and Fragment independent
        ((TaskActivity)getActivity()).replaceFragment(taskDetailFragment, true);
    }
    public void deleteTask(Task task){
        DeleteTaskService delService = NetContext.instance.creDeleteTaskService();

        delService.delTask(task.getLocalID()).enqueue(new Callback<EditTaskBodyJSon>() {
            @Override
            public void onResponse(Call<EditTaskBodyJSon> call, Response<EditTaskBodyJSon> response) {
                EditTaskBodyJSon delTaskResponeJSon = response.body();
                if (delTaskResponeJSon != null) {
                    Log.d(TAG, String.format("onResponse: %s", delTaskResponeJSon));
                }else {
                    Log.d(TAG, "Không xóa được");
                }
            }

            @Override
            public void onFailure(Call<EditTaskBodyJSon> call, Throwable t) {
                Log.d(TAG, String.format("onFailure: %s", t));
            }
        });
    }
    private void getAllTasks(){
        GetAllTasksService getAllTasksService = NetContext.instance.createGetTaskService();
        getAllTasksService.getAllTask().enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {

                for(Task task : response.body()) {
                    if (task != null) {
                        progressDialog.dismiss();
                        DbContext.instance.addorUpdate(task);
                    }else
                    {
                        Log.d(TAG, "Không get được Task");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Log.d(TAG, "onFailure: %s",t);
            }
        });
    }

}
