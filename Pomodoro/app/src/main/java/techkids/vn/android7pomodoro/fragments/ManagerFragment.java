package techkids.vn.android7pomodoro.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by minhh on 14/02/2017.
 */

public class ManagerFragment {
    private FragmentManager fragmentManager;
    private int id_fl_main;

    public ManagerFragment(FragmentManager fragmentManager, int id_fl_main) {
        this.fragmentManager = fragmentManager;
        this.id_fl_main = id_fl_main;
    }
    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id_fl_main,fragment);
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
