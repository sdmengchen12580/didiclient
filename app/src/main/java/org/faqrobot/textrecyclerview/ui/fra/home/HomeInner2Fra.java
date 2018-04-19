package org.faqrobot.textrecyclerview.ui.fra.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.config.MData;
import org.faqrobot.textrecyclerview.ui.basefra.RxBaseFragment;
import org.faqrobot.textrecyclerview.utils.MyHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeInner2Fra extends RxBaseFragment {

    private int page = 1;
    private boolean mIsRefreshing = false;
    private RecyclerView mRecyclerView;
    private MyHandler myHandler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private StaggeredGridLayoutManager mLayoutManager;

    public HomeInner2Fra() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text2, container, false);

        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_text2;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("测试", "onViewCreated: ");
    }





}
