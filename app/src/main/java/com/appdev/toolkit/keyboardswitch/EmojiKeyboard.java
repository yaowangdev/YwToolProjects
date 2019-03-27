package com.appdev.toolkit.keyboardswitch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.appdev.common.lib.log.LogUtils;
import com.appdev.common.lib.ui.DisplayUtils;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.keyboardswitch
 * @createTime 创建时间 ：2019/3/26
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class EmojiKeyboard {
    private static final String EMOJI_KEYBOARD = "EmojiKeyboard";
    private static final String KEY_SOFT_KEYBOARD_HEIGHT = "SoftKeyboardHeight";
    private static final int SOFT_KEYBOARD_HEIGHT_DEFAULT = 654;

    private Activity activity;

    //文本输入框
    private EditText editText;

    //表情面板
    private View emojiPanelView;

    //内容View,即除了表情布局和输入框布局以外的布局
    //用于固定输入框一行的高度以防止跳闪
    private View contentView;

    private View switchView;

    private InputMethodManager inputMethodManager;

    private SharedPreferences sharedPreferences;

    @SuppressLint("ClickableViewAccessibility")
    public EmojiKeyboard(Activity activity, EditText editText, View switchView, View emojiPanelView, View contentView) {
        this.activity = activity;
        this.editText = editText;
        this.emojiPanelView = emojiPanelView;
        this.contentView = contentView;
        this.switchView = switchView;
        this.inputMethodManager = (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.sharedPreferences = this.activity.getSharedPreferences(EMOJI_KEYBOARD, Context.MODE_PRIVATE);
        this.activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }


    @SuppressLint("ClickableViewAccessibility")
    public void initListener(){
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP && emojiPanelView.isShown()){
                    lockContentHeight();
                    hideEmojiPanel(true);
                    unLockContentHeight();
                }
                return false;
            }
        });
        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emojiPanelView.isShown()) {
                    lockContentHeight();
                    hideEmojiPanel(true);
                    unLockContentHeight();
                } else {
                    if (isSoftInputShown()) {
                        lockContentHeight();
                        showEmojiPanel();
                        unLockContentHeight();
                    } else {
                        showEmojiPanel();
                    }
                }

            }
        });
        init();
    }

    private void init() {
        if (!sharedPreferences.contains(KEY_SOFT_KEYBOARD_HEIGHT)) {
            editText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showSoftKeyboard(true);
                }
            }, 200);
        }
    }


    private void lockContentHeight() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) contentView.getLayoutParams();
        layoutParams.height = contentView.getHeight();
        layoutParams.weight = 0;
    }

    private void hideEmojiPanel(boolean showSoftInput) {
        if (emojiPanelView.isShown()) {
            emojiPanelView.setVisibility(View.GONE);
            if (showSoftInput) {
                showSoftKeyboard(false);
            }
        }
    }

    private void showSoftKeyboard(boolean save) {
        editText.requestFocus();
        inputMethodManager.showSoftInput(editText, 0);
        if (save) {
            editText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSoftKeyboardHeight();
                }
            }, 200);
        }
    }

    private void unLockContentHeight() {
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((LinearLayout.LayoutParams) contentView.getLayoutParams()).weight = 1;
            }
        }, 200);
    }

    /**
     * 是否显示软件盘
     * @return
     */
    private boolean isSoftInputShown() {
        return getSoftKeyboardHeight() != 0;
    }

    private int getSoftKeyboardHeight() {
        Rect r = new Rect();
        /**
         * decorView是window中的最顶层view，可以从window中通过getDecorView获取到decorView。
         * 通过decorView获取到程序显示的区域，包括标题栏，但不包括状态栏。
         */
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        //获取屏幕的高度
        int screenHeight = DisplayUtils.getDisplayHeight(activity);
        //计算软件盘的高度
        int softInputHeight = screenHeight - r.bottom;

        /**
         * 某些Android版本下，没有显示软键盘时减出来的高度总是144，而不是零，
         * 这是因为高度是包括了虚拟按键栏的(例如华为系列)，所以在API Level高于20时，
         * 我们需要减去底部虚拟按键栏的高度（如果有的话）
         */
        if (Build.VERSION.SDK_INT >= 20) {
            softInputHeight = softInputHeight - DisplayUtils.getSoftButtonsBarHeight(activity);
        }

        if (softInputHeight < 0) {
            LogUtils.w("EmotionKeyboard--Warning: value of softInputHeight is below zero!");
        }
        //存一份到本地
        if (softInputHeight > 0) {
            sharedPreferences.edit().putInt(KEY_SOFT_KEYBOARD_HEIGHT, softInputHeight).apply();
        }
        return softInputHeight;

    }

    /**
     * 获取本地存储的键盘高度值或者是返回默认值
     */
    private int getSoftKeyboardHeightLocalValue() {
        return sharedPreferences.getInt(KEY_SOFT_KEYBOARD_HEIGHT, SOFT_KEYBOARD_HEIGHT_DEFAULT);
    }

    private void showEmojiPanel() {
        int softKeyboardHeight = getSoftKeyboardHeight();
        if (softKeyboardHeight == 0) {
            softKeyboardHeight = getSoftKeyboardHeightLocalValue();
        } else {
            hideSoftKeyboard();
        }
        emojiPanelView.getLayoutParams().height = softKeyboardHeight;
        emojiPanelView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏键盘
     */
    private void hideSoftKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    /**
     * 当点击返回键时需要先隐藏表情面板
     */
    public boolean interceptBackPress() {
        if (emojiPanelView.isShown()) {
            hideEmojiPanel(false);
            return true;
        }
        return false;
    }


}
