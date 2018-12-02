package com.appdev.toolkit;

import android.os.Bundle;
import android.view.View;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.dialog.ConfirmDialog;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    public void showDialog(View view) {
        ConfirmDialog.newConfirmBuilder()
                .setLeftText("取消")
                .setRightText("确定")
                .setTitle("消息标题")
                .setMessage("消息内容")
                .openCustom(true)
                .setDimAmount(0.4f)
                .setRatio(0.8f)
                .setmBackgroundDrawable(R.drawable.agmobile_bg_popup_windowed)
//                .setAnimation(R.style.DialogBaseAnimation)
                .build()
                .show(getSupportFragmentManager(),"confirmDialog");
    }
}
