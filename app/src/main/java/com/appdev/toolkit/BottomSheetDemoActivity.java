package com.appdev.toolkit;

import java.util.List;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit
 * @createTime 创建时间 ：2019/1/30
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class BottomSheetDemoActivity extends DemoActivity {


    @Override
    protected List<MenuItem> getData() {
        mItems.add(new MenuItem(EasyBottomSheetActivity.class,"BottomSheet简单应用"));
        return mItems;
    }
}
