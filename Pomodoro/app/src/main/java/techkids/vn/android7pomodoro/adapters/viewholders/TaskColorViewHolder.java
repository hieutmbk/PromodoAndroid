package techkids.vn.android7pomodoro.adapters.viewholders;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import techkids.vn.android7pomodoro.R;
import techkids.vn.android7pomodoro.databases.models.TaskColor;

/**
 * Created by minhh on 13/02/2017.
 */

public class TaskColorViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_task_color)
    ImageView ivTaskColor;
    boolean selected = false;

    public TaskColorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void bind(String color){
        GradientDrawable drawable = (GradientDrawable) ivTaskColor.getBackground();
        drawable.setColor(Color.parseColor(color));
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
    }
}
