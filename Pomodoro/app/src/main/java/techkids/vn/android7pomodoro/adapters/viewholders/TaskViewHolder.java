package techkids.vn.android7pomodoro.adapters.viewholders;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.databases.models.Task;

/**
 * Created by minhh on 08/02/2017.
 */

public class TaskViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.v_task_color)
    ImageView ivTaskColor;

    @BindView(R.id.tv_task_name)
    TextView tvTaskName;

    boolean selected = false;
    public TaskViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void bind(Task task){
        //1: bind color
       // vTaskColor.setBackgroundColor(Color.parseColor(task.getColor()));
        final GradientDrawable drawable = (GradientDrawable)ivTaskColor.getBackground();
        drawable.setColor(Color.parseColor(task.getColor()));
        ivTaskColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected == false){
                    ivTaskColor.setImageResource(R.drawable.ic_check);
                    selected = true;
                }
                else {
                    selected = false;
                    ivTaskColor.setImageResource(R.color.colorTransparent);
                }
            }

        });
        //2: Bind task name
        tvTaskName.setText(task.getName());
    }
}
