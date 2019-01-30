package com.appdev.toolkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;

import java.util.ArrayList;
import java.util.List;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.activity
 * @createTime 创建时间 ：2018/12/5
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public abstract class DemoActivity extends BaseActivity {
    protected List<MenuItem> mItems = new ArrayList<>();

    @Override
    protected int getLayoutView() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initView() {
        RecyclerView mRecyclerView = findViewById(R.id.rv_recyclerView);
        mItems = getData();
        TitleBar mTitleBar = findViewById(R.id.titleBar);
        mTitleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
        MenuAdapter mMenuAdapter = new MenuAdapter(R.layout.item_menu_view, mItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMenuAdapter);
        mMenuAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(DemoActivity.this,((MenuAdapter)adapter).getData().get(position).getActivity());
            intent.putExtra(MAIN_EXTRA_TITLE,((MenuAdapter)adapter).getData().get(position).getTitle());
            startActivity(intent);
        });
    }

    protected abstract List<MenuItem> getData();

//    public void Confirm(View v){
//        ConfirmDialog.newConfirmBuilder()
//                .openCustom(true)
//                .setLeftText("取消")
//                .setRightText("确定")
//                .setTitle("提示窗口")
//                .setMessage("消息内容")
//                .setGravity(Gravity.CENTER)
//                .setDimAmount(0.3f)
//                .build()
//                .show(getSupportFragmentManager(),"dialog1");
//    }
}
