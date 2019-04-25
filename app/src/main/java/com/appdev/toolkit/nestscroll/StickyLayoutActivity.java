package com.appdev.toolkit.nestscroll;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.appdev.common.view.base.BaseActivity;
import com.appdev.common.view.navigation.TitleBar;
import com.appdev.toolkit.R;

import java.util.ArrayList;
import java.util.List;

import static com.appdev.toolkit.MainActivity.MAIN_EXTRA_TITLE;

/**
 * @author 创建人 ：yaowang
 * @version 1.0
 * @package 包名 ：com.appdev.toolkit.nestscroll
 * @createTime 创建时间 ：2019/4/18
 * @modifyBy 修改人 ：
 * @modifyTime 修改时间 ：
 * @modifyMemo 修改备注：
 */
public class StickyLayoutActivity extends BaseActivity {
    private TitleBar mTitleBar;
    private HomeRecyclerView mParentRecyclerView;
    private List<MultipleItem> multipleItems = new ArrayList<>();
    private MainNestScrollAdapter mAdapter;
    private String[] mTitles = new String[] { "简介", "评价", "相关" };
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    private FragmentPagerAdapter fragmentPagerAdapter;
    private HomeRecyclerView.OnScrollableChildCallback onScrollableChildCallback;

    public RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int newStatus) {
            if (newStatus == RecyclerView.SCROLL_STATE_IDLE) {
                if (mParentRecyclerView != null) {
                    onScrollableChildCallback.onScrollableChildChangedToIdle(recyclerView);
                }
            }
        }
    };

    @Override
    protected int getLayoutView() {
        return R.layout.activity_sticky_layout;
    }

    @Override
    protected void initView() {
        mTitleBar = findViewById(R.id.titleBar);
        mTitleBar.setTitleText(mIntent.getStringExtra(MAIN_EXTRA_TITLE));
        mParentRecyclerView = findViewById(R.id.hrv_recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mParentRecyclerView.setLayoutManager(mLayoutManager);

        multipleItems.add(new MultipleItem(MultipleItem.TYPE_BANNER,null));
        multipleItems.add(new MultipleItem(MultipleItem.TYPE_TABLELAYOUT_AND_VIEWPAGER,null));

        onScrollableChildCallback = mParentRecyclerView.getOnScrollableChildCallback();

        mAdapter = new MainNestScrollAdapter(multipleItems){
            @Override
            protected void loadBanner() {
                super.loadBanner();
            }

            @Override
            protected void loadFragments(SimpleViewPagerIndicator indicator, ViewPager viewpager) {
                super.loadFragments(indicator, viewpager);
                indicator.setTitles(mTitles);
                for (int i = 0; i < mTitles.length; i++)
                {
                    mFragments[i] = TabFragment.newInstance(mTitles[i]);
                    mFragments[i].setParentRecyclerView(mParentRecyclerView);
                    mFragments[i].setOnScrollListener(onScrollListener);
                }

                fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
                {
                    @Override
                    public int getCount()
                    {
                        return mTitles.length;
                    }

                    @Override
                    public Fragment getItem(int position)
                    {
                        return mFragments[position];
                    }

                };
                viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        indicator.scroll(position, positionOffset);
                    }

                    @Override
                    public void onPageSelected(int position) {
                        pageSelected(viewpager,position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                viewpager.setAdapter(fragmentPagerAdapter);
                viewpager.setCurrentItem(0);
                pageSelected(viewpager,0);
            }

            private void pageSelected(ViewPager viewPager, int position) {
                Fragment fragment = ((FragmentPagerAdapter) viewPager.getAdapter()).getItem(position);
                if (fragment instanceof ScrollableChild) {
                    onScrollableChildCallback.onScrollableChildSelected((ScrollableChild) fragment);
                }
            }
        };
        mParentRecyclerView.setAdapter(mAdapter);
    }
}
