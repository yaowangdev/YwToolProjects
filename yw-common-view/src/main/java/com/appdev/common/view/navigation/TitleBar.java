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

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.common.view.navigation
 * @createTime 创建时间 ：2018/11/28
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class TitleBar extends RelativeLayout {
    private String mTitleText;
    private String mBackText;
    private int mBackImg;
    private boolean mShowBackImg;
    private boolean mShowBack;
    private String mMoreText;
    private int mMoreImg;
    private boolean mShowMoreImg;
    private boolean mShowMore;
    private boolean mShowViewBar;
    private int mBarStyle;

    private TextView tvTitle;
    private TextView tvBack;
    private ImageView ivBack;
    private TextView tvMore;
    private ImageView ivMore;
    private View vViewBar;
    private LinearLayout llBackBtn;
    private LinearLayout llMoreBtn;

    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = null;
        try {
            ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar,0,0);
            mTitleText = ta.getString(R.styleable.TitleBar_titleText);
            mBackText = ta.getString(R.styleable.TitleBar_backText);
            mBackImg = ta.getInt(R.styleable.TitleBar_backImg,-1);
            mShowBackImg = ta.getBoolean(R.styleable.TitleBar_showBackImg,true);
            mShowBack = ta.getBoolean(R.styleable.TitleBar_showBack,true);
            mMoreText = ta.getString(R.styleable.TitleBar_moreText);
            mMoreImg = ta.getInt(R.styleable.TitleBar_moreImg,-1);
            mShowMoreImg = ta.getBoolean(R.styleable.TitleBar_showMoreImg,false);
            mShowMore = ta.getBoolean(R.styleable.TitleBar_showMore,true);
            mShowViewBar = ta.getBoolean(R.styleable.TitleBar_showViewBar,false);
            mBarStyle = ta.getInt(R.styleable.TitleBar_barStyle,1);
            if(mBarStyle==1){
                LayoutInflater.from(context).inflate(R.layout.navigation_title_bar_center,this);
            }else if(mBarStyle==2){
                LayoutInflater.from(context).inflate(R.layout.navigation_title_bar_left,this);
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
        tvTitle = findViewById(R.id.title);
        tvBack = findViewById(R.id.txt_back);
        ivBack = findViewById(R.id.img_back);
        tvMore = findViewById(R.id.txt_more);
        ivMore = findViewById(R.id.img_more);
        if(mBarStyle==2){
            vViewBar = findViewById(R.id.view_divider_back);
        }
        llBackBtn = findViewById(R.id.title_back);
        llMoreBtn = findViewById(R.id.btn_more);
    }

    private void afterInit() {
        tvTitle.setText(mTitleText);
        tvBack.setText(mBackText);
        if(mBackImg!=-1){
            ivBack.setImageDrawable(getResources().getDrawable(mBackImg));
        }
        ivBack.setVisibility(mShowBackImg? VISIBLE:GONE);
        llBackBtn.setVisibility(mShowBack? VISIBLE:GONE);
        tvMore.setText(mMoreText);
        if(mMoreImg!=-1){
            ivMore.setImageDrawable(getResources().getDrawable(mMoreImg));
        }
        ivMore.setVisibility(mShowMoreImg? VISIBLE:GONE);
        llMoreBtn.setVisibility(mShowMore? VISIBLE:GONE);
        if(mBarStyle==2){
            vViewBar.setVisibility(mShowViewBar? VISIBLE:GONE);
        }

        llBackBtn.setOnClickListener(v -> goBack());
    }

    /**
     * 默认返回
     */
    private void goBack() {
        ((Activity) getContext()).finish();
    }

    /**
     * 设置更多按钮事件
     *
     * @param listener 事件监听
     */
    public void setMoreButtonAction(OnClickListener listener) {
        llMoreBtn.setOnClickListener(listener);
    }
}
