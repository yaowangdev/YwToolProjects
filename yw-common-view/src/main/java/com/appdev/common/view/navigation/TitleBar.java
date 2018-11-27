package com.appdev.common.view.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appdev.common.view.R;

public class TitleBar extends RelativeLayout {
    private LinearLayout llTitleBack;   //返回按钮
    private TextView tvTextBack;        //返回文字
    private TextView tvTitleText;       //标题
    private ImageView ivImgMore;        //更多图标
    private TextView tvTextMore;        //更多文字
    private LinearLayout llBtnMore;     //更多按钮
    private View vBackDivider;          //分隔线

    private String mTitleText;
    private boolean mCanBack;
    private String mBackText;
    private int mMoreImg;
    private String mMoreText;
    private boolean mShowMore;
    private boolean isCenterTitle;
    private int mStyle;


    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = null;
        try {
            ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar, 0, 0);
            mTitleText = ta.getString(R.styleable.TitleBar_titleText);
            mCanBack = ta.getBoolean(R.styleable.TitleBar_canBack, false);
            mBackText = ta.getString(R.styleable.TitleBar_backText);
            mMoreImg = ta.getInt(R.styleable.TitleBar_moreImg, -1);
            mMoreText = ta.getString(R.styleable.TitleBar_moreText);
            mShowMore = ta.getBoolean(R.styleable.TitleBar_showMore, false);
            isCenterTitle = ta.getBoolean(R.styleable.TitleBar_centerTitle, false);
            mStyle = ta.getInt(R.styleable.TitleBar_barStyle, -1);
            if (mStyle == -1) {
                LayoutInflater.from(context).inflate(R.layout.navigation_title_bar_dark, this);
            }
            initView();
            afterInit();
        } finally {
            if (ta != null) {
                ta.recycle();
            }
        }
    }

    private void initView() {
        llTitleBack = findViewById(R.id.title_back);
        tvTextBack = findViewById(R.id.txt_back);
        tvTitleText = findViewById(R.id.title);
        ivImgMore = findViewById(R.id.img_more);
        tvTextMore = findViewById(R.id.txt_more);
        llBtnMore = findViewById(R.id.btn_more);
        findViewById(R.id.view_divider_back);
    }

    private void afterInit() {
        if(mCanBack){
            tvTextBack.setText(mBackText);
            llTitleBack.setOnClickListener(v -> ((Activity)getContext()).finish());
        }else {
            llTitleBack.setVisibility(GONE);
            vBackDivider.setVisibility(GONE);
        }

    }


}
