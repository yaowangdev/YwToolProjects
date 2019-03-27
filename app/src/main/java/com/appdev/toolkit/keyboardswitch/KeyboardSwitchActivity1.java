package com.appdev.toolkit.keyboardswitch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appdev.common.lib.ui.AndroidBug5497Workaround;
import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.R;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.keyboardswitch
 * @createTime 创建时间 ：2019/3/26
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class KeyboardSwitchActivity1 extends BaseActivity {
    private TitleBar mTitleBar;
    private ImageView iv_more;
    private EditText et_inputMessage;
    private LinearLayout ll_rootEmojiPanel;
    private EmojiKeyboard emojiKeyboard;
    private View v_content_view;


    @Override
    protected int getLayoutView() {
        return R.layout.activity1_keyboard_switch;
    }

    @Override
    protected void initView() {
        AndroidBug5497Workaround.assistActivity(this);
        mTitleBar = findViewById(R.id.titleBar);
        mTitleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
        et_inputMessage = findViewById(R.id.et_inputMessage);
        iv_more = findViewById(R.id.iv_more);
        ll_rootEmojiPanel = findViewById(R.id.ll_rootEmojiPanel);
        v_content_view = findViewById(R.id.v_content_view);
        emojiKeyboard = new EmojiKeyboard(this,et_inputMessage,iv_more,ll_rootEmojiPanel,v_content_view);
        emojiKeyboard.initListener();
    }

    @Override
    public void onBackPressed() {
        if (!emojiKeyboard.interceptBackPress()) {
            super.onBackPressed();
        }
    }
}
