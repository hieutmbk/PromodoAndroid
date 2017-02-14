package techkids.vn.android7pomodoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.adapters.viewholders.TaskColorViewHolder;
import techkids.vn.android7pomodoro.databases.DbContext;
import techkids.vn.android7pomodoro.databases.models.TaskColor;

/**
 * Created by minhh on 13/02/2017.
 */

public class TableColorAdapter extends RecyclerView.Adapter<TaskColorViewHolder> {
    @Override
    public TaskColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_color_task,parent,false);
        TaskColorViewHolder taskColorViewHolder = new TaskColorViewHolder(itemView);
        return  taskColorViewHolder;
    }

    @Override
    public void onBindViewHolder(TaskColorViewHolder holder, int position) {
        String taskColor = DbContext.colors[position];
        holder.bind(taskColor);
    }

    @Override
    public int getItemCount() {
       return DbContext.colors.length;
    }
}
