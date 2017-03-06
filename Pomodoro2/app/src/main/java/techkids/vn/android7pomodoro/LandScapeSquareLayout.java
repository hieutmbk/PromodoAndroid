package techkids.vn.android7pomodoro;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by minhh on 04/03/2017.
 */

public class LandScapeSquareLayout extends RelativeLayout {

    public LandScapeSquareLayout(Context context) {
        super(context);
    }

    public LandScapeSquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LandScapeSquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = height;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }
}
