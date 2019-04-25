package com.appdev.toolkit.nestscroll;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.appdev.toolkit.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

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
public class MainNestScrollAdapter extends BaseMultiItemQuickAdapter<MultipleItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MainNestScrollAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TYPE_BANNER, R.layout.layout_nest_banner);
        addItemType(MultipleItem.TYPE_TABLELAYOUT_AND_VIEWPAGER, R.layout.layout_nest_viewpager);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()){
            case MultipleItem.TYPE_BANNER:
                loadBanner();
                break;
            case MultipleItem.TYPE_TABLELAYOUT_AND_VIEWPAGER:
                SimpleViewPagerIndicator indicator = helper.getView(R.id.svp_indicator);
                ViewPager viewpager = helper.getView(R.id.vp_viewpager);
                loadFragments(indicator,viewpager);
                break;
        }

    }

    /**
     * 加载Banner
     */
    protected void loadBanner() {

    }

    /**
     * 加载Fragments
     */
    protected void loadFragments(SimpleViewPagerIndicator indicator,ViewPager viewpager) {

    }
}
