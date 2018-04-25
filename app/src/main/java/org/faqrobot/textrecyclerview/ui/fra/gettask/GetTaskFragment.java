package org.faqrobot.textrecyclerview.ui.fra.gettask;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.callback.FraPagerAdapter;
import org.faqrobot.textrecyclerview.ui.act.HomeActivity;
import org.faqrobot.textrecyclerview.ui.fra.basefra.RxBaseFragment;
import org.faqrobot.textrecyclerview.view.CaterpillarIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetTaskFragment extends RxBaseFragment {

    private ViewPager mViewPager;
    private CaterpillarIndicator mCaterpillarIndicator;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((HomeActivity)getActivity()).hideToolbar();
    }

    public static GetTaskFragment getInstance(){
        return new GetTaskFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_get_task;
    }

    @Override
    public void initViews(View view) {
        mCaterpillarIndicator = (CaterpillarIndicator) view.findViewById(R.id.caterpillarIndicator);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager_gettaskfra);

        //标题
        List<CaterpillarIndicator.TitleInfo> titleInfos = new ArrayList<>();
        titleInfos.add(new CaterpillarIndicator.TitleInfo("关注"));
        titleInfos.add(new CaterpillarIndicator.TitleInfo("推荐"));
        titleInfos.add(new CaterpillarIndicator.TitleInfo("发现"));

        //启动页为第二个
        mCaterpillarIndicator.init(1, titleInfos, mViewPager);

        //填充fragment
        fragmentList.add(HomeAttentionFragment.newInstance());
        fragmentList.add(SquareFragmentNew.newInstance());
        fragmentList.add(DiscoverFragment.newInstance());

        //fragment嵌套fragment使用getChildFragmentManager()
        mViewPager.setAdapter(new FraPagerAdapter(getChildFragmentManager(),fragmentList));
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(2);
    }

}
