package org.faqrobot.mylibrary.utils.string;

import org.faqrobot.mylibrary.utils.toast.ToastUtils;

import java.util.regex.Pattern;

import okhttp3.HttpUrl;


public class PatternUtils {

    public static boolean isHost(String url) {
        final Pattern sAddressPattern = Pattern.compile(
                "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-Z0-9\\.&amp;%\\$\\-]+)*@)?(" +
                        "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\." +
                        "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\." +
                        "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\." +
                        "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|" +
                        "([a-zA-Z0-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?" +
                        "(/[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&amp;%\\$#\\=~_\\-@]*)*$", Pattern
                        .CASE_INSENSITIVE);
        if (HttpUrl.parse(url) == null || sAddressPattern.matcher(url).matches()) {
            ToastUtils.show("请输入正确的域名");
            return false;
        }
        return true;
    }
}
