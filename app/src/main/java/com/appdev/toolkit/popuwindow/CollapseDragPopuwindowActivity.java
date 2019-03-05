package com.appdev.toolkit.popuwindow;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.R;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

public class CollapseDragPopuwindowActivity extends BaseActivity {
    private TitleBar mTitleBar;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_collapse_drag_popuwindow;
    }

    @Override
    protected void initView() {
        mTitleBar = findViewById(R.id.titleBar);
        mTitleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
    }
}
