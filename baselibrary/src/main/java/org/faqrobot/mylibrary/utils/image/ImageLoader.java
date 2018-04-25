package org.faqrobot.mylibrary.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.faqrobot.mylibrary.utils.other.AppUtils;


public class ImageLoader {

    /**
     * 加载图片
     */
    public static void load(String path, ImageView imageView) {
        Glide.with(AppUtils.getAppContext())
                .load(path)
                .into(imageView);
    }

    /**
     * 跳过缓存加载图片
     */
    public static void loadSkipCache(Object path, ImageView imageView) {
        Glide.with(AppUtils.getAppContext())
                .load(path)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    /**
     * 缓存载图片
     */
    public static void loadCacheImageView(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
}
