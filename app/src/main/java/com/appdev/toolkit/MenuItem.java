package com.appdev.toolkit;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.entity
 * @createTime 创建时间 ：2018/12/5
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class MenuItem {
    private Class<?> activity;
    private String title;

    public MenuItem(Class<?> activity, String title) {
        this.activity = activity;
        this.title = title;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
