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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.nested_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setDummyData();
        ListRecyclerViewAdapter adapter = new ListRecyclerViewAdapter(strings);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

    private void setDummyData() {
        strings = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            strings.add(String.valueOf(i));
        }
    }
}
