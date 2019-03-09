package com.appdev.toolkit.coordinatorlayout;

import com.appdev.toolkit.DemoActivity;
import com.appdev.toolkit.MenuItem;

import java.util.List;

public class CoordinatorLayoutDemoActivity extends DemoActivity {
    @Override
    protected List<MenuItem> getData() {
        mItems.add(new MenuItem(EazyCoordinatorLayoutActivity.class,"CoordinatorLayout简单应用"));
        return mItems;
    }
}
