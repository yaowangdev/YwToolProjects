package com.appdev.toolkit.popuwindow;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.appdev.common.lib.log.LogUtils;
import com.appdev.toolkit.R;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.popuwindow
 * @createTime 创建时间 ：2019/3/7
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class CollapseDragView extends LinearLayout {
    private int outShowSize;
    private int gridColumns;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private CollapseDragAdapter mAdapter;
    private boolean showName;
    private OnCollapseDragViewItemClickListener mlistener;
    private LayoutInflater mInflater;
    private View popuView;
    private RecyclerView popuRecyclerView;
    private GridDragAdapter gridDragAdapter;
    private EasyPopup mPopup;
    private int width;
    private int height;
    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;
    private List<ActionModel> actionModels = new ArrayList<>();
    private List<ActionModel> outActions = new ArrayList<>();

    public CollapseDragView(Context context) {
        this(context,null);
    }

    public CollapseDragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CollapseDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        TypedArray typedArray = null;
        try{
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CollapseDragView, 0, 0);
            outShowSize = typedArray.getInt(R.styleable.CollapseDragView_outShowSize,3);
            gridColumns = typedArray.getInt(R.styleable.CollapseDragView_gridColumns,3);
            initView();
        }finally {
            if(typedArray!=null){
                typedArray.recycle();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        LogUtils.d("CollapseDragView","width:"+width+",height:"+height);
    }

    private void initView() {
        mInflater.inflate(R.layout.layout_collapse_view,this,true);
        mRecyclerView = findViewById(R.id.rv_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CollapseDragAdapter(R.layout.item_collapse_drag_view);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(position==outShowSize){
                    showMorePopuWindow();
                    return;
                }
                if(mlistener!=null){
                    mlistener.OnCollapseDragViewItemClick((ActionModel)adapter.getData().get(position),view,position);
                }
            }
        });
        popuView = mInflater.inflate(R.layout.layout_popuwindow_view,this,false);
        popuRecyclerView = popuView.findViewById(R.id.rv_recyclerView);
        popuRecyclerView.setLayoutManager(new GridLayoutManager(mContext,gridColumns));
        gridDragAdapter = new GridDragAdapter(R.layout.item_grid_drag_view,actionModels);
        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(gridDragAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(popuRecyclerView);
        gridDragAdapter.enableDragItem(mItemTouchHelper);

        gridDragAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mlistener!=null){
                    mlistener.OnCollapseDragViewItemClick((ActionModel)adapter.getData().get(position),view,position);
                }
                if(mPopup!=null && mPopup.isShowing()){
                    mPopup.dismiss();
                }
            }
        });

        gridDragAdapter.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Vibrator vibrator = (Vibrator)mContext.getSystemService(mContext.VIBRATOR_SERVICE);
                vibrator.vibrate(100);
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                List<ActionModel> data = gridDragAdapter.getData();
                outActions.clear();
                for (int i=0;i<outShowSize;i++){
                    outActions.add(data.get(i));
                }
                ActionModel actionModel = new ActionModel();
                actionModel.setActionIconId(R.drawable.img_more);
                outActions.add(actionModel);
                mAdapter.setNewData(outActions);
            }
        });
        popuRecyclerView.setAdapter(gridDragAdapter);
    }


    class CollapseDragAdapter extends BaseQuickAdapter<ActionModel,BaseViewHolder>{

        public CollapseDragAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, ActionModel item) {
            helper.setImageResource(R.id.iv_img,item.getActionIconId());
            helper.setText(R.id.tv_name,item.getActionName());
            helper.setGone(R.id.tv_name,showName);
            if(helper.getAdapterPosition()==mAdapter.getItemCount()-1){
                helper.setGone(R.id.v_line,false);
            }
        }
    }

    class GridDragAdapter extends BaseItemDraggableAdapter<ActionModel,BaseViewHolder> {


        public GridDragAdapter(int layoutResId, List<ActionModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ActionModel item) {
            helper.setImageResource(R.id.iv_img,item.getActionIconId());
            helper.setText(R.id.tv_name,item.getActionName());
        }
    }


    interface OnCollapseDragViewItemClickListener{
        void OnCollapseDragViewItemClick(ActionModel actionModel,View view,int position);
    }


    public void setActionList(List<ActionModel> actionList){
        this.actionModels.addAll(actionList);
        if(actionList.size()>outShowSize){
            ActionModel actionModel = new ActionModel();
            actionModel.setActionIconId(R.drawable.img_more);
            outActions = actionList.subList(0, outShowSize);
            outActions.add(actionModel);
            mAdapter.setNewData(outActions);
        }else {
            mAdapter.setNewData(actionModels);
        }
        gridDragAdapter.setNewData(actionModels);
    }

    public void showName(boolean showName){
        this.showName = showName;
        mAdapter.setNewData(mAdapter.getData());
    }

    public void setCollapseDragViewItemClickListener(OnCollapseDragViewItemClickListener listener){
        this.mlistener = listener;
    }

    private void showMorePopuWindow() {
        mPopup = new EasyPopup(mContext)
                .setContentView(popuView)
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setBackgroundDimEnable(true)
                .setDimValue(0.2f)
                .setFocusAndOutsideEnable(true)
                .setAnimationStyle(R.style.AnimTransitionRight)
                .createPopup();
        if(mPopup!=null){
            mPopup.showAtAnchorView(this,VerticalGravity.CENTER,HorizontalGravity.LEFT,width,height/2);
            mPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    setVisibility(VISIBLE);
                }
            });
            setVisibility(INVISIBLE);
        }
    }
}
