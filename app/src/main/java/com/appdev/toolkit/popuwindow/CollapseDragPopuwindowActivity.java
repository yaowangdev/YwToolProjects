package com.appdev.toolkit.popuwindow;

import android.view.View;

import com.appdev.common.lib.toast.ToastUtils;
import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.MenuItem;
import com.appdev.toolkit.R;

import java.util.ArrayList;
import java.util.List;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

public class CollapseDragPopuwindowActivity extends BaseActivity {
    private TitleBar mTitleBar;
    private CollapseDragView collapseDragView;
    private PhoneToolBar phoneToolBar;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_collapse_drag_popuwindow;
    }

    @Override
    protected void initView() {
        mTitleBar = findViewById(R.id.titleBar);
        phoneToolBar = findViewById(R.id.ctb_toolbar);
        mTitleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
        collapseDragView = findViewById(R.id.cdv_view);
        initData();
        collapseDragView.setCollapseDragViewItemClickListener(new CollapseDragView.OnCollapseDragViewItemClickListener() {
            @Override
            public void OnCollapseDragViewItemClick(ActionModel actionModel, View view, int position) {
                ToastUtils.shortToast(getApplicationContext(),actionModel.getActionName());
            }
        });
    }

    private void initData() {
        List<ActionModel> actionModels = new ArrayList<>();
        ActionModel actionModel1 = new ActionModel();
        actionModel1.setActionIconId(R.drawable.img_add_bookmark);
        actionModel1.setActionName("bookmark");
        ActionModel actionModel2 = new ActionModel();
        actionModel2.setActionIconId(R.drawable.img_attr);
        actionModel2.setActionName("attr");
//        ActionModel actionModel3 = new ActionModel();
//        actionModel3.setActionIconId(R.drawable.img_measure);
//        actionModel3.setActionName("measure");
//        ActionModel actionModel4 = new ActionModel();
//        actionModel4.setActionIconId(R.drawable.img_search);
//        actionModel4.setActionName("search");
//        ActionModel actionModel5 = new ActionModel();
//        actionModel5.setActionIconId(R.drawable.img_layer);
//        actionModel5.setActionName("layer");
        actionModels.add(actionModel1);
        actionModels.add(actionModel2);
//        actionModels.add(actionModel3);
//        actionModels.add(actionModel4);
//        actionModels.add(actionModel5);
        collapseDragView.setActionList(actionModels);

        /********************************************************/

        phoneToolBar.showBack();
        phoneToolBar.showClose(true);
        phoneToolBar.setContent("Hello");
        phoneToolBar.setContentColor(getResources().getColor(R.color.agmobile_black));
        MenuItem menuItem = new MenuItem();
        menuItem.setIconRes(R.drawable.icon_voice);
        menuItem.setTitle("voice");
        phoneToolBar.addMenu(menuItem);


    }
}
