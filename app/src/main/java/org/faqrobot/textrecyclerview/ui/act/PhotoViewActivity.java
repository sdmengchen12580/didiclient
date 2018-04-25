package org.faqrobot.textrecyclerview.ui.act;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import java.util.List;
import android.widget.TextView;
import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.adapter.PhotoViewAdapter;
import org.faqrobot.textrecyclerview.callback.PagerChangeCallback;
import org.faqrobot.textrecyclerview.entity.ImgInfoBean;
import org.faqrobot.textrecyclerview.ui.act.baseact.RxBaseActivity;

public class PhotoViewActivity extends RxBaseActivity {

    private int imgsSize;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private int currentPosition;
    private PhotoViewAdapter photoViewAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo_view;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        getDataAndPositon();
    }


    private void getDataAndPositon() {
        List<ImgInfoBean> imgsList = getIntent().getParcelableArrayListExtra("persons");
        imgsSize = imgsList.size();
        currentPosition = getIntent().getIntExtra("position", 0);
        setToolbarTitle((currentPosition + 1) + "/" + imgsSize);
        if (imgsList.size() > 0 && imgsList != null) {
            photoViewAdapter = new PhotoViewAdapter();
            for (int i = 0; i < imgsList.size(); i++) {
                Log.e("存入数据: ", imgsList.get(i).getImgUrl());
                photoViewAdapter.addView(imgsList.get(i).getImgUrl());
            }
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        findViewById(R.id.loadtextview).setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
        viewPager.setAdapter(photoViewAdapter);
        PagerChangeCallback pagerChangeCallback = new PagerChangeCallback() {
            @Override
            public void pageSelected(int position) {
                setToolbarTitle((position + 1) + "/" + imgsSize);
            }
        };
        viewPager.addOnPageChangeListener(pagerChangeCallback);
        viewPager.setCurrentItem(currentPosition);
        pagerChangeCallback.onPageSelected(currentPosition);
    }


    @Override
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_photoact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setToolbarTitle(String toolbarTitle) {
        getSupportActionBar().setTitle(toolbarTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

}
