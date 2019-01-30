package com.appdev.toolkit;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.toolkit.bottomsheet.BottomSheetDemoActivity;
import com.appdev.toolkit.fragmentdialog.DialogDemoActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static final String MAIN_EXTRA_TITLE = "extra_title";
    private List<MenuItem> mItems = new ArrayList<>();

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        RecyclerView mRecyclerView = findViewById(R.id.rv_recyclerView);
        mItems = getData();
        MenuAdapter mMenuAdapter = new MenuAdapter(R.layout.item_menu_view, mItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMenuAdapter);
        mMenuAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(MainActivity.this,((MenuAdapter)adapter).getData().get(position).getActivity());
            intent.putExtra(MAIN_EXTRA_TITLE,((MenuAdapter)adapter).getData().get(position).getTitle());
            startActivity(intent);
        });
    }

    private List<MenuItem> getData() {
        mItems.add(new MenuItem(DialogDemoActivity.class,"DialogFragment案例"));
        mItems.add(new MenuItem(BottomSheetDemoActivity.class,"BottomSheet案例"));
        return mItems;
    }
}
