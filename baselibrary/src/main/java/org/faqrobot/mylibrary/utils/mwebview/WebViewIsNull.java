package org.faqrobot.mylibrary.utils.mwebview;

import android.webkit.WebView;

/**
 * Created by 孟晨 on 2018/3/19.
 */

public class WebViewIsNull {
    public static void judgeWebIsNull(WebView webView) {
        if (webView != null) {
            webView.reload();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }
}
