package org.faqrobot.textrecyclerview.utils;

import android.app.Activity;
import android.view.Display;

/**
 * Created by 孟晨 on 2018/4/9.
 */

public class WindowUtil {

    public static int getWindowHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        return display.getHeight();
    }

    public static int getWindowWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        return display.getWidth();
    }
}
