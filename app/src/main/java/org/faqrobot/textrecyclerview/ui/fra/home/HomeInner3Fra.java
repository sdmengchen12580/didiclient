package org.faqrobot.textrecyclerview.ui.fra.home;


import android.graphics.Rect;
import android.hardware.Sensor;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.adapter.HomeFra3Adapter;
import org.faqrobot.textrecyclerview.entity.VideoBean;
import org.faqrobot.textrecyclerview.ui.fra.basefra.RxBaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.hardware.SensorManager;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer.JCAutoFullscreenListener;


public class HomeInner3Fra extends RxBaseFragment {

    private int page = 1;
    private RecyclerView mRecyclerView;
    private HomeFra3Adapter homeFra3Adapter;
    private SensorManager sensorManager;
    private boolean mIsRefreshing = false;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private JCAutoFullscreenListener sensorEventListener;
    private String titles[] = {"helloWord", "helloCity", "helloGirl", "helloBoy", "helloEveryOne"};
    private String imgUrls[] = {
            "http://ovuwadagy.bkt.clouddn.com/homeact_img1.jpg",
            "http://ovuwadagy.bkt.clouddn.com/homeact_img2.jpg",
            "http://ovuwadagy.bkt.clouddn.com/homeact_img3.jpg",
            "http://ovuwadagy.bkt.clouddn.com/homeact_img4.jpg",
            "http://ovuwadagy.bkt.clouddn.com/homeact_img5.jpg"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_text3;
    }

    @Override
    public void initViews(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh3);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        sensorManager = (SensorManager) getActivity().getSystemService("sensor");
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle3);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(OnLoadMoreListener(linearLayoutManager));


        initFirstRefresh();
    }

    public boolean judgeIsVideoPlay() {
        Rect rect = new Rect();
        homeFra3Adapter.jcVideoPlayer.getLocalVisibleRect(rect);
        return rect.top == 0;
    }

    public void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

    private void initFirstRefresh() {
        Log.e("测试: ", "homeInner3Fra初始刷新啦");
        mIsRefreshing = true;
        mSwipeRefreshLayout.setRefreshing(true);
        getData(page, false);


        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            if (mIsRefreshing) {
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
                List<VideoBean> list1 = new ArrayList<>();
                for (int i = 1; i < 11; i++) {
                    VideoBean videoBean1 = new VideoBean(
                            "http://ovuwadagy.bkt.clouddn.com/48001513326577597.mp4",
                            titles[new Random().nextInt(4)],
                            imgUrls[new Random().nextInt(4)]);

                    list1.add(videoBean1);
                }
                finishRefresh(list1, isBottom);
                break;
            case 2:
                List<VideoBean> list2 = new ArrayList<>();
                for (int i = 11; i < 21; i++) {
                    VideoBean videoBean2 = new VideoBean(
                            "http://ovuwadagy.bkt.clouddn.com/48001513326577597.mp4",
                            titles[new Random().nextInt(4)],
                            imgUrls[new Random().nextInt(4)]);

                    list2.add(videoBean2);
                }
                finishRefresh(list2, isBottom);
                break;
            case 3:
                List<VideoBean> list3 = new ArrayList<>();
                for (int i = 21; i < 31; i++) {
                    VideoBean videoBean3 = new VideoBean(
                            "http://ovuwadagy.bkt.clouddn.com/48001513326577597.mp4",
                            titles[new Random().nextInt(4)],
                            imgUrls[new Random().nextInt(4)]);

                    list3.add(videoBean3);
                }
                finishRefresh(list3, isBottom);
                break;
            case 4:
                List<VideoBean> list4 = new ArrayList<>();
                for (int i = 31; i < 41; i++) {
                    VideoBean videoBean4 = new VideoBean(
                            "http://ovuwadagy.bkt.clouddn.com/48001513326577597.mp4",
                            titles[new Random().nextInt(4)],
                            imgUrls[new Random().nextInt(4)]);

                    list4.add(videoBean4);
                }
                finishRefresh(list4, isBottom);
                break;
            default:
                break;
        }
    }

    private void finishRefresh(List<VideoBean> mList, boolean isBottom) {
        if (isBottom) {
            homeFra3Adapter.addBottomData(mList);
            mIsRefreshing = false;
            return;
        }
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mIsRefreshing = false;
        if (homeFra3Adapter == null) {
            homeFra3Adapter = new HomeFra3Adapter(getContext(), mList);
            mRecyclerView.setAdapter(this.homeFra3Adapter);
            return;
        }
        homeFra3Adapter.refreshData(mList);
    }

    private RecyclerView.OnScrollListener OnLoadMoreListener(LinearLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                boolean isBottom = layoutManager.findLastVisibleItemPosition()
                        >= homeFra3Adapter.getItemCount() - 1;
                if (newState == RecyclerView.SCROLL_STATE_IDLE && isBottom && !mIsRefreshing) {
                    new Handler().postDelayed(() -> {
                        mIsRefreshing = true;
                        page += 1;
                        page = (page == 5) ? 1 : page;
                        getData(page, true);
                    }, 100);
                }
            }
        };
    }
}
