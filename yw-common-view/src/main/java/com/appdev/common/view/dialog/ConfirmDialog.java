package com.appdev.common.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.appdev.common.view.R;
import com.appdev.common.view.base.BaseDialogFragment;

public class ConfirmDialog extends BaseDialogFragment {
    private static final String LEFT_TEXT = "left_text";
    private static final String RIGHT_TEXT = "right_text";
    private static final String PARAM_TITLE = "title";
    private static final String PARAM_MESSAGE = "message";

    private TextView tvTitle;
    private TextView tvContent;
    private Button btnConfirm;
    private Button btnCancel;


    @Override
    protected View setDialogView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_confirm_dialog, container, false);
        tvTitle = view.findViewById(R.id.tv_title);
        tvContent = view.findViewById(R.id.et_dialog_content);
        btnConfirm = view.findViewById(R.id.btn_confirm);
        btnCancel = view.findViewById(R.id.btn_cancel);
        if (getArguments()!=null) {
            tvTitle.setText(getArguments().getString(PARAM_TITLE));
            tvContent.setText(getArguments().getString(PARAM_MESSAGE));
            btnCancel.setText(getArguments().getString(LEFT_TEXT));
            btnConfirm.setText(getArguments().getString(RIGHT_TEXT));
        }
        return view;
    }

    public static Builder newConfirmBuilder() {
        return new Builder();
    }

    private static ConfirmDialog newInstance(Builder builder) {
        ConfirmDialog dialog = new ConfirmDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString(LEFT_TEXT, builder.leftText);
        bundle.putString(RIGHT_TEXT, builder.rightText);
        bundle.putString(PARAM_TITLE, builder.mTitle);
        bundle.putString(PARAM_MESSAGE, builder.mMessage);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static class Builder extends BaseDialogFragment.Builder<Builder, ConfirmDialog> {

        private String mTitle;
        private String mMessage;
        private String leftText;
        private String rightText;

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }

        public Builder setLeftText(String leftText) {
            this.leftText = leftText;
            return this;
        }

        public Builder setRightText(String rightText) {
            this.rightText = rightText;
            return this;
        }

        @Override
        public ConfirmDialog build() {
            return ConfirmDialog.newInstance(this);
        }
    }

}
