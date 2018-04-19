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
import org.faqrobot.textrecyclerview.utils.MyHandler;

import java.util.ArrayList;
import java.util.List;


public class HomeInner5Fra extends Fragment {

    private int page = 1;
    private boolean mIsRefreshing = false;
    private RecyclerView mRecyclerView;
    private MyHandler myHandler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private StaggeredGridLayoutManager mLayoutManager;

    public HomeInner5Fra() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        initHandler();
        initFirstRefresh(view);
//        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("测试", "onViewCreated: ");
    }

    private void initHandler() {
        myHandler = new MyHandler(HomeInner5Fra.this, new MyHandler.UpDateUi() {
            @Override
            public void upDateUi() {
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                mIsRefreshing = false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("测试", "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("测试", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("测试", "onStop: ");
    }

    private void initFirstRefresh(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        Log.e("测试","homeInner5Fra初始刷新啦" );
        mIsRefreshing = true;
        mSwipeRefreshLayout.setRefreshing(true);
        getData(page);


        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            Log.e("测试: ","走了刷新监听" );
            if (mIsRefreshing) {
                return;
            }
            mIsRefreshing = true;
            page += 1;
            page = (page == 5) ? 1 : page;
            getData(page);
        });
    }

    private void getData(int page) {
        switch (page) {
            case 1:
                List<String> list1 = new ArrayList<>();
                for (int i = 1; i < 11; i++) {
                    list1.add((MData.IMGURL + i + ".jpg").trim());
                }
                finishRefresh();
                break;
            case 2:
                List<String> list2 = new ArrayList<>();
                for (int i = 11; i < 21; i++) {
                    list2.add((MData.IMGURL + i + ".jpg").trim());
                }
                finishRefresh();
                break;
            case 3:
                List<String> list3 = new ArrayList<>();
                for (int i = 21; i < 31; i++) {
                    list3.add((MData.IMGURL + i + ".jpg").trim());
                }
                finishRefresh();
                break;
            case 4:
                List<String> list4 = new ArrayList<>();
                for (int i = 31; i < 41; i++) {
                    list4.add((MData.IMGURL + i + ".jpg").trim());
                }
                finishRefresh();
                break;
            default:
                break;
        }
    }

    private void finishRefresh() {
        Log.e("测试", "第" + page + "次数据获取完毕");
        myHandler.sendEmptyMessageDelayed(MData.HANDLER_MESSAGE_WHAT, 1000);
    }

//    private void initView(View view) {
//
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.addOnScrollListener(OnLoadMoreListener(mLayoutManager));
//
//        mAdapter = new HuaBanMeiziAdapter(mRecyclerView, meiziInfos);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setOnTouchListener((v, event) -> mIsRefreshing);
//    }
//
//
//    RecyclerView.OnScrollListener OnLoadMoreListener(StaggeredGridLayoutManager layoutManager) {
//        return new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(RecyclerView rv, int dx, int dy) {
//                boolean isBottom = mLayoutManager.findLastCompletelyVisibleItemPositions(
//                        new int[2])[1] >= mAdapter.getItemCount() - PRELOAD_SIZE;
//                if (!mSwipeRefreshLayout.isRefreshing() && isBottom) {
//                    if (!mIsLoadMore) {
//                        mSwipeRefreshLayout.setRefreshing(true);
//                        page++;
//                        getHuaBanMeizi();
//                    } else {
//                        mIsLoadMore = false;
//                    }
//                }
//            }
//        };
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("测试 ", "homeInner5Fra销毁视图");
    }
}
