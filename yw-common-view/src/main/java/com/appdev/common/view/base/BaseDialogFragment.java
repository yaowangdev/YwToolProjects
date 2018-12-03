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

    private Dialog mDialog;

    protected static Bundle getArgumentBundle(Builder b) {
        Bundle bundle = new Bundle();
        bundle.putInt("mWidth", b.mWidth);
        bundle.putInt("mHeight", b.mHeight);
        bundle.putInt("mGravity", b.mGravity);
        bundle.putInt("mOffsetX", b.mOffsetX);
        bundle.putInt("mOffsetY", b.mOffsetY);
        bundle.putInt("mAnimation", b.mAnimation);
        bundle.putInt("mTheme", b.mTheme);
        bundle.putFloat("mRatio", b.mRatio);
        bundle.putFloat("mDimAmount", b.mDimAmount);
        bundle.putInt("mBackgroundDrawable", b.mBackgroundDrawable);
        bundle.putBoolean("isTouchOutsideCancel",b.isTouchOutsideCancel);
        bundle.putBoolean("isCancelable",b.isCancelable);
        bundle.putBoolean("openCustom",b.openCustom);
        return bundle;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWidth = getArguments().getInt("mWidth",WindowManager.LayoutParams.MATCH_PARENT);
            mHeight = getArguments().getInt("mHeight",WindowManager.LayoutParams.WRAP_CONTENT);
            mOffsetX = getArguments().getInt("mOffsetX",0);
            mOffsetY = getArguments().getInt("mOffsetY",0);
            mAnimation = getArguments().getInt("mAnimation",R.style.DialogBaseAnimation);
            mGravity = getArguments().getInt("mGravity",Gravity.BOTTOM);
            mTheme = getArguments().getInt("mTheme",R.style.CustomDialog);
            mRatio = getArguments().getFloat("mRatio",0);
            mDimAmount = getArguments().getFloat("mDimAmount",0);
            mBackgroundDrawable = getArguments().getInt("mBackgroundDrawable", R.color.transparent);
            isTouchOutsideCancel = getArguments().getBoolean("isTouchOutsideCancel", true);
            isCancelable = getArguments().getBoolean("isCancelable", true);
            openCustom = getArguments().getBoolean("openCustom", false);
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
