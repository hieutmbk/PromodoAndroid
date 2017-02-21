package techkids.vn.android7pomodoro.networks.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import techkids.vn.android7pomodoro.networks.jsonmodels.GetTaskResponseJson;

/**
 * Created by minhh on 21/02/2017.
 */

public interface GetAllTasksService {
    @GET("task")
    Call<List<GetTaskResponseJson>> getAllTask();
}
