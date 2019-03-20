package com.appdev.toolkit.popuwindow;

import com.appdev.toolkit.MenuItem;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.popuwindow
 * @createTime 创建时间 ：2019/3/11
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public interface IToolBar {

    void setContent(String value);

    void setContentColor(int color);

    void showHamburger();

    void showBack();

    void showClose(boolean show);

    void showMenus(boolean show);

    void addMenu(MenuItem menuItem);

    void removeMenu(String menuName);

    void toggleHamburger();

    void goBack();

    void setToolState(@ToolState int state);
}
