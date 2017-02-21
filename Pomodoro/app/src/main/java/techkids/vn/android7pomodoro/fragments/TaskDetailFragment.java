package techkids.vn.android7pomodoro.fragments;


import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.activities.TaskActivity;
import techkids.vn.android7pomodoro.adapters.TaskColorAdapter;
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.Task;
import techkids.vn.android7pomodoro.decorations.TaskColorDecor;
import techkids.vn.android7pomodoro.fragments.strategies.EditTaskAction;
import techkids.vn.android7pomodoro.fragments.strategies.TaskAction;
import techkids.vn.android7pomodoro.networks.jsonmodels.AddNewTaskResponeJson;
import techkids.vn.android7pomodoro.networks.services.AddNewTaskSerVice;
import techkids.vn.android7pomodoro.settings.SharedPrefs;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment {

    private static String TAG = "TaskDetailFragment";

    @BindView(R.id.rv_task_color)
    RecyclerView rvTaskColor;

    @BindView(R.id.et_task_name)
    EditText etTaskName;

    @BindView(R.id.et_payment_per_hour)
    EditText etPaymentPerHour;

    private TaskColorAdapter taskColorAdapter;

    private String title;
    private Task task;

    private TaskAction taskAction;

    public TaskDetailFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_task_detail, container, false);
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);

        taskColorAdapter = new TaskColorAdapter();

        rvTaskColor.setLayoutManager(new GridLayoutManager(this.getContext(), 4));
        rvTaskColor.setAdapter(taskColorAdapter);
        rvTaskColor.addItemDecoration(new TaskColorDecor());

        if(getActivity() instanceof TaskActivity) {
            ((TaskActivity) getActivity()).getSupportActionBar().setTitle(title);
        }

        if (task != null) {
            // Edit
            etTaskName.setText(task.getName());
            etPaymentPerHour.setText(String.valueOf(task.getPaymentPerHour()));
            taskColorAdapter.setSelectedColor(task.getColor());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_task, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");

        if (item.getItemId() == R.id.mni_ok) {
            Log.d(TAG, "onOptionsItemSelected: OK clicked");

            // 1: Get data from UI
            String taskName = etTaskName.getText().toString();
            float paymentPerHour = Float.parseFloat(etPaymentPerHour.getText().toString());
            String color = taskColorAdapter.getSelectedColor();

            if (task == null) {
                // ADD
                task = new Task(taskName, color, paymentPerHour);
            } else {
                // EDIT
                task.setName(taskName);
                task.setColor(color);
                task.setPaymentPerHour(paymentPerHour);

            }
            addNewTask(task);
            this.taskAction.excute(task);

            getActivity().onBackPressed();
        }
        return false;
    }

    public void setTaskAction(TaskAction taskAction) {
        this.taskAction = taskAction;
    }

    public void addNewTask(Task task){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization","JWT "+ SharedPrefs.getInstance().getAccessToken())
                        .method(original.method(),original.body())
                        .build();
                return  chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        AddNewTaskSerVice addNewTaskSerVice = retrofit.create(AddNewTaskSerVice.class);
        MediaType mediaTypeJson = MediaType.parse("application/json");
        String addTaskJson = (new Gson()).toJson(new AddNewTaskResponeJson(task.getColor(),null,true,null,task.getName(),null,task.getPaymentPerHour()));
        RequestBody addTaskBody = RequestBody.create(mediaTypeJson,addTaskJson);
        Call<AddNewTaskResponeJson> addTaskCall = addNewTaskSerVice.addTask(addTaskBody);
        addTaskCall.enqueue(new Callback<AddNewTaskResponeJson>() {
            @Override
            public void onResponse(Call<AddNewTaskResponeJson> call, Response<AddNewTaskResponeJson> response) {
                AddNewTaskResponeJson addNewTaskResponeJson = response.body();
                if(addNewTaskResponeJson == null){
                    Log.d(TAG, "onResponse: Added");
                }
            }

            @Override
            public void onFailure(Call<AddNewTaskResponeJson> call, Throwable t) {

            }
        });

    }
}
