package org.faqrobot.mylibrary.tts;

import android.content.Context;
import android.util.Log;
import com.unisound.client.SpeechConstants;
import com.unisound.client.SpeechSynthesizer;
import com.unisound.client.SpeechSynthesizerListener;
import org.faqrobot.mylibrary.config.YZSData;
import org.faqrobot.mylibrary.tts.mcallback.YzsTtsInterface;

/**
 * Created by 孟晨 on 2018/3/20.
 */

public class YzsTtsManager {

    private static Context context;
    private SpeechSynthesizer mTTSPlayer = null;
    private YzsTtsInterface yzsTtsInterface;
    private static volatile YzsTtsManager instance = null;

    public static YzsTtsManager getInstance(Context context) {
        if (instance == null) {
            synchronized (YzsTtsManager.class) {
                if (instance == null) {
                    instance = new YzsTtsManager(context);
                }
            }
        }
        return instance;
    }

    private YzsTtsManager(Context context) {
        this.context = context;
    }

    public void setIatCallback(YzsTtsInterface yzsTtsInterface) {
        this.yzsTtsInterface = yzsTtsInterface;
    }

    public void init(String helloWord) {
        /**创建语音合成（合成就是播报）对象*/
        mTTSPlayer = new SpeechSynthesizer(context, YZSData.YZS_APPKEY, YZSData.YZS_APPSECRET);
        mTTSPlayer.setOption(SpeechConstants.TTS_SERVICE_MODE, SpeechConstants.TTS_SERVICE_MODE_NET);
        /**志玲*/
//        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_NAME, "lzl");
        /**大点女生*/
//        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_NAME, "xiaoli");
        /**小女孩*/
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_NAME, "tangtang");
        /**小男孩*/
//        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_NAME, "boy");
        /**唯美*/
//        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_NAME, "sweet");
        /**男生*/
//        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_NAME, "xiaoming");
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_SPEED, 74);
        /**设置语音合成回调监听*/
        mTTSPlayer.setTTSListener(new SpeechSynthesizerListener() {
            @Override
            public void onEvent(int type) {
                switch (type) {
                    case SpeechConstants.TTS_EVENT_INIT:
                        // 初始化成功回调
                        break;
                    case SpeechConstants.TTS_EVENT_SYNTHESIZER_START:
                        // 开始合成回调
                        break;
                    case SpeechConstants.TTS_EVENT_SYNTHESIZER_END:
                        // 合成结束回调
                        break;
                    case SpeechConstants.TTS_EVENT_BUFFER_BEGIN:
                        // 开始缓存回调
                        break;
                    case SpeechConstants.TTS_EVENT_BUFFER_READY:
                        // 缓存完毕回调
                        break;
                    case SpeechConstants.TTS_EVENT_PLAYING_START:
                        // 开始播放回调
                        break;
                    case SpeechConstants.TTS_EVENT_PLAYING_END:
                        Log.e("onEvent", "播报完成");
                        if (yzsTtsInterface != null) {
                            yzsTtsInterface.speckEnd();
                        }
                        // 播放完成回调
                        break;
                    case SpeechConstants.TTS_EVENT_PAUSE:
                        // 暂停回调
                        break;
                    case SpeechConstants.TTS_EVENT_RESUME:
                        // 恢复回调
                        break;
                    case SpeechConstants.TTS_EVENT_STOP:
                        // 停止回调
                        break;
                    case SpeechConstants.TTS_EVENT_RELEASE:
                        // 释放资源回调
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int type, String errorMSG) {
                Log.e("onError", "播报错误：" + errorMSG.toString());
                if (yzsTtsInterface != null) {
                    yzsTtsInterface.speckErrorAndStartIat();
                }
            }
        });
        mTTSPlayer.init("");
        mTTSPlayer.playText(helloWord);
    }


    public void initSpeckWord(String words) {
        if (mTTSPlayer.isPlaying() && mTTSPlayer != null) {
            mTTSPlayer.stop();
        }
        mTTSPlayer.playText(words);
    }

    public void stopSpeck() {
        if (mTTSPlayer != null&&mTTSPlayer.isPlaying()) {
            mTTSPlayer.stop();
        }
    }

    public void releaseTts() {
        if (mTTSPlayer != null) {
            mTTSPlayer.stop();
            mTTSPlayer.cancel();
            mTTSPlayer = null;
        }
    }

    public boolean isPlaying() {
        if (mTTSPlayer != null) {
            return mTTSPlayer.isPlaying();
        }
        return false;
    }
}
