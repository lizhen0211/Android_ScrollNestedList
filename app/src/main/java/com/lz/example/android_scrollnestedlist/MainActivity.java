package com.lz.example.android_scrollnestedlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNestedListView(View view) {
        Intent intent = new Intent(MainActivity.this, NestedListViewActivity.class);
        startActivity(intent);
    }

    public void onNestedExpandListView(View view) {
        Intent intent = new Intent(MainActivity.this, NestedExpandListViewActivity.class);
        startActivity(intent);
    }

    public void onInSideScrollview(View view) {
        Intent intent = new Intent(MainActivity.this, NestedScrollInsideScroll.class);
        startActivity(intent);
    }

    public void onListitemNestNestedScroll(View view) {
        Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
        startActivity(intent);
    }

    public void onNestedScrollViewAndRecycleview(View view) {
        Intent intent = new Intent(MainActivity.this, NestedScrollViewAndRecycleview.class);
        startActivity(intent);
    }

    public void onSimpleNestedScrollParent(View view) {
        Intent intent = new Intent(MainActivity.this, SimpleNestedScrollParentActivity.class);
        startActivity(intent);
    }

    public void onHideHeader(View view) {
        Intent intent = new Intent(MainActivity.this, HideHeaderActivity.class);
        startActivity(intent);
    }
}
