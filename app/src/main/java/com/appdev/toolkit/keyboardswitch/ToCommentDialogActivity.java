package com.appdev.toolkit.keyboardswitch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.R;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.keyboardswitch
 * @createTime 创建时间 ：2019/3/27
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class ToCommentDialogActivity extends AppCompatActivity{
    private TitleBar mTitleBar;
    private ImageView iv_more;
    private EditText et_inputMessage;
    private LinearLayout ll_rootEmojiPanel;
    private EmojiKeyboard emojiKeyboard;
    private View v_content_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_comment_dialog);
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
