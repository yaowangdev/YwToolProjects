package com.appdev.toolkit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.dialog.ConfirmDialog;
import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.MainActivity;
import com.appdev.toolkit.R;

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
        mTitleBar = findViewById(R.id.titleBar);
        mTitleBar.setTitleText(mIntent.getStringExtra(MainActivity.MAIN_EXTRA_TITLE));
    }

    public void Confirm(View v){
        ConfirmDialog.newConfirmBuilder()
                .openCustom(true)
                .setLeftText("取消")
                .setRightText("确定")
                .setTitle("提示窗口")
                .setMessage("消息内容")
                .setGravity(Gravity.CENTER)
                .setDimAmount(0.3f)
                .build()
                .show(getSupportFragmentManager(),"dialog1");
    }
}
