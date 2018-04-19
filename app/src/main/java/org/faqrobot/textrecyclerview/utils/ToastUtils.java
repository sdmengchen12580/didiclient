package org.faqrobot.textrecyclerview.utils;

import android.widget.Toast;
import org.faqrobot.textrecyclerview.utils.AppUtils;


public class ToastUtils {

    public static void show(String text) {
        Toast.makeText(AppUtils.getAppContext(),text, Toast.LENGTH_SHORT).show();
    }
}
