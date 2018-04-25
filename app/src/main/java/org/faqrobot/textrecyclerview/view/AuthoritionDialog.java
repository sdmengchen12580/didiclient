package org.faqrobot.textrecyclerview.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import com.vise.log.ViseLog;
import com.wang.avi.AVLoadingIndicatorView;

import org.faqrobot.mylibrary.http.callback.HttpListener;
import org.faqrobot.mylibrary.utils.logger.ObjectUtils;
import org.faqrobot.mylibrary.utils.toast.ToastUtils;
import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.config.MData;
import org.faqrobot.textrecyclerview.http.api.FaqRobotLoader;
import org.faqrobot.textrecyclerview.http.bean.EmpowerBean;
import org.faqrobot.textrecyclerview.utils.AuthoritionUtils;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by 孟晨 on 2018/4/20.
 */

public class AuthoritionDialog extends AlertDialog {

    public interface AuthoritionCallback {
        void closeLoadingAnim();
    }

    private AuthoritionCallback authoritionCallback;

    private void setCallback(AuthoritionCallback authoritionCallback) {
        this.authoritionCallback = authoritionCallback;
    }

    public AuthoritionDialog(@NonNull Context context) {
        super(context);
    }

    public AuthoritionDialog(@NonNull Context context, @StyleRes int themeResId, AuthoritionCallback authoritionCallback) {
        super(context, themeResId);
        setCallback(authoritionCallback);
    }

    private TextView tvShowAuthoritionInfo,tvExitAuthoriton;
    private AVLoadingIndicatorView avLoadingIndicatorView;
    private HashMap<String, Object> mAuthoritionMap = new HashMap<>();
    private Subscription subAuthorition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_authorition_layout);
        initView();
        getAuthoritionMaps();
    }

    private void initView() {
        tvShowAuthoritionInfo = (TextView) findViewById(R.id.tv_authorition);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.load_animal);
        tvExitAuthoriton = (TextView) findViewById(R.id.jiezhebofang);
        tvExitAuthoriton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ObjectUtils.isNull(tvExitAuthoriton.getText().toString())){
                    subAuthorition.unsubscribe();
                    subAuthorition = null;
                }
                authoritionCallback.closeLoadingAnim();
            }
        });
    }





    //------------------------------------------------授权----------------------------------------------
    private void getAuthoritionMaps() {
        long timestamp = System.currentTimeMillis();
        String token = AuthoritionUtils.encryptDesBase62(timestamp,AuthoritionUtils.getUniqueDeviceCode());
        mAuthoritionMap.put("s", "ss");
        mAuthoritionMap.put("clientId",AuthoritionUtils.getUniqueDeviceCode());
        mAuthoritionMap.put("token",token);
        mAuthoritionMap.put("timestamp",timestamp);
        mAuthoritionMap.put("sourceId",MData.SOURCEID);
        mAuthoritionMap.put("original",false);
        mAuthoritionMap.put("version","1.0");
        mAuthoritionMap.put("purpose",1);
        mAuthoritionMap.put("width",100);
        mAuthoritionMap.put("height",200);
        mAuthoritionMap.put("deviceType",0);
        mAuthoritionMap.put("latitude",100);
        mAuthoritionMap.put("longitude",200);
        mAuthoritionMap.put("sysNum",MData.ZHANGDIAN);

        anthorition(mAuthoritionMap);
    }

    private void anthorition(HashMap<String, Object> maps) {
        HttpListener httpListener = new HttpListener<EmpowerBean>() {
            @Override
            public void onError(String errorMsg) {
                ToastUtils.show(errorMsg);
//                authoritionCallback.closeLoadingAnim();
            }

            @Override
            public void onSuccess(EmpowerBean empowerBean) {
                ViseLog.e("授权信息："+empowerBean.getMessage());
                tvShowAuthoritionInfo.setText(empowerBean.getStatus());
            }
        };
        FaqRobotLoader faqrobot = new FaqRobotLoader(MData.HOSTNAME);
        subAuthorition=faqrobot.getAuthoritionInfo(maps, httpListener);
    }
}
