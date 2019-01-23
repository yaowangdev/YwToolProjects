package com.appdev.toolkit;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appdev.common.lib.toast.ToastUtils;
import com.appdev.common.lib.ui.KeyboardUtils;
import com.appdev.common.view.base.BaseDialogFragment;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.dialog
 * @createTime 创建时间 ：2018/12/7
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class CommentDialog extends BaseDialogFragment implements View.OnClickListener {
    private EditText etAddComment;
    private ImageView ivBtnPhoto;
    private ImageView ivBtnAt;
    private ImageView ivCommentSend;
    private FrameLayout flContainer;
    private InputMethodManager inputMethodManager;
    private DialogFragmentDataCallback dataCallback;
    private int keyboardHeight;
    private int count;
    private LinearLayout linearLayout;

    @Override
    protected View setDialogView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_comment_layout, container, false);
        flContainer = view.findViewById(R.id.fl_container);
        etAddComment = view.findViewById(R.id.edit_comment);
        ivBtnPhoto = view.findViewById(R.id.image_btn_photo);
        ivBtnAt = view.findViewById(R.id.image_btn_at);
        ivCommentSend = view.findViewById(R.id.image_btn_comment_send);
        ivBtnPhoto.setOnClickListener(this);
        ivBtnAt.setOnClickListener(this);
        ivCommentSend.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        if (!(getActivity() instanceof DialogFragmentDataCallback)) {
            throw new IllegalStateException("DialogFragment 所在的 activity 必须实现 DialogFragmentDataCallback 接口");
        }
        super.onAttach(context);
    }

    public static Builder newCommentBuilder() {
        return new Builder();
    }

    @Override
    public void onStart() {
        super.onStart();
        fillEditText();
        setSoftKeyboard();
    }

    private void fillEditText() {
        dataCallback = (DialogFragmentDataCallback) getActivity();
        etAddComment.setText(dataCallback.getCommentText());
        etAddComment.setSelection(dataCallback.getCommentText().length());
        if (dataCallback.getCommentText().length() == 0) {
            ivCommentSend.setEnabled(false);
            ivCommentSend.setColorFilter(ContextCompat.getColor(getActivity(), R.color.agmobile_grey_2));
        }
        etAddComment.addTextChangedListener(mTextWatcher);
    }

    private void setSoftKeyboard() {
        etAddComment.setFocusable(true);
        etAddComment.setFocusableInTouchMode(true);
        etAddComment.requestFocus();

        //为 commentEditText 设置监听器，在 DialogFragment 绘制完后立即呼出软键盘，呼出成功后即注销
        etAddComment.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    if (inputMethodManager.showSoftInput(etAddComment, 0)) {
                        etAddComment.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }


    private TextWatcher mTextWatcher = new TextWatcher() {

        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() > 0) {
                ivCommentSend.setEnabled(true);
                ivCommentSend.setClickable(true);
                ivCommentSend.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent));
            } else {
                ivCommentSend.setEnabled(false);
                ivCommentSend.setColorFilter(ContextCompat.getColor(getActivity(), R.color.agmobile_grey_2));
            }
        }
    };

    private static CommentDialog newInstance(Builder builder) {
        CommentDialog dialog = new CommentDialog();
        Bundle bundle = getArgumentBundle(builder);
        dialog.setArguments(bundle);
        return dialog;
    }

    public void setKeyboardHeight(int keyboardHeight){
        this.keyboardHeight = keyboardHeight;
//        if(count==0){
            linearLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    keyboardHeight
            );
            linearLayout.setLayoutParams(llParams);
            linearLayout.setGravity(Gravity.CENTER);

            TextView textView = new TextView(getContext());
            textView.setText("Hello");
            textView.setTextColor(Color.BLACK);
            ViewGroup.LayoutParams tvParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            textView.setLayoutParams(tvParams);
            linearLayout.addView(textView);
            flContainer.addView(linearLayout);
//        }else {
//            TextView textView = new TextView(getContext());
//            textView.setText("Hello");
//            textView.setTextColor(Color.BLACK);
//            ViewGroup.LayoutParams tvParams = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            textView.setLayoutParams(tvParams);
//            linearLayout.addView(textView);
//        }
//        count++;
    }

    public void removeImageView(){
        if(flContainer!=null){
            flContainer.removeAllViews();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_btn_photo:
                dataCallback.onImageClick();
                break;
            case R.id.image_btn_at:
                dataCallback.onAtClick();
                break;

            case R.id.image_btn_comment_send:
                ToastUtils.shortToast(getContext(),etAddComment.getText().toString());
                etAddComment.setText("");
                dismiss();
                break;
        }

    }


    public static class Builder extends BaseDialogFragment.Builder<Builder, CommentDialog>{

        @Override
        protected CommentDialog build() {
            return CommentDialog.newInstance(this);
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        dataCallback.setCommentText(etAddComment.getText().toString());
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dataCallback.setCommentText(etAddComment.getText().toString());
        super.onCancel(dialog);
    }
}
