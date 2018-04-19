package org.faqrobot.textrecyclerview.ui.fra.home;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.flyco.tablayout.SlidingTabLayout;
import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.callback.PagerChangeCallback;
import org.faqrobot.textrecyclerview.ui.basefra.RxBaseFragment;
import org.faqrobot.textrecyclerview.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends RxBaseFragment {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private List<String> titles = Arrays.asList("高颜值", "高颜值", "高颜值", "高颜值", "高颜值");
    private List<Fragment> fragments = new ArrayList<>();
    private HomeInner1Fra homeInner1Fra;
    private HomeInner2Fra homeInner2Fra;
    private HomeInner3Fra homeInner3Fra;
    private HomeInner4Fra homeInner4Fra;
    private HomeInner5Fra homeInner5Fra;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews(View view ) {
        mViewPager= (ViewPager) view.findViewById(R.id.view_pager);
        mSlidingTabLayout= (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);

        homeInner1Fra = new HomeInner1Fra();
        fragments.add(homeInner1Fra);
        homeInner2Fra = new HomeInner2Fra();
        fragments.add(homeInner2Fra);
        homeInner3Fra = new HomeInner3Fra();
        fragments.add(homeInner3Fra);
        homeInner4Fra = new HomeInner4Fra();
        fragments.add(homeInner4Fra);
        homeInner5Fra = new HomeInner5Fra();
        fragments.add(homeInner5Fra);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
            @Override
            public int getCount() {
                return titles.size();
            }
        });
        //缓存5个
        mViewPager.setOffscreenPageLimit(5);
        mSlidingTabLayout.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new PagerChangeCallback() {
            @Override
            public void pageSelected(int position) {
                if(position!=2&&homeInner3Fra.judgeIsVideoPlay()){
                    JCVideoPlayer.releaseAllVideos();
                }
            }
        });
        //缓存5个fragment，让fragment第二次切换到自身不走任何回调
//        mViewPager.setOffscreenPageLimit(5);
//        04-13 18:06:51.186 3658-3658/org.faqrobot.textrecyclerview E/测试: 第1次数据获取完毕
//        04-13 18:06:51.188 3658-3658/org.faqrobot.textrecyclerview E/测试: onViewCreated:
//        04-13 18:06:51.189 3658-3658/org.faqrobot.textrecyclerview E/测试: 第1次数据获取完毕
//        04-13 18:06:51.189 3658-3658/org.faqrobot.textrecyclerview E/测试: onViewCreated:
//        04-13 18:06:51.191 3658-3658/org.faqrobot.textrecyclerview E/测试: 第1次数据获取完毕
//        04-13 18:06:51.191 3658-3658/org.faqrobot.textrecyclerview E/测试: onViewCreated:
//        04-13 18:06:51.193 3658-3658/org.faqrobot.textrecyclerview E/测试: 第1次数据获取完毕
//        04-13 18:06:51.193 3658-3658/org.faqrobot.textrecyclerview E/测试: onViewCreated:
//        04-13 18:06:51.194 3658-3658/org.faqrobot.textrecyclerview E/测试: 第1次数据获取完毕
//        04-13 18:06:51.194 3658-3658/org.faqrobot.textrecyclerview E/测试: onViewCreated:
//        04-13 18:06:51.194 3658-3658/org.faqrobot.textrecyclerview E/测试: onResume:
//        04-13 18:06:51.194 3658-3658/org.faqrobot.textrecyclerview E/测试: onResume:
//        04-13 18:06:51.194 3658-3658/org.faqrobot.textrecyclerview E/测试: onResume:
//        04-13 18:06:51.194 3658-3658/org.faqrobot.textrecyclerview E/测试: onResume:
//        04-13 18:06:51.194 3658-3658/org.faqrobot.textrecyclerview E/测试: onResume:
    }
}
