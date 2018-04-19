package org.faqrobot.textrecyclerview.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 孟晨 on 2018/4/9.
 */

public class ViewUtil {

    public static void setViewSize(View view, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }

    public static void setMarginBottonUiPercent(View view, int marginBottomValue) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.bottomMargin = marginBottomValue;
        view.setLayoutParams(params);
    }
}
