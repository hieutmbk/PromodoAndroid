package techkids.vn.android7pomodoro.networks.services;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import techkids.vn.android7pomodoro.networks.jsonmodels.EditTaskBodyJSon;

/**
 * Created by minhh on 24/02/2017.
 */

public interface DeleteTaskService {
    @DELETE("task/{localID}")
    Call<EditTaskBodyJSon> delTask(@Path("localID") String localID);
}
