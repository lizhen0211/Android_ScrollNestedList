package com.lz.example.android_scrollnestedlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lz on 2017/4/11.
 */

public class NestedExpandListViewAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;

    private List<DataEntry> data;

    public NestedExpandListViewAdapter(Context context, List<DataEntry> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getChildData().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getChildData().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.nested_expand_listview_group_item, parent, false);
        TextView groupText = (TextView) convertView.findViewById(R.id.group_textview);
        DataEntry dataEntry = data.get(groupPosition);
        groupText.setText(dataEntry.getLable());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.nested_expand_listview_child_item, parent, false);
        TextView childText = (TextView) convertView.findViewById(R.id.child_textview);
        childText.setText(data.get(groupPosition).getChildData().get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public static class DataEntry {
        private String lable;
        private List<String> childData;

        public List<String> getChildData() {
            return childData;
        }

        public void setChildData(List<String> childData) {
            this.childData = childData;
        }

        public String getLable() {
            return lable;
        }

        public void setLable(String lable) {
            this.lable = lable;
        }
    }
}
