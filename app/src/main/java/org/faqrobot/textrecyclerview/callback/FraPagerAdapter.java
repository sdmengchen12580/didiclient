package org.faqrobot.textrecyclerview.callback;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.ui.fra.SinglePhotoViewFragment;

import java.util.List;

/**
 * Created by 孟晨 on 2018/4/18.
 */

public class FraPagerAdapter extends FragmentPagerAdapter {

    private List<SinglePhotoViewFragment> list;

    public FraPagerAdapter(FragmentManager fm,List<SinglePhotoViewFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}

