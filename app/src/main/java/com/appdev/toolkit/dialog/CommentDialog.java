package com.appdev.toolkit.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appdev.common.view.base.BaseDialogFragment;
import com.appdev.toolkit.R;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.dialog
 * @createTime 创建时间 ：2018/12/7
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class CommentDialog extends BaseDialogFragment {

    @Override
    protected View setDialogView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_comment_layout,container,false);
    }

    public static Builder newCommontBuilder() {
        return new Builder();
    }

    private static CommentDialog newInstance(Builder builder) {
        CommentDialog dialog = new CommentDialog();
        Bundle bundle = getArgumentBundle(builder);
        dialog.setArguments(bundle);
        return dialog;
    }


    public static class Builder extends BaseDialogFragment.Builder<Builder, CommentDialog>{

        @Override
        protected CommentDialog build() {
            return CommentDialog.newInstance(this);
        }
    }
}
