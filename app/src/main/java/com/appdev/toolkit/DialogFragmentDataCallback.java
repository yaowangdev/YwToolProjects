package com.appdev.toolkit;

import android.view.View;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit
 * @createTime 创建时间 ：2018/12/29
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public interface DialogFragmentDataCallback {

    String getCommentText();

    void setCommentText(String commentTextTemp);

    void onImageClick();

    void onAtClick();

}
