package com.appdev.toolkit.popuwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appdev.toolkit.MenuItem;
import com.appdev.toolkit.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.popuwindow
 * @createTime 创建时间 ：2019/3/8
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class CustomToolBar extends RelativeLayout implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    private Context mContext;
    private LayoutInflater mInflater;
    private RelativeLayout rlHamburger;
    private ImageView ivHead;
    private ImageView ivBack;
    private ImageView ivClose;
    private View vLine;
    private TextView tvInput;
    private RecyclerView mRecyclerView;
    private ToolBarMenuAdapter menuAdapter;
    private List<MenuItem> menuItems = new ArrayList<>();
    private HamburgerToggleListener toggleListener;
    private BackClickListener backClickListener;
    private ContentClickListener contentClickListener;
    private CloseClickListener closeClickListener;
    private ToolMenuItemClickListener toolMenuItemClickListener;

    @ToolState
    private int toolState = ToolState.HEAD;

    public CustomToolBar(Context context) {
        this(context,null);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        initView();
    }

    private void initView() {
        mInflater.inflate(R.layout.layout_custom_toolbar_view,this,true);
        ivHead = findViewById(R.id.iv_head);
        ivBack = findViewById(R.id.iv_back);
        tvInput = findViewById(R.id.tv_input);
        rlHamburger = findViewById(R.id.rl_hamburger);
        mRecyclerView = findViewById(R.id.rv_menu_recyclerview);
        ivClose = findViewById(R.id.iv_close);
        vLine = findViewById(R.id.v_line);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        menuAdapter = new ToolBarMenuAdapter(R.layout.item_tool_menu_view);
        mRecyclerView.setAdapter(menuAdapter);
        rlHamburger.setOnClickListener(this);
        tvInput.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        menuAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_hamburger:
                if(toolState==ToolState.HEAD){
                    toggleHamburger();
                }else if(toolState==ToolState.BACK){
                    goBack();
                }
                break;
            case R.id.tv_input:
                clickContent();
                break;
            case R.id.iv_close:
                clickClose();
                break;
        }
    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        clickMenu(((MenuItem)adapter.getItem(position)).getTitle());
    }


    class ToolBarMenuAdapter extends BaseQuickAdapter<MenuItem,BaseViewHolder>{

        public ToolBarMenuAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, MenuItem item) {
            helper.setImageResource(R.id.iv_img,item.getIconRes());
        }

    }

    /**
     * 添加菜单
     * @param menuItem
     */
    public void addMenu(MenuItem menuItem){
        menuItems.add(menuItem);
        menuAdapter.setNewData(menuItems);
    }

    /**
     * 根据菜单名移除菜单
     * @param menuName
     */
    public void removeMenu(String menuName){
        Iterator<MenuItem> iterator = menuItems.iterator();
        while (iterator.hasNext()){
            MenuItem next = iterator.next();
            if(menuName.equals(next.getTitle())){
                iterator.remove();
            }
        }
        menuAdapter.setNewData(menuItems);
    }

    /**
     * 设置ToolBar内容
     * @param hint
     */
    public void setHint(String hint){
        tvInput.setText(hint);
    }

    /**
     * 设置ToolBar内容颜色
     * @param color
     */
    public void setHintColor(int color){
        tvInput.setTextColor(color);
    }

    /**
     * 显示头像图标
     */
    public void showHamburger(){
        ivHead.setVisibility(VISIBLE);
        ivBack.setVisibility(GONE);
    }

    /**
     * 显示返回图标
     */
    public void showBack(){
        ivHead.setVisibility(GONE);
        ivBack.setVisibility(VISIBLE);
    }

    /**
     * 是否显示关闭按钮
     * @param show
     */
    public void showClose(boolean show){
        ivClose.setVisibility(show?VISIBLE:GONE);
        vLine.setVisibility(show?VISIBLE:GONE);
    }

    /**
     * 是否显示右边菜单
     * @param show
     */
    public void showMenus(boolean show){
        mRecyclerView.setVisibility(show?VISIBLE:GONE);
    }

//    /**
//     * 设置Toolbar状态
//     * @param toolState
//     */
//    public void setToolState(@ToolState int toolState){
//        this.toolState = toolState;
//    }

    /**
     * 开关回调
     * @param toggleListener
     */
    public void setOnToggleHamburgerListener(HamburgerToggleListener toggleListener){
        this.toggleListener =  toggleListener;
    }

    public void setOnBackClickListener(BackClickListener backClickListener){
        this.backClickListener = backClickListener;
    }

    public void setOnContentClickListener(ContentClickListener contentClickListener){
        this.contentClickListener = contentClickListener;
    }

    public void setOnCloseClickListener(CloseClickListener closeClickListener){
        this.closeClickListener = closeClickListener;
    }

    public void setOnToolMenuClickListener(ToolMenuItemClickListener toolMenuClickListener){
        this.toolMenuItemClickListener = toolMenuClickListener;
    }

    /**
     * 开关头像
     */
    private void toggleHamburger(){
        if(toggleListener!=null){
            toggleListener.toggle();
        }
    }

    /**
     * 回上一状态
     */
    private void goBack() {
        if(backClickListener!=null){
            backClickListener.back();
        }
    }

    /**
     * 点击内容区
     */
    private void clickContent() {
        if(contentClickListener!=null){
            contentClickListener.contentClick();
        }
    }

    /**
     * 关闭当前状态（即回主状态）
     */
    private void clickClose(){
        if(closeClickListener!=null){
            closeClickListener.close();
        }
    }

    /**
     * 点击菜单项
     * @param menuName
     */
    private void clickMenu(String menuName){
        if(toolMenuItemClickListener!=null){
            toolMenuItemClickListener.itemClick(menuName);
        }
    }


    interface HamburgerToggleListener{
        void toggle();
    }

    interface BackClickListener{
        void back();
    }

    interface ContentClickListener{
        void contentClick();
    }

    interface CloseClickListener{
        void close();
    }

    interface ToolMenuItemClickListener{
        void itemClick(String menuName);
    }

}
