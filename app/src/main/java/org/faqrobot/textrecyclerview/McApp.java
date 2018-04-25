package org.faqrobot.textrecyclerview;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;

import com.vise.log.ViseLog;
import com.vise.log.inner.LogcatTree;

import org.faqrobot.mylibrary.utils.other.AppUtils;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by 孟晨 on 2018/4/12.
 */

public class McApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);

        ViseLog.getLogConfig()
                .configAllowLog(true)//是否输出日志
                .configShowBorders(true)//是否排版显示
                .configTagPrefix("ViseLog")//设置标签前缀
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")//个性化设置标签，默认显示包名
                .configLevel(Log.VERBOSE);//设置日志最小输出级别，默认Log.VERBOSE
        ViseLog.plant(new LogcatTree());//添加打印日志信息到Logcat的树

        initTextType();
    }

    private void initTextType(){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/fzltingsimp.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set(null, typeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
