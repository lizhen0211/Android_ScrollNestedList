package com.lz.example.android_scrollnestedlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lz on 2018/6/1.
 */
public class ListviewAdapter extends BaseAdapter {

    private List<String> datas;

    public ListviewAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        TextView textView = (TextView) convertView.findViewById(R.id.text_view);
        textView.setText(datas.get(position));
        return convertView;
    }
}
