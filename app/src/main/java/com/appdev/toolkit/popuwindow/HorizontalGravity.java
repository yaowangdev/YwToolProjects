package com.appdev.toolkit.popuwindow;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Popup水平位置
 * @author 创建人 ：xiejiexin
 * @version 1.0
 * @package 包名 ：com.augurit.common.view.popup
 * @createTime 创建时间 ：2018/4/8
 * @modifyBy 修改人 ：xiejiexin
 * @modifyTime 修改时间 ：2018/4/8
 * @modifyMemo 修改备注：
 */

@IntDef({
        HorizontalGravity.CENTER,
        HorizontalGravity.LEFT,
        HorizontalGravity.RIGHT,
        HorizontalGravity.ALIGN_LEFT,
        HorizontalGravity.ALIGN_RIGHT,
})
@Retention(RetentionPolicy.SOURCE)
public @interface HorizontalGravity {
    int CENTER = 0;
    int LEFT = 1;
    int RIGHT = 2;
    int ALIGN_LEFT = 3;
    int ALIGN_RIGHT = 4;
}