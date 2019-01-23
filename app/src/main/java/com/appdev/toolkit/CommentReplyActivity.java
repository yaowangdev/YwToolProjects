package com.appdev.toolkit;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appdev.common.lib.log.LogUtils;
import com.appdev.common.lib.toast.ToastUtils;
import com.appdev.common.lib.ui.DisplayUtils;
import com.appdev.common.lib.ui.KeyboardUtils;
import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

public class CommentReplyActivity extends BaseActivity implements DialogFragmentDataCallback, View.OnClickListener {
    private TitleBar mTitleBar;
    private TextView tvCommentBtn;
    private RelativeLayout rlParent;
    private int keyboardHeight;
    private CommentDialog commentDialog;

    private boolean isKeyboardShow;
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
        rlParent = findViewById(R.id.rl_parent);
        mTitleBar = findViewById(R.id.titleBar);
        tvCommentBtn = findViewById(R.id.tv_comment_fake_button);
        mTitleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
        tvCommentBtn.setOnClickListener(this);
        rlParent.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            ToastUtils.shortToast(CommentReplyActivity.this,"haha");
            Rect r = new Rect();
            rlParent.getWindowVisibleDisplayFrame(r);
            LogUtils.d("yaowang","rTop="+r.top);
            LogUtils.d("yaowang","rBottom="+r.bottom);
            LogUtils.d("yaowang","statusHeight="+ DisplayUtils.getStatusHeight(CommentReplyActivity.this));
            LogUtils.d("yaowang","screenHeight="+ DisplayUtils.getDisplayHeight(CommentReplyActivity.this));
            int screenHeight = DisplayUtils.getDisplayHeight(CommentReplyActivity.this);
            int heightDiff = screenHeight - r.bottom;
            if(heightDiff>100){
                //则很能是弹出键盘
                keyboardHeight = heightDiff;
            }
        });

        KeyboardUtils.setListener(this, new KeyboardUtils.OnKeyboardListener() {
            @Override
            public void onKeyboardShow(int i) {
                isKeyboardShow = true;
            }

            @Override
            public void onKeyboardHide(int i) {
                isKeyboardShow = false;
            }
        });
    }

    @Override
    public String getCommentText() {
        return tvCommentBtn.getText().toString();
    }

    @Override
    public void setCommentText(String commentTextTemp) {
        tvCommentBtn.setText(commentTextTemp);
    }

    @Override
    public void onImageClick() {
        if(!isKeyboardShow){
            KeyboardUtils.showSoftInputUsingToggle(this);
            commentDialog.removeImageView();
        }else {
            commentDialog.setKeyboardHeight(keyboardHeight);
            KeyboardUtils.hideSoftInputUsingToggle(this);
        }
    }

    @Override
    public void onAtClick() {
        ToastUtils.shortToast(this,"点击@入口");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_comment_fake_button:
                commentDialog = CommentDialog.newCommentBuilder()
                        .openCustom(true)
                        .setGravity(Gravity.BOTTOM)
                        .setDimAmount(0.5f)
                        .build();
                commentDialog.show(getSupportFragmentManager(),"dialogComment");
                break;
        }

    }
}
