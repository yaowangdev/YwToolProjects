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
public class DialogDemoActivity extends BaseActivity {
    private TitleBar mTitleBar;
    private RecyclerView mRecyclerView;
    private MenuAdapter mMenuAdapter;
    private List<MenuItem> mItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_dialog_demo;
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.rv_recyclerView);
        mItems = getData();
        mTitleBar = findViewById(R.id.titleBar);
        mTitleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
        mMenuAdapter = new MenuAdapter(R.layout.item_menu_view,mItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMenuAdapter);
        mMenuAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(DialogDemoActivity.this,((MenuAdapter)adapter).getData().get(position).getActivity());
            intent.putExtra(MAIN_EXTRA_TITLE,((MenuAdapter)adapter).getData().get(position).getTitle());
            startActivity(intent);
        });
    }

    private List<MenuItem> getData() {
        mItems.add(new MenuItem(CommentReplyActivity.class,"评论回复"));
        return mItems;
    }

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
