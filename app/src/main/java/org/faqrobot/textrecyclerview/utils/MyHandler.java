package org.faqrobot.textrecyclerview.utils;

import android.os.Handler;
import android.os.Message;

import org.faqrobot.textrecyclerview.config.MData;
import org.faqrobot.textrecyclerview.ui.fra.home.HomeInner1Fra;
import org.faqrobot.textrecyclerview.ui.fra.home.HomeInner2Fra;
import org.faqrobot.textrecyclerview.ui.fra.home.HomeInner3Fra;
import org.faqrobot.textrecyclerview.ui.fra.home.HomeInner4Fra;
import org.faqrobot.textrecyclerview.ui.fra.home.HomeInner5Fra;

import java.lang.ref.WeakReference;


/**
 * Created by 孟晨 on 2018/4/13.
 */

public class MyHandler extends Handler {

    public Object fragment;
    public WeakReference<Object> weakReference;

    public MyHandler(Object fragment, UpDateUi upDateUi) {
        judgeFra(fragment,upDateUi);
    }

    @Override
    public void handleMessage(Message msg) {
        getFra();
        super.handleMessage(msg);
        if (fragment != null) {
            switch (msg.what) {
                case MData.HANDLER_MESSAGE_WHAT:
                    if (upDateUi != null) {
                        upDateUi.upDateUi();
                    }
                    break;
                default:
                    break;
            }
        }
    }


    private void judgeFra(Object fragment, UpDateUi upDateUi){
        if(fragment instanceof HomeInner5Fra){
            weakReference = new WeakReference<Object>(fragment);
            MyHandler.this.upDateUi = upDateUi;
        }else if(fragment instanceof HomeInner1Fra){
            weakReference = new WeakReference<Object>(fragment);
            MyHandler.this.upDateUi = upDateUi;
        }else if(fragment instanceof HomeInner2Fra){
            weakReference = new WeakReference<Object>(fragment);
            MyHandler.this.upDateUi = upDateUi;
        }else if(fragment instanceof HomeInner3Fra){
            weakReference = new WeakReference<Object>(fragment);
            MyHandler.this.upDateUi = upDateUi;
        }else if(fragment instanceof HomeInner4Fra){
            weakReference = new WeakReference<Object>(fragment);
            MyHandler.this.upDateUi = upDateUi;
        }
    }

    private void getFra(){
        if(weakReference.get() instanceof HomeInner5Fra){
            fragment = (HomeInner5Fra)weakReference.get();
        }else if(weakReference.get() instanceof HomeInner1Fra){
            fragment = (HomeInner1Fra)weakReference.get();
        }else if(weakReference.get() instanceof HomeInner2Fra){
            fragment = (HomeInner2Fra)weakReference.get();
        }else if(weakReference.get() instanceof HomeInner3Fra){
            fragment = (HomeInner3Fra)weakReference.get();
        }else if(weakReference.get() instanceof HomeInner4Fra){
            fragment = (HomeInner4Fra)weakReference.get();
        }
    }

    public UpDateUi upDateUi;

    public interface UpDateUi {
        void upDateUi();
    }
}
