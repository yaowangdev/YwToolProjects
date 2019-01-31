package com.appdev.toolkit.bottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.R;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit
 * @createTime 创建时间 ：2019/1/30
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class EasyBottomSheetActivity extends BaseActivity implements View.OnClickListener {
    private TitleBar mTitleBar;
    private Button mExtandBtn;
    private Button mCollapseBtn;
    private Button mHideBtn;
    private Button mShowBtn;
    private Button mToggleBtn;
    private TextView mBottomSheetHeading;
    private BottomSheetBehavior bottomSheetBehavior;
    private RelativeLayout rlBottomSheet;


    @Override
    protected int getLayoutView() {
        return R.layout.activity_easy_bottom_sheet;
    }

    @Override
    protected void initView() {
        mTitleBar = findViewById(R.id.titleBar);
        rlBottomSheet = findViewById(R.id.bottomSheetLayout);
        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet);
        mBottomSheetHeading = findViewById(R.id.bottomSheetHeading);
        mExtandBtn = findViewById(R.id.extand_bottom_sheet_button);
        mCollapseBtn = findViewById(R.id.collapse_bottom_sheet_button);
        mHideBtn = findViewById(R.id.hide_bottom_sheet_button);
        mShowBtn = findViewById(R.id.show_bottom_sheet_button);
        mToggleBtn = findViewById(R.id.bottom_sheet_toggle_button);
        mExtandBtn.setOnClickListener(this);
        mCollapseBtn.setOnClickListener(this);
        mHideBtn.setOnClickListener(this);
        mShowBtn.setOnClickListener(this);
        mToggleBtn.setOnClickListener(this);
        mTitleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetHeading.setText(getString(R.string.text_collapse_me));
                } else {
                    mBottomSheetHeading.setText(getString(R.string.text_expand_me));
                }
                // Check Logs to see how bottom sheets behaves
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.extand_bottom_sheet_button:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.collapse_bottom_sheet_button:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.hide_bottom_sheet_button:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.show_bottom_sheet_button:
                new CustomButtomSheetDialogFragment().show(getSupportFragmentManager(),"show");
                break;
            case R.id.bottom_sheet_toggle_button:
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED ){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN || bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
        }

    }
}
