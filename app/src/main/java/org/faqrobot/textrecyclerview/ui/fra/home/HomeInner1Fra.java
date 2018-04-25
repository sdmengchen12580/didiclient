package org.faqrobot.textrecyclerview.ui.fra.home;


import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.adapter.HomeFra1Adapter;
import org.faqrobot.textrecyclerview.config.MData;
import org.faqrobot.textrecyclerview.entity.ImgInfoBean;
import org.faqrobot.textrecyclerview.ui.fra.basefra.RxBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class HomeInner1Fra extends RxBaseFragment {

    private int page = 1;
    private RecyclerView mRecyclerView;
    private boolean mIsRefreshing = false;
    private HomeFra1Adapter homeFra1Adapter = null;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private StaggeredGridLayoutManager mLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_text1;
    }

    @Override
    public void initViews(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh1);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //不设置的话，图片闪烁错位，有可能有整列错位的情况。
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(OnLoadMoreListener(mLayoutManager));


        initFirstRefresh();
    }

    private void initFirstRefresh() {
        Log.e("测试: ", "homeInner1Fra初始刷新啦");
        mIsRefreshing = true;
        mSwipeRefreshLayout.setRefreshing(true);
        getData(page, false);


        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            Log.e("小几把: ", "开启刷新");
            if (mIsRefreshing) {
                Log.e("测试: ", "在刷新，不去请求数据");
                return;
            }
            mIsRefreshing = true;
            page += 1;
            page = (page == 5) ? 1 : page;
            getData(page, false);
        });
    }

    private void getData(int page, boolean isBottom) {
        switch (page) {
            case 1:
                List<ImgInfoBean> listImgsUrl1 = new ArrayList<>();
                for (int i = 1; i < 11; i++) {
                    ImgInfoBean imgInfoBean = new ImgInfoBean((MData.IMGURL + i + ".jpg").trim(),"");
                    listImgsUrl1.add(imgInfoBean);
                }
                finishRefresh(listImgsUrl1,isBottom);
                break;
            case 2:
                List<ImgInfoBean> listImgsUrl2 = new ArrayList<>();
                for (int i = 11; i < 21; i++) {
                    ImgInfoBean imgInfoBean = new ImgInfoBean((MData.IMGURL + i + ".jpg").trim(),"");
                    listImgsUrl2.add(imgInfoBean);
                }
                finishRefresh(listImgsUrl2,isBottom);
                break;
            case 3:
                List<ImgInfoBean> listImgsUrl3 = new ArrayList<>();
                for (int i = 21; i < 31; i++) {
                    ImgInfoBean imgInfoBean = new ImgInfoBean((MData.IMGURL + i + ".jpg").trim(),"");
                    listImgsUrl3.add(imgInfoBean);
                }
                finishRefresh(listImgsUrl3,isBottom);
                break;
            case 4:
                List<ImgInfoBean> listImgsUrl4 = new ArrayList<>();
                for (int i = 31; i < 40; i++) {
                    ImgInfoBean imgInfoBean = new ImgInfoBean((MData.IMGURL + i + ".jpg").trim(),"");
                    listImgsUrl4.add(imgInfoBean);
                }
                finishRefresh(listImgsUrl4,isBottom);
                break;
            default:
                break;
        }
    }

    private void finishRefresh(List<ImgInfoBean> list,boolean isBottom) {
        if (isBottom) {
            homeFra1Adapter.addBottomData(list);
            mIsRefreshing = false;
            return;
        }
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mIsRefreshing = false;
        if (null == homeFra1Adapter) {
            homeFra1Adapter = new HomeFra1Adapter(getContext(), list);
            mRecyclerView.setAdapter(homeFra1Adapter);
            return;
        }
        homeFra1Adapter.refreshData(list);
    }

    private RecyclerView.OnScrollListener OnLoadMoreListener(StaggeredGridLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPositions(new int[2])
                        [1] >= homeFra1Adapter.getItemCount() - 2;
                if (newState ==RecyclerView.SCROLL_STATE_IDLE&&isBottom && !mIsRefreshing) {
                    new Handler().postDelayed(() -> {
                        mIsRefreshing = true;
                        page += 1;
                        page = (page == 5) ? 1 : page;
                        getData(page, true);
                    },100);
                }
            }
        };
    }
}
