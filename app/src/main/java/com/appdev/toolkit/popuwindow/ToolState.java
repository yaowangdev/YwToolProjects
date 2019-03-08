package com.appdev.toolkit.popuwindow;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.popuwindow
 * @createTime 创建时间 ：2019/3/8
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */

@IntDef({ToolState.HEAD,ToolState.BACK})
@Retention(RetentionPolicy.SOURCE)
public @interface ToolState {
    int HEAD = 1;
    int BACK = 2;
}
