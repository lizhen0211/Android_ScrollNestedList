package com.lz.example.android_scrollnestedlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NestedListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_list_view);
        ListView listOne = (ListView) findViewById(R.id.listOne);
        listOne.setScrollingCacheEnabled(false);
        listOne.setDrawingCacheEnabled(false);
        NestedListViewAdapter listOneAdapter = new NestedListViewAdapter(this, getData());
        listOne.setAdapter(listOneAdapter);
        ListUtil.setListViewHeight(listOneAdapter.getDatas().size()
                , (int) getResources().getDimension(R.dimen.list_item_height), listOne);

        ListView listTwo = (ListView) findViewById(R.id.listTwo);
        listTwo.setScrollingCacheEnabled(false);
        listTwo.setDrawingCacheEnabled(false);
        NestedListViewAdapter listTwoAdapter = new NestedListViewAdapter(this, getData());
        listTwo.setAdapter(listTwoAdapter);
        ListUtil.setListViewHeight(listTwoAdapter.getDatas().size()
                , (int) getResources().getDimension(R.dimen.list_item_height), listTwo);
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
