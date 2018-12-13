package com.lz.example.android_scrollnestedlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

public class HideHeaderActivity extends Activity {

    private LinkedList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_header);
        RecyclerView recyclerView = findViewById(R.id.recycle_view);

        setDummyData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListRecyclerViewAdapter adapter = new ListRecyclerViewAdapter(strings);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(true);
    }

    private void setDummyData() {
        strings = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            strings.add(String.valueOf(i));
        }
    }
}
