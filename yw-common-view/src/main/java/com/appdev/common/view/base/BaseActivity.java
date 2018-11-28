package com.appdev.common.view.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.appdev.common.lib.ui.DisplayUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.common.view.base
 * @createTime 创建时间 ：2018/11/21
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class BaseActivity extends AppCompatActivity {
    public static final String STATUS_BAR_COLOR = "status_bar_color";
    protected SystemBarTintManager tintManager;
    protected Intent mIntent;
    protected int statusBarColor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = getIntent();
        statusBarColor = mIntent.getIntExtra(STATUS_BAR_COLOR,Color.TRANSPARENT);
        tintManager = new SystemBarTintManager(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DisplayUtils.setStatusBarTransparentLollipop(this,statusBarColor);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            DisplayUtils.setStatusBarTransparent(this);
        }
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(statusBarColor);//通知栏所需颜色
    }
}