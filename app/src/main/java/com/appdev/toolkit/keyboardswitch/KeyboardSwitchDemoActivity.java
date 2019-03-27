package com.appdev.toolkit.keyboardswitch;

import com.appdev.toolkit.DemoActivity;
import com.appdev.toolkit.MenuItem;

import java.util.List;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.keyboardswitch
 * @createTime 创建时间 ：2019/3/26
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class KeyboardSwitchDemoActivity extends DemoActivity {
    @Override
    protected List<MenuItem> getData() {
        mItems.add(new MenuItem(KeyboardSwitchActivity1.class,"键盘切换1"));
        return mItems;
    }
}
