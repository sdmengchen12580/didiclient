package org.faqrobot.mylibrary.iat.mcallback;

/**
 * Created by 孟晨 on 2018/3/15.
 */

public interface YzsIatInterface {
    //识别到的话
    void recogWord(String word);

    //说话音量
    void volumeChange(int volume);

    //重启计时
    void personSpeckAndResetTime();
}
