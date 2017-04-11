package com.lz.example.android_scrollnestedlist;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

/**
 * Created by lz on 2017/4/11.
 */

public class ListUtil {

    public static void setListViewHeight(int size, int itemHeight, ListView listView) {

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = size * itemHeight
                + (size - 1) * listView.getDividerHeight();
    }

    /**
     * 处理一级菜单 展开或关闭
     *
     * @param groupPosition        当前点击的索引
     * @param lastOpenGroupPostion 上次点击的索引
     * @param list
     */
    public static void disposeGroupStatus(int groupPosition, int lastOpenGroupPostion, ExpandableListView list) {
        if (lastOpenGroupPostion == -1) {
            // 展开被选的group
            list.expandGroup(groupPosition);
            // 设置被选中的group置于顶端
            list.setSelectedGroup(groupPosition);
        } else if (lastOpenGroupPostion == groupPosition) {
            if (list.isGroupExpanded(groupPosition)) {
                list.collapseGroup(groupPosition);
            } else {
                list.expandGroup(groupPosition);
                list.setSelectedGroup(groupPosition);
            }
        } else {
            list.collapseGroup(lastOpenGroupPostion);
            // 展开被选的group
            list.expandGroup(groupPosition);
            // 设置被选中的group置于顶端
            list.setSelectedGroup(groupPosition);
            lastOpenGroupPostion = groupPosition;
        }
    }

    public static void scrollToTop(final View scroll, final View inner, final int offSet) {
        Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null == scroll || null == inner) {
                    return;
                }
                scroll.scrollTo(0, offSet);
            }
        });
    }
}
