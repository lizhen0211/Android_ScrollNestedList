package com.lz.example.android_scrollnestedlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    }

    private void setDummyData() {
        strings = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            strings.add(String.valueOf(i));
        }
    }
}
