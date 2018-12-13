package com.lz.example.android_scrollnestedlist;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class HideHeaderNestedParent extends RelativeLayout implements NestedScrollingParent {

    private View headerView;

    private int mHeaderHeight;

    public HideHeaderNestedParent(Context context) {
        super(context);
        init();
    }

    public HideHeaderNestedParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HideHeaderNestedParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerView = findViewById(R.id.header_view);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        boolean headerScrollUp = dy > 0 && getScrollY() < mHeaderHeight;
        boolean headerScrollDown = dy < 0 && getScrollY() > 0 && !target.canScrollVertically(-1);
        if (headerScrollUp || headerScrollDown) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeaderHeight = headerView.getMeasuredHeight();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        } else if (y > mHeaderHeight) {
            y = mHeaderHeight;
        }

        super.scrollTo(x, y);
    }
}
