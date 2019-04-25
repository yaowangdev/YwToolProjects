package com.appdev.toolkit.nestscroll;

import com.appdev.toolkit.DemoActivity;
import com.appdev.toolkit.MenuItem;

import java.util.List;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.nestscroll
 * @createTime 创建时间 ：2019/4/18
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class NestScrollDemoActivity extends DemoActivity{
    @Override
    protected List<MenuItem> getData() {
        mItems.add(new MenuItem(StickyLayoutActivity.class,"可悬浮导航栏"));
        return mItems;
    }
}
