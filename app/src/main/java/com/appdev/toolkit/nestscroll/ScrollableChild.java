package com.appdev.toolkit.nestscroll;

import android.support.v7.widget.RecyclerView;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.nestscroll
 * @createTime 创建时间 ：2019/4/18
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public abstract class ScrollableChild extends BaseFragment{

    protected RecyclerView.OnScrollListener onScrollListener;
    protected RecyclerView parentRecyclerView;

    public abstract RecyclerView getRecyclerView();

    public RecyclerView getParentRecyclerView() {
        return this.parentRecyclerView;
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void setParentRecyclerView(RecyclerView recyclerView) {
        this.parentRecyclerView = recyclerView;
    }
}
