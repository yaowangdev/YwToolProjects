package com.appdev.toolkit.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.R;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

public class CommentReplyActivity extends BaseActivity {
    private TitleBar mTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_comment_reply;
    }

    @Override
    protected void initView() {
        mTitleBar = findViewById(R.id.titleBar);
        mTitleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
    }
}
