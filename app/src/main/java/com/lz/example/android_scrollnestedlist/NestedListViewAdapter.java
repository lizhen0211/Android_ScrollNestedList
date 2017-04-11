package com.lz.example.android_scrollnestedlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lz on 2017/4/11.
 */

public class NestedListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    private List<String> datas;

    public NestedListViewAdapter(Context context, List<String> datas) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }

    public List<String> getDatas() {
        return datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.nested_list_view_item, viewGroup, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(datas.get(i));
        return view;
    }
}
