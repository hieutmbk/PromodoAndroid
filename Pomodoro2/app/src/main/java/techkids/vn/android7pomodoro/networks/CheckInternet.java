package techkids.vn.android7pomodoro.networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by minhh on 27/02/2017.
 */

public class CheckInternet {

    public static boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected() && info.isAvailable();
    }
}
