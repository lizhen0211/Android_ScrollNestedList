package com.lz.example.android_scrollnestedlist;

import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by lz on 2017/4/11.
 */

public class ListUtil {

    public static void setListViewHeight(int size, int itemHeight, ListView listView) {

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = size
                * itemHeight
                + (size - 1)
                * listView.getDividerHeight();
    }
}
