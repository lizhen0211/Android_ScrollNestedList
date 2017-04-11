package com.lz.example.android_scrollnestedlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NestedExpandListViewActivity extends Activity {

    private ExpandableListView expandableListView;

    private NestedExpandListViewAdapter expandListViewAdapter;

    private TextView titleView;

    private View titleDividerView;

    private int listItemHeight;

    private int lastOpenGroupPostion = -1;

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_expand_list_view);
        expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        scrollView = (ScrollView) findViewById(R.id.scrollview);
        titleView = (TextView) findViewById(R.id.title_view);
        titleDividerView = findViewById(R.id.title_divider);
        expandableListView.setGroupIndicator(null);
        expandableListView.setChildIndicator(null);
        expandableListView.setScrollingCacheEnabled(false);
        expandableListView.setDrawingCacheEnabled(false);

        expandableListView.setOnGroupExpandListener(onGroupExpandListener);
        expandableListView.setOnGroupCollapseListener(onGroupCollapseListener);
        expandableListView.setOnGroupClickListener(onGroupClickListener);

        expandListViewAdapter = new NestedExpandListViewAdapter(this, getData());
        expandableListView.setAdapter(expandListViewAdapter);
        listItemHeight = (int) getResources().getDimension(R.dimen.expand_list_item_height);
        ListUtil.setListViewHeight(expandListViewAdapter.getGroupCount()
                , (int) getResources().getDimension(R.dimen.expand_list_item_height), expandableListView);
    }

    private ExpandableListView.OnGroupExpandListener onGroupExpandListener = new ExpandableListView.OnGroupExpandListener() {
        @Override
        public void onGroupExpand(int groupPosition) {
            lastOpenGroupPostion = groupPosition;
            //展开时，listview 高度重新计算后，高度错误。
            //需要再次设置正确的listview高度。
            ListUtil.setListViewHeight(expandListViewAdapter.getGroupCount(), listItemHeight, expandableListView);
        }
    };

    private ExpandableListView.OnGroupCollapseListener onGroupCollapseListener = new ExpandableListView.OnGroupCollapseListener() {
        @Override
        public void onGroupCollapse(int groupPosition) {
            lastOpenGroupPostion = -1;
            ListUtil.setListViewHeight(expandListViewAdapter.getGroupCount(), listItemHeight, expandableListView);
        }
    };

    private ExpandableListView.OnGroupClickListener onGroupClickListener = new ExpandableListView.OnGroupClickListener() {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            //手动展开与关闭groupItem
            ListUtil.disposeGroupStatus(groupPosition, lastOpenGroupPostion, expandableListView);

           /* int dividerHeight = expandableListView.getDividerHeight();
            int height = (dividerHeight + listItemHeight) * groupPosition;
            int scrollTo = height + titleView.getHeight() + titleDividerView.getHeight();
            //设置滚到位置
            ListUtil.scrollToTop(scrollView, expandableListView, scrollTo);*/

            // 返回true 展开与关闭要自己处理；返回false展开关闭由系统处理
            return true;
        }
    };

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
