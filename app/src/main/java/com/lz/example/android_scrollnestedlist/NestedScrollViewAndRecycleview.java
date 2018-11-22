package com.lz.example.android_scrollnestedlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class NestedScrollViewAndRecycleview extends Activity {

    private LinkedList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrollview_and_recycleview);
        RecyclerView recyclerViewFirst = (RecyclerView) findViewById(R.id.first_nested_recycleview);
        RecyclerView recyclerViewSecond = (RecyclerView) findViewById(R.id.second_nested_recycleview);
        RecyclerView recyclerViewThird = (RecyclerView) findViewById(R.id.third_nested_recycleview);

        setDummyData();

        recyclerViewFirst.setLayoutManager(new LinearLayoutManager(this));
        ListRecyclerViewAdapter adapterFirst = new ListRecyclerViewAdapter(strings);
        recyclerViewFirst.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewFirst.setNestedScrollingEnabled(false);
        recyclerViewFirst.setAdapter(adapterFirst);

        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(this));
        ListRecyclerViewAdapter adapterSecond = new ListRecyclerViewAdapter(strings);
        recyclerViewSecond.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewSecond.setNestedScrollingEnabled(false);
        recyclerViewSecond.setAdapter(adapterSecond);

        recyclerViewThird.setLayoutManager(new LinearLayoutManager(this));
        ListRecyclerViewAdapter adapterThird = new ListRecyclerViewAdapter(strings);
        recyclerViewThird.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewThird.setNestedScrollingEnabled(false);
        recyclerViewThird.setAdapter(adapterThird);

        /*NestedScrollView nestedScrollView = findViewById(R.id.first_nested_scrollview);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
                int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
                if (diff == 0 && onScrolldListener != null) {
                    onScrolldListener.onBottomReached();
                } else if (nestedScrollView.getScrollY() == 0 && onScrolldListener != null) {
                    onScrolldListener.onTopReached();
                }
            }
        });*/
    }

    private void setDummyData() {
        strings = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            strings.add(String.valueOf(i));
        }
    }

    OnScrolldListener onScrolldListener = new OnScrolldListener() {
        @Override
        public void onTopReached() {
            Log.e("Nested","onTopReached");
        }

        @Override
        public void onBottomReached() {
            Log.e("Nested","onBottomReached");
        }
    };

    public interface OnScrolldListener {

        void onTopReached();

        void onBottomReached();
    }
}
