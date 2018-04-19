package org.faqrobot.textrecyclerview.callback;

import android.support.v4.view.ViewPager;

/**
 * Created by 孟晨 on 2018/4/18.
 */

public abstract class PagerChangeCallback implements ViewPager.OnPageChangeListener {

    public abstract void pageSelected(int position);

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
