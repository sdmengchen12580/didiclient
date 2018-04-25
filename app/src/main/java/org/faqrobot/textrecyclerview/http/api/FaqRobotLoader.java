package org.faqrobot.textrecyclerview.http.api;

import android.app.Activity;

import com.trello.rxlifecycle.RxLifecycle;

import org.faqrobot.mylibrary.http.RetrofitManager;
import org.faqrobot.mylibrary.http.callback.HttpListener;
import org.faqrobot.textrecyclerview.ui.act.baseact.RxBaseActivity;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yunwen on 2017/12/28 0028.
 */

public class FaqRobotLoader {

    private FaqRobotApi faqRobotApi;
    private Subscription subRobotChat;
    private Subscription subAuthorition;

    public FaqRobotLoader(String baseUrl) {
        faqRobotApi = RetrofitManager.getFaqRobotApi(FaqRobotApi.class, baseUrl);
    }

    public Subscription robotChat(String face_token,
                                  String s,
                                  String token,
                                  long timestamp,
                                  String clientId,
                                  String sysNum,
                                  String sourceId,
                                  String userId,
                                  String question,
                                  boolean original,
                                  RxBaseActivity activity,
                                  HttpListener httpListener) {
        subRobotChat = faqRobotApi.robotChat(face_token, s, token, timestamp, clientId, sysNum,
                sourceId, userId, question, original)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpListener);
        return subRobotChat;
    }

    public void getAccessToken(String appId, String secret,
                               RxBaseActivity activity,
                               HttpListener httpListener) {
        faqRobotApi.getAccessToken(appId, secret)
                .compose(activity.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpListener);
    }

//    public void emPower(String s,
//                        String client,
//                        String token,
//                        long timestamp,
//                        String sourceId,
//                        String original,
//                        String version,
//                        int purpose,
//                        int width,
//                        int height,
//                        int deviceType,
//                        int latitude,
//                        int longitude,
//                        String sysNum,
//                        RxBaseActivity activity,
//                        HttpListener httpListener){
//        faqRobotApi.emPower(s,client,token,timestamp,sourceId,original,version,
//                purpose,width,height,deviceType,latitude,longitude,sysNum)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(httpListener);
//    }

    public Subscription getAuthoritionInfo(HashMap<String, Object> maps, HttpListener httpListener) {
        subAuthorition=faqRobotApi.getEmpowerResult(maps)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpListener);
        return subAuthorition;
    }

}
