package org.faqrobot.textrecyclerview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.github.chrisbanes.photoview.PhotoView;

import org.faqrobot.textrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孟晨 on 2018/4/18.
 */

public class PhotoViewAdapter extends PagerAdapter {

    private List<String> imgUrls;

    public PhotoViewAdapter() {
        super();
        imgUrls = new ArrayList<>();
    }

    public void addView(String imgUrl) {
        imgUrls.add(imgUrl);
    }



    // 删除页卡
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    // 添加页卡
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //内部布局解析
        View view = View.inflate(container.getContext(), R.layout.item_singleimg, null);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.photoview_singleimg);
//        Glide.with(container.getContext()).load(imgUrls.get(position))
//                .skipMemoryCache(true) // 不用内存缓存
//                .diskCacheStrategy(DiskCacheStrategy.NONE) // 不用磁盘缓存
//                .into(photoView);
        Glide.with(container.getContext())
                .load(imgUrls.get(position))
//                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.mipmap.ic_launcher)
                .into(photoView)
                .getSize((width, height) -> {
                    if (!view.isShown()) {
                        view.setVisibility(View.VISIBLE);
                    }
                });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return this.imgUrls == null ? 0 : imgUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
