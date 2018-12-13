package com.lz.example.android_scrollnestedlist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.support.v4.view.NestedScrollingChild;

public class SimpleNestedScrollChild extends RelativeLayout implements NestedScrollingChild {

    private static final String TAG = "NestedScrollImpl";

    private NestedScrollingChildHelper scrollingChildHelper;

    private int[] consumed = new int[2];

    private int[] offsetInWindow = new int[2];

    private float lastY;

    public SimpleNestedScrollChild(Context context) {
        super(context);
        init();
    }

    public SimpleNestedScrollChild(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleNestedScrollChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        scrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return scrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return scrollingChildHelper.hasNestedScrollingParent();
    }

    /**
     * 开始滚动
     *
     * @param axes 滚动的方向 ViewCompat.SCROLL_AXIS_VERTICAL|ViewCompat.SCROLL_AXIS_HORIZONTAL
     * @return true 支持嵌套滚动,false不支持嵌套滚动
     */
    @Override
    public boolean startNestedScroll(int axes) {
        return scrollingChildHelper.startNestedScroll(axes);
    }

    /**
     * 停止滚动
     */
    @Override
    public void stopNestedScroll() {
        scrollingChildHelper.stopNestedScroll();
    }

    /**
     * 在子view进行滚动之后调用此方法，询问父view是否还要进行余下(unconsumed)的滚动。
     *
     * @param dxConsumed     输入参数 告诉父view,子view已经消费的水平距离
     * @param dyConsumed     输入参数 告诉父view,子已经消费的垂直距离
     * @param dxUnconsumed   输入参数 告诉父view,子view未消费的水平距离
     * @param dyUnconsumed   输入参数 告诉父view,子view未消费的垂直距离
     * @param offsetInWindow 输出参数 用于子view获取父view位置的偏移量
     * @return
     */
    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable @Size(value = 2) int[] offsetInWindow) {
        return scrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    /**
     * 子view进行滚动之前调用此方法,询问父view是否要在子view之前进行滚动
     *
     * @param dx             输入参数 告诉父View此次要滚动的水平距离
     * @param dy             输入参数 告诉父View此次要滚动的垂直距离
     * @param consumed       输出参数 父View消费掉的距离
     * @param offsetInWindow 输出参数 父view位置的偏移量
     * @return 如果父view消费了一部分或全部距离，则此方法返回true。
     */
    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable @Size(value = 2) int[] consumed, @Nullable @Size(value = 2) int[] offsetInWindow) {
        return scrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return scrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return scrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int actionMasked = MotionEventCompat.getActionMasked(event);
        // 取第一个接触屏幕的手指Id
        final int pointerId = MotionEventCompat.getPointerId(event, 0);
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                // 取得当前的Y，并赋值给lastY变量
                lastY = getPointerY(event, pointerId);
                // 找不到手指，放弃掉这个触摸事件流
                if (lastY == -1) {
                    return false;
                }
                // 通知父View，开始滑动
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_MOVE:
                // 获得当前手指的Y
                final float pointerY = getPointerY(event, pointerId);

                // 找不到手指，放弃掉这个触摸事件流
                if (pointerY == -1) {
                    return false;
                }

                // 计算出滑动的偏移量
                float deltaY = pointerY - lastY;

                Log.e(TAG, String.format("before dispatchNestedPreScroll, deltaY = %f", deltaY));
                //子view进行滚动之前调用此方法,询问父view是否要在子view之前进行滚动
                if (dispatchNestedPreScroll(0, (int) deltaY, consumed, offsetInWindow)) {
                    // 偏移量需要减掉被父View消费掉的
                    deltaY = deltaY - consumed[1];
                    Log.e(TAG, String.format("after dispatchNestedPreScroll , deltaY = %f", deltaY));
                }

                /*// 上面的 (int)deltaY 会造成精度丢失，这里把精度给舍弃掉
                if (Math.floor(Math.abs(deltaY)) == 0) {
                    deltaY = 0;
                }*/
                // 这里移动子View，下面的min,max是为了控制边界，避免子View越界
                setY(Math.min(Math.max(getY() + deltaY, 0), ((View) getParent()).getHeight() - getHeight()));

                break;
        }
        return true;
    }

    /**
     * 这个方法通过pointerId获取pointerIndex,然后获取Y
     */
    private float getPointerY(MotionEvent event, int pointerId) {
        final int pointerIndex = MotionEventCompat.findPointerIndex(event, pointerId);
        if (pointerIndex < 0) {
            return -1;
        }
        return MotionEventCompat.getY(event, pointerIndex);
    }
}
