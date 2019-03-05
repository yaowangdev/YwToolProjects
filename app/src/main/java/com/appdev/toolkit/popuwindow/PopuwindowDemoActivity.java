package com.appdev.toolkit.popuwindow;

import com.appdev.toolkit.DemoActivity;
import com.appdev.toolkit.MenuItem;

import java.util.List;

public class PopuwindowDemoActivity extends DemoActivity {
    @Override
    protected List<MenuItem> getData() {
        mItems.add(new MenuItem(CollapseDragPopuwindowActivity.class,"收缩的可拖拽工具栏"));
        return mItems;
    }
}
