package com.appdev.toolkit.keyboardswitch;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.R;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.keyboardswitch
 * @createTime 创建时间 ：2019/3/27
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class KeyboardSwitchActivity2 extends BaseActivity {
    private TitleBar mTitlebar;
    private Button btnComments;

    @Override
    protected int getLayoutView() {
        return R.layout.activity2_keyoard_switch;
    }

    @Override
    protected void initView() {
        mTitlebar = findViewById(R.id.titleBar);
        mTitlebar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
        btnComments = findViewById(R.id.btn_comments);
    }


    public void toComment(View view){
        Intent intent = new Intent(this,ToCommentDialogActivity.class);
        startActivity(intent);
    }


}
