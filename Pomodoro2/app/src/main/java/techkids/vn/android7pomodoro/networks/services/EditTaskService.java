package techkids.vn.android7pomodoro.networks.services;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import techkids.vn.android7pomodoro.networks.jsonmodels.EditTaskBodyJSon;

/**
 * Created by minhh on 23/02/2017.
 */

public interface EditTaskService {
    @PUT("task/{localID}")
    Call<EditTaskBodyJSon> editTask(@Path("localID") String localID,
                                    @Body RequestBody body);
}
