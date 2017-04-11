package com.lz.example.android_scrollnestedlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class NestedListViewActivity extends Activity {

    private ListView listOne;

    private NestedListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_list_view);
        listOne = (ListView) findViewById(R.id.listOne);
        listOne.setScrollingCacheEnabled(false);
        listOne.setDrawingCacheEnabled(false);
        adapter = new NestedListViewAdapter(this, getData());
        listOne.setAdapter(adapter);
        setListViewHeight();
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private void setListViewHeight() {
        List<String> data = adapter.getDatas();
        ViewGroup.LayoutParams layoutParams = listOne.getLayoutParams();
        layoutParams.height = data.size()
                * (int) getResources().getDimension(R.dimen.list_item_height)
                + (data.size() - 1)
                * listOne.getDividerHeight();
    }

}
