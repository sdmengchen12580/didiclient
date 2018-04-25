package org.faqrobot.mylibrary.utils;

/**
 * Created by 孟晨 on 2018/3/19.
 */

public class Mstring {

    //识别指定单个文字和2个字以上
    public static boolean judgeTxtContent(String result){
        return result.equals("恩") || result.equals("是") || result.equals("好") || result.equals("嗨")
                || Character.isDigit(result.charAt(0)) || result.contains("一") || result.contains("二")
                || result.contains("三") || result.contains("四") || result.contains("五") || result.contains("六")
                || result.contains("七") || result.contains("八") || result.contains("九") || result.contains("十");
    }
}
