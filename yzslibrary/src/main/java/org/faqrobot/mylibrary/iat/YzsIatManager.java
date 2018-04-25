package org.faqrobot.mylibrary.iat;

import android.content.Context;
import android.util.Log;
import com.unisound.client.SpeechConstants;
import com.unisound.client.SpeechUnderstander;
import com.unisound.client.SpeechUnderstanderListener;
import org.faqrobot.mylibrary.config.YZSData;
import org.faqrobot.mylibrary.iat.mcallback.YzsIatInterface;
import org.faqrobot.mylibrary.utils.Mstring;
import org.faqrobot.mylibrary.utils.logger.Logger;
import org.faqrobot.mylibrary.utils.logger.ObjectUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by 孟晨 on 2018/3/15.
 */

public class YzsIatManager {

    private int volume;
    private String recogWords;
    private static Context context;
    private SpeechUnderstander mUnderstander=null;
    private YzsIatInterface yzsIatInterface;
    private static volatile YzsIatManager instance = null;
    //对应的采样率和说话的语种
    private static int arraySample[] = new int[]{SpeechConstants.ASR_SAMPLING_RATE_BANDWIDTH_AUTO,
            SpeechConstants.ASR_SAMPLING_RATE_16K, SpeechConstants.ASR_SAMPLING_RATE_8K};
    private static String arrayLanguageStr[] = new String[]{SpeechConstants.LANGUAGE_MANDARIN,
            SpeechConstants.LANGUAGE_ENGLISH, SpeechConstants.LANGUAGE_CANTONESE};

    public static YzsIatManager getInstance(Context context) {
        if (instance == null) {
            synchronized (YzsIatManager.class) {
                if (instance == null) {
                    instance = new YzsIatManager(context);
                }
            }
        }
        return instance;
    }

    private YzsIatManager(Context context) {
        this.context = context;
    }

    public void setIatCallback(YzsIatInterface yzsIatInterface) {
        this.yzsIatInterface = yzsIatInterface;
    }

    public void initRecognizer() {
        //创建语音识别对象，appKey和 secret通过 http://dev.hivoice.cn/ 网站申请
        mUnderstander = new SpeechUnderstander(context, YZSData.YZS_APPKEY, YZSData.YZS_APPSECRET);
        //开启可变结果
        mUnderstander.setOption(SpeechConstants.ASR_OPT_TEMP_RESULT_ENABLE, true);
        //设置语义场景
        mUnderstander.setOption(SpeechConstants.NLU_SCENARIO, "videoDefault");
        //收到 onRecognizerStart 回调前，录音设备没有打开，请添加界面等待提示
        mUnderstander.setOption(SpeechConstants.ASR_SAMPLING_RATE, arraySample[0]);
        mUnderstander.setOption(SpeechConstants.ASR_LANGUAGE, arrayLanguageStr[0]);
        //保存录音数据——数据保存到哪里？
        //recognizer.setRecordingDataEnable(true);
        mUnderstander.setListener(new SpeechUnderstanderListener() {
            @Override
            public void onResult(int type, String jsonResult) {
                switch (type) {
                    case SpeechConstants.ASR_RESULT_NET:
                        // TODO: 2017/10/26  在线识别结果，通常onResult接口多次返回结果，保留识别结果组成完整的识别内容。
                        if (jsonResult.contains("net_asr")
                                && jsonResult.contains("net_nlu")) {
                            try {
                                JSONObject json = new JSONObject(jsonResult);
                                JSONArray jsonArray = json.getJSONArray("net_asr");
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String status = jsonObject.getString("result_type");
                                if (status.equals("full")) {
                                    recogWords = (String) jsonObject.get("recognition_result");
                                    //大于两字
                                    if (jsonResult != null && recogWords.length() > 2) {
                                        yzsIatInterface.recogWord(recogWords.toString().trim());
                                    }
                                    //长度为2个字-非指定文字就重启聆听
                                    else if (recogWords.length() == 2) {
                                        if (Mstring.judgeTxtContent(recogWords)) {
                                            yzsIatInterface.recogWord(recogWords.toString().trim().substring(0, 1));
                                        } else {
                                            yzsIatInterface.personSpeckAndResetTime();
                                            reStartIat();
                                        }
                                    }
                                    //用户没说话-重启聆听
                                    else if (ObjectUtils.isNull(recogWords)) {
                                        reStartIat();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Logger.e("识别出错，重启聆听");
                                reStartIat();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onEvent(int type, int timeMs) {
                switch (type) {
                    //1.用户可以说话了——当前打开录音设备
                    case SpeechConstants.ASR_EVENT_RECORDING_START:
                        Log.e("onEvent: ", "录音设备打开");
                        break;
                    //2.说话开始
                    case SpeechConstants.ASR_EVENT_SPEECH_DETECTED:
                        Log.e("onEvent: ", "用户开始说话");
                        break;
                    //3.音量的改变
                    case SpeechConstants.ASR_EVENT_VOLUMECHANGE:
                        // 说话音量实时返回
                        volume = 0;
                        if (mUnderstander != null) {
                            volume = (Integer) mUnderstander.getOption(SpeechConstants.GENERAL_UPDATE_VOLUME);
                        }
                        if (volume > 50) {
                            yzsIatInterface.personSpeckAndResetTime();
                        }
                        if (volume < 100) {
                            yzsIatInterface.volumeChange(volume);
                        }
                        break;
                    //4.识别完
                    case SpeechConstants.ASR_EVENT_NET_END:
                        if (ObjectUtils.isNull(recogWords) || recogWords.length() == 2) {
                            recogWords = null;
                            reStartIat();
                        } else if (!ObjectUtils.isNull(recogWords)) {
                            recogWords = null;
                        }
                        break;
                    //5.超时未说话
                    case SpeechConstants.ASR_EVENT_VAD_TIMEOUT:
                        Log.e("onEvent", "超时未说话");
                        break;
                    //6.录音停止
                    case SpeechConstants.ASR_EVENT_RECORDING_STOP:
                        Log.e("onEvent", "录音停止-解析用户言语");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int type, String errorMSG) {
                Log.e("语音识别异常转聆听：", errorMSG.toString());
                reStartIat();
            }
        });
        mUnderstander.init("");
    }

    //开启聆听
    public void reStartIat() {
        if (mUnderstander != null) {
            mUnderstander.start();
        }
    }

    //关闭聆听
    public void stopListenering() {
        if (mUnderstander != null) {
            mUnderstander.cancel();
        }
    }

    //销毁
    public void releaseIat() {
        if (mUnderstander != null) {
            mUnderstander.stop();
            mUnderstander.cancel();
            mUnderstander = null;
        }
    }
}
