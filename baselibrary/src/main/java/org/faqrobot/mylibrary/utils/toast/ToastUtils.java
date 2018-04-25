package org.faqrobot.mylibrary.utils.toast;

import android.widget.Toast;

import org.faqrobot.mylibrary.utils.other.AppUtils;


public class ToastUtils {

    public static void show(String text) {
        Toast.makeText(AppUtils.getAppContext(),text, Toast.LENGTH_SHORT).show();
    }
}
