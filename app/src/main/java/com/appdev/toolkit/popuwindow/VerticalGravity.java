package com.appdev.toolkit.popuwindow;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Popup竖直
 * @author 创建人 ：xiejiexin
 * @version 1.0
 * @package 包名 ：com.augurit.common.view.popup
 * @createTime 创建时间 ：2018/4/8
 * @modifyBy 修改人 ：xiejiexin
 * @modifyTime 修改时间 ：2018/4/8
 * @modifyMemo 修改备注：
 */

@IntDef({
        VerticalGravity.CENTER,
        VerticalGravity.ABOVE,
        VerticalGravity.BELOW,
        VerticalGravity.ALIGN_TOP,
        VerticalGravity.ALIGN_BOTTOM,
})
@Retention(RetentionPolicy.SOURCE)
public @interface VerticalGravity {
    int CENTER = 0;
    int ABOVE = 1;
    int BELOW = 2;
    int ALIGN_TOP = 3;
    int ALIGN_BOTTOM = 4;
}
