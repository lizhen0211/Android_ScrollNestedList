package com.lz.example.android_scrollnestedlist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class NestedExpandListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_expand_list_view);
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        expandableListView.setGroupIndicator(null);
        expandableListView.setChildIndicator(null);
        expandableListView.setScrollingCacheEnabled(false);
        expandableListView.setDrawingCacheEnabled(false);

        NestedExpandListViewAdapter expandListViewAdapter = new NestedExpandListViewAdapter(this, getData());
        expandableListView.setAdapter(expandListViewAdapter);
        ListUtil.setListViewHeight(expandListViewAdapter.getGroupCount()
                , (int) getResources().getDimension(R.dimen.expand_list_item_height), expandableListView);
    }

    private List<NestedExpandListViewAdapter.DataEntry> getData() {
        List<NestedExpandListViewAdapter.DataEntry> dataEntries = new ArrayList<>();
        NestedExpandListViewAdapter.DataEntry dataEntry1 = new NestedExpandListViewAdapter.DataEntry();
        List<String> childDatas1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            childDatas1.add(String.valueOf(i));
        }
        dataEntry1.setLable("one");
        dataEntry1.setChildData(childDatas1);

        NestedExpandListViewAdapter.DataEntry dataEntry2 = new NestedExpandListViewAdapter.DataEntry();
        List<String> childDatas2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            childDatas2.add(String.valueOf(i));
        }
        dataEntry2.setLable("two");
        dataEntry2.setChildData(childDatas2);

        NestedExpandListViewAdapter.DataEntry dataEntry3 = new NestedExpandListViewAdapter.DataEntry();
        List<String> childDatas3 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            childDatas3.add(String.valueOf(i));
        }
        dataEntry3.setLable("three");
        dataEntry3.setChildData(childDatas3);

        NestedExpandListViewAdapter.DataEntry dataEntry4 = new NestedExpandListViewAdapter.DataEntry();
        List<String> childDatas4 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            childDatas4.add(String.valueOf(i));
        }
        dataEntry4.setLable("four");
        dataEntry4.setChildData(childDatas4);

        NestedExpandListViewAdapter.DataEntry dataEntry5 = new NestedExpandListViewAdapter.DataEntry();
        List<String> childDatas5 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            childDatas5.add(String.valueOf(i));
        }
        dataEntry5.setLable("five");
        dataEntry5.setChildData(childDatas5);

        dataEntries.add(dataEntry1);
        dataEntries.add(dataEntry2);
        dataEntries.add(dataEntry3);
        dataEntries.add(dataEntry4);
        dataEntries.add(dataEntry5);
        return dataEntries;
    }
}
