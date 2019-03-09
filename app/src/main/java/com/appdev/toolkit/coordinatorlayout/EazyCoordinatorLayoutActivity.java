package com.appdev.toolkit.coordinatorlayout;

import android.annotation.SuppressLint;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.R;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

public class EazyCoordinatorLayoutActivity extends BaseActivity {
    private TempView tempView;
    private TitleBar titleBar;
    private int lastX;
    private int lastY;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_eazy_coordinator_layout;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        tempView = findViewById(R.id.tv_dependency);
        titleBar = findViewById(R.id.titleBar);
        titleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
        tempView.setOnTouchListener((v, event) -> {
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            switch (event.getAction()){
                case MotionEvent.ACTION_MOVE:
                    CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) tempView.getLayoutParams();
                    Log.d("yaowang","leftMargin:"+layoutParams.leftMargin+";x:"+x+";lastX:"+lastX);
                    Log.d("yaowang","topMargin:"+layoutParams.topMargin+";y:"+y+";lastY:"+lastY);
                    int left = layoutParams.leftMargin + x - lastX;
                    int top = layoutParams.topMargin + y - lastY;
                    layoutParams.leftMargin = left;
                    layoutParams.topMargin = top;
                    tempView.setLayoutParams(layoutParams);
                    break;
            }
            lastX = x;
            lastY = y;
            return true;
        });

    }
}
