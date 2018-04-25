package org.faqrobot.mylibrary.tts.mcallback;

/**
 * Created by 孟晨 on 2018/3/20.
 */

public interface YzsTtsInterface {
    //播报结束
    void speckEnd();

    //播报错误-重启聆听
    void speckErrorAndStartIat();

}
