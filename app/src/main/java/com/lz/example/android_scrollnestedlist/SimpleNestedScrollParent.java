package com.lz.example.android_scrollnestedlist;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 子view	                父view
 * startNestedScroll	    onStartNestedScroll、onNestedScrollAccepted
 * dispatchNestedPreScroll	onNestedPreScroll
 * dispatchNestedScroll	    onNestedScroll
 * stopNestedScroll	        onStopNestedScroll
 */
public class SimpleNestedScrollParent extends RelativeLayout implements NestedScrollingParent {

    private static final String TAG = "NestedScrollImpl";

    private NestedScrollingParentHelper mParentHelper;

    public SimpleNestedScrollParent(Context context) {
        super(context);
        init();
    }

    public SimpleNestedScrollParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleNestedScrollParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mParentHelper = new NestedScrollingParentHelper(this);
    }

    /**
     * 开始滚动，子view调用startNestedScroll时,会调用该方法
     *
     * @param child
     * @param target
     * @param nestedScrollAxes
     * @return true 会调用onNestedScrollAccepted方法
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.e(TAG, "parent onStartNestedScroll");
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    /**
     * @param child
     * @param target
     * @param axes
     */
    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
        Log.e(TAG, "parent onNestedScrollAccepted");
    }

    /**
     * 停止滚动,子view调用stopNestedScroll时会调用该方法
     *
     * @param child
     */
    @Override
    public void onStopNestedScroll(View child) {
        Log.e(TAG, "parent onStopNestedScroll");
        super.onStopNestedScroll(child);
    }


    /**
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // 应该移动的Y距离
        final float shouldMoveY = getY() + dy;

        // 获取到父View的容器的引用，这里假定父View容器是View
        final View parent = (View) getParent();

        int consumedY;
        // 如果超过了父View的上边界，只消费子View到父View上边的距离
        if (shouldMoveY <= 0) {
            consumedY = -(int) getY();
        } else if (shouldMoveY >= parent.getHeight() - getHeight()) {
            // 如果超过了父View的下边界，只消费子View到父View
            consumedY = (int) (parent.getHeight() - getHeight() - getY());
        } else {
            // 其他情况下全部消费
            consumedY = dy;
        }

        // 对父View进行移动
        setY(getY() + consumedY);

        // 将父View消费掉的放入consumed数组中
        consumed[1] = consumedY;

        Log.e(TAG, "parent onNestedPreScroll");
    }

    /**
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.e(TAG, "parent onNestedScroll");
    }
}
