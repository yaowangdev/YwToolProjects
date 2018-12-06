package com.appdev.toolkit.adapter;

import android.support.annotation.Nullable;

import com.appdev.toolkit.R;
import com.appdev.toolkit.entity.HomeItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.adapter
 * @createTime 创建时间 ：2018/12/5
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class HomeAdapter extends BaseQuickAdapter<HomeItem,BaseViewHolder> {


    public HomeAdapter(int layoutResId, @Nullable List<HomeItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.tv_title,item.getTitle());
    }
}
