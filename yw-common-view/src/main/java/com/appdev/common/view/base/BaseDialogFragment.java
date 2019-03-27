package com.appdev.common.view.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.appdev.common.lib.ui.DisplayUtils;
import com.appdev.common.view.R;

public abstract class BaseDialogFragment extends DialogFragment {
    private static final String DIALOG_WIDTH = "dialog_width";
    private static final String DIALOG_HEIGHT = "dialog_height";
    private static final String DIALOG_GRAVITY = "dialog_gravity";
    private static final String DIALOG_OFFSET_X = "dialog_offset_x";
    private static final String DIALOG_OFFSET_Y = "dialog_offset_y";
    private static final String DIALOG_ANIMATION = "dialog_animation";
    private static final String DIALOG_THEME = "dialog_theme";
    private static final String DIALOG_RATIO = "dialog_ratio";
    private static final String DIALOG_DIM_AMOUNT = "dialog_dim_amount";
    private static final String DIALOG_BACKGROUND_DRAWABLE = "dialog_background_drawable";
    private static final String DIALOG_TOUCH_OUTSIDE_CANCEL = "dialog_touch_outside_cancel";
    private static final String DIALOG_CANCELABLE = "dialog_cancel";
    private static final String DIALOG_OPEN_CUSTOM = "dialog_open_custom";

    private int mWidth = WindowManager.LayoutParams.MATCH_PARENT;
    private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;
    private int mGravity = Gravity.CENTER;
    private int mOffsetX = 0;
    private int mOffsetY = 0;
    private int mAnimation = 0;
    private int mTheme = 0;
    private float mRatio = 0;
    private float mDimAmount = 0;
    private int mBackgroundDrawable = R.color.transparent;
    private boolean isTouchOutsideCancel = true;
    private boolean isCancelable = true;
    private boolean openCustom = false;

    public Dialog mDialog;

    protected static Bundle getArgumentBundle(Builder b) {
        Bundle bundle = new Bundle();
        bundle.putInt(DIALOG_WIDTH, b.mWidth);
        bundle.putInt(DIALOG_HEIGHT, b.mHeight);
        bundle.putInt(DIALOG_GRAVITY, b.mGravity);
        bundle.putInt(DIALOG_OFFSET_X, b.mOffsetX);
        bundle.putInt(DIALOG_OFFSET_Y, b.mOffsetY);
        bundle.putInt(DIALOG_ANIMATION, b.mAnimation);
        bundle.putInt(DIALOG_THEME, b.mTheme);
        bundle.putFloat(DIALOG_RATIO, b.mRatio);
        bundle.putFloat(DIALOG_DIM_AMOUNT, b.mDimAmount);
        bundle.putInt(DIALOG_BACKGROUND_DRAWABLE, b.mBackgroundDrawable);
        bundle.putBoolean(DIALOG_TOUCH_OUTSIDE_CANCEL,b.isTouchOutsideCancel);
        bundle.putBoolean(DIALOG_CANCELABLE,b.isCancelable);
        bundle.putBoolean(DIALOG_OPEN_CUSTOM,b.openCustom);
        return bundle;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWidth = getArguments().getInt(DIALOG_WIDTH,WindowManager.LayoutParams.MATCH_PARENT);
            mHeight = getArguments().getInt(DIALOG_HEIGHT,WindowManager.LayoutParams.WRAP_CONTENT);
            mGravity = getArguments().getInt(DIALOG_GRAVITY,Gravity.BOTTOM);
            mOffsetX = getArguments().getInt(DIALOG_OFFSET_X,0);
            mOffsetY = getArguments().getInt(DIALOG_OFFSET_Y,0);
            mAnimation = getArguments().getInt(DIALOG_ANIMATION,R.style.DialogBaseAnimation);
            mTheme = getArguments().getInt(DIALOG_THEME,R.style.CustomDialog);
            mRatio = getArguments().getFloat(DIALOG_RATIO,0);
            mDimAmount = getArguments().getFloat(DIALOG_DIM_AMOUNT,0);
            mBackgroundDrawable = getArguments().getInt(DIALOG_BACKGROUND_DRAWABLE, R.color.transparent);
            isTouchOutsideCancel = getArguments().getBoolean(DIALOG_TOUCH_OUTSIDE_CANCEL, true);
            isCancelable = getArguments().getBoolean(DIALOG_CANCELABLE, true);
            openCustom = getArguments().getBoolean(DIALOG_OPEN_CUSTOM, false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setDialogView(inflater, container, savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        setCommonStyle();
        setStyle(STYLE_NO_TITLE,mTheme);
    }

    private void setCommonStyle() {
        mDialog = getDialog();
        Window window = mDialog.getWindow();
        mDialog.setCanceledOnTouchOutside(isTouchOutsideCancel);
        mDialog.setCancelable(isCancelable);
        if (window != null) {
            WindowManager.LayoutParams wlp = window.getAttributes();
            if(!openCustom){
                wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
                wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                wlp.dimAmount = 0.4f;
            }else {
                // 透明背景
                window.setBackgroundDrawableResource(mBackgroundDrawable);
                //设置宽高
                window.getDecorView().setPadding(0, 0, 0, 0);
                DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                wlp.width = mRatio==0 ? mWidth: (int) (mRatio * dm.widthPixels);
                wlp.height = mHeight;
                //设置对齐方式
                wlp.gravity = mGravity;
                //设置偏移量
                wlp.x = DisplayUtils.dip2px(mDialog.getContext(), mOffsetX);
                wlp.y = DisplayUtils.dip2px(mDialog.getContext(), mOffsetY);

                wlp.dimAmount = mDimAmount;
                //设置动画
                window.setWindowAnimations(mAnimation);
            }
            window.setAttributes(wlp);
        }
    }

    protected abstract View setDialogView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    public static abstract class Builder<T extends Builder, D extends BaseDialogFragment> {
        private int mWidth = WindowManager.LayoutParams.MATCH_PARENT;
        private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        private int mGravity = Gravity.CENTER;
        private int mOffsetX = 0;
        private int mOffsetY = 0;
        private int mAnimation = 0;
        private int mTheme = 0;
        private float mRatio = 0;
        private float mDimAmount = 0;
        private int mBackgroundDrawable = R.color.transparent;
        private boolean isTouchOutsideCancel = true;
        private boolean isCancelable = true;
        private boolean openCustom = false;

        public T setSize(int mWidth, int mHeight) {
            this.mWidth = mWidth;
            this.mHeight = mHeight;
            return (T) this;
        }

        public T setGravity(int mGravity) {
            this.mGravity = mGravity;
            return (T) this;
        }

        public T setOffsetX(int mOffsetX) {
            this.mOffsetX = mOffsetX;
            return (T) this;
        }

        public T setOffsetY(int mOffsetY) {
            this.mOffsetY = mOffsetY;
            return (T) this;
        }

        public T setAnimation(int mAnimation) {
            this.mAnimation = mAnimation;
            return (T) this;
        }

        public T setTheme(int mTheme){
            this.mTheme = mTheme;
            return (T) this;
        }

        public T setRatio(float mRatio){
            this.mRatio = mRatio;
            return (T) this;
        }

        public T setDimAmount(float mDimAmount){
            this.mDimAmount = mDimAmount;
            return (T) this;
        }

        public T setBackgroundDrawable(int mBackgroundDrawable){
            this.mBackgroundDrawable = mBackgroundDrawable;
            return (T) this;
        }

        public T setTouchOutsideCancel(boolean isTouchOutsideCancel){
            this.isTouchOutsideCancel = isTouchOutsideCancel;
            return (T) this;
        }

        public T isCancelable(boolean isCancelable){
            this.isCancelable = isCancelable;
            return (T) this;
        }

        public T openCustom(boolean openCustom){
            this.openCustom = openCustom;
            return (T) this;
        }

        protected abstract D build();

        protected void clear() {
            this.mWidth = WindowManager.LayoutParams.MATCH_PARENT;
            this.mHeight = WindowManager.LayoutParams.WRAP_CONTENT;
            this.mGravity = Gravity.CENTER;
            this.mOffsetX = 0;
            this.mOffsetY = 0;
            this.mAnimation = 0;
            this.mTheme = 0;
            this.mRatio = 0;
            this.mDimAmount = 0;
            this.mBackgroundDrawable = R.color.transparent;
            this.isTouchOutsideCancel = true;
            this.isCancelable = true;
            this.openCustom = false;
        }
    }



    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
