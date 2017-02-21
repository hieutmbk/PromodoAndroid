package techkids.vn.android7pomodoro.networks.services;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import techkids.vn.android7pomodoro.networks.jsonmodels.AddNewTaskResponeJson;

/**
 * Created by minhh on 21/02/2017.
 */

public interface AddNewTaskSerVice {
    @POST("task")
    Call<AddNewTaskResponeJson> addTask(@Body RequestBody body);
}
