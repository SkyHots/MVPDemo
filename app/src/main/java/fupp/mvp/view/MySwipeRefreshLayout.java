package fupp.mvp.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by DVO on 2017/7/13 0013.
 *
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    float lastX = 0;
    float lastY = 0;
    boolean ismovepic = false;

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isok = super.onInterceptTouchEvent(ev);

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            lastX = ev.getX();
            lastY = ev.getY();
            ismovepic = false;
            return isok;
        }

        int absX = (int) Math.abs(ev.getX() - lastX);
        int absY = (int) Math.abs(ev.getY() - lastY);

        if (absX > absY) {
            if (absX >= 100)
                ismovepic = true;
            return false;
        }
        //如果移动图片(下拉刷新不处理)
        if (ismovepic) {
            return false;
        }
        return isok;
    }
}
