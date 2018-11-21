package com.appdev.common.view.base;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.appdev.common.lib.ui.ClickUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * 视图列表Adapter基类
 * @author 创建人 ：xiejiexin
 * @version 1.0
 * @package 包名 ：com.augurit.agmobile.busi.bpm.viewlist.view
 * @createTime 创建时间 ：2018/9/3
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public abstract class BaseViewListAdapter<T> extends RecyclerView.Adapter<BaseViewListAdapter.BaseDataViewHolder> {

    /**
     * 列表显示
     */
    public static final int LAYOUT_LIST = 1;

    /**
     * 网格显示
     */
    public static final int LAYOUT_GRID = 2;

    @IntDef({LAYOUT_LIST, LAYOUT_GRID})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LayoutStyle {}

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mLayoutStyle;

    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    public BaseViewListAdapter(Context context) {
        this(context, LAYOUT_LIST);
    }

    public BaseViewListAdapter(Context context, @LayoutStyle int layoutStyle) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mDatas = new ArrayList<>();
        mLayoutStyle = layoutStyle;
    }

    @CallSuper
    @Override
    public void onBindViewHolder(BaseDataViewHolder holder, int position) {
        // 点击长按事件处理
        ClickUtil.bind(holder.itemView, v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, position, mDatas.get(position));
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (mOnItemLongClickListener != null) {
                return mOnItemLongClickListener.onItemLongClick(v, position, mDatas.get(position));
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 设置数据，将会调用{@link #notifyDataSetChanged()}
     * @param datas
     */
    public void setDatas(List<T> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 添加数据，将会调用{@link #notifyDataSetChanged()}
     * @param datas
     */
    public void addDatas(List<T> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 清除数据，将会调用{@link #notifyDataSetChanged()}
     */
    public void clearDatas() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public interface OnItemClickListener<T> {
        /**
         * 列表项点击
         * @param itemView itemView
         * @param position 位置
         * @param data 数据
         */
        void onItemClick(View itemView, int position, T data);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener<T>  {
        /**
         * 列表项长按
         * @param itemView itemView
         * @param position 位置
         * @param data 数据
         * @return 是否已处理事件
         */
        boolean onItemLongClick(View itemView, int position, T data);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    protected static abstract class BaseDataViewHolder extends RecyclerView.ViewHolder {

        public BaseDataViewHolder(View itemView) {
            super(itemView);
        }
    }
}
