package org.faqrobot.textrecyclerview;

import android.app.Application;

import org.faqrobot.textrecyclerview.utils.AppUtils;

/**
 * Created by 孟晨 on 2018/4/12.
 */

public class McApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
    }
}
