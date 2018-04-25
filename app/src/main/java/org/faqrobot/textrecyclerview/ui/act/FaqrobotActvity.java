package org.faqrobot.textrecyclerview.ui.act;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;

import org.faqrobot.mylibrary.http.callback.HttpListener;
import org.faqrobot.mylibrary.iat.YzsIatManager;
import org.faqrobot.mylibrary.iat.mcallback.YzsIatInterface;
import org.faqrobot.mylibrary.tts.YzsTtsManager;
import org.faqrobot.mylibrary.tts.mcallback.YzsTtsInterface;
import org.faqrobot.mylibrary.utils.logger.ObjectUtils;
import org.faqrobot.mylibrary.utils.sp.SPUtil;
import org.faqrobot.mylibrary.utils.toast.ToastUtils;
import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.config.MData;
import org.faqrobot.textrecyclerview.http.api.FaqRobotLoader;
import org.faqrobot.textrecyclerview.http.bean.GetRobotResultV4;
import org.faqrobot.textrecyclerview.http.bean.RobotReplyV4;
import org.faqrobot.textrecyclerview.ui.act.baseact.RxBaseActivity;
import org.faqrobot.textrecyclerview.utils.AuthoritionUtils;
import org.faqrobot.textrecyclerview.view.AuthoritionDialog;
import org.faqrobot.textrecyclerview.view.SpecialGiftSurfaceView;

import java.lang.ref.WeakReference;


// TODO: 2018/4/19  基类加入allMvpCallback()抽样方法
// TODO: 2018/4/20 ctrl+shift+r 全局替换
public class FaqrobotActvity extends RxBaseActivity implements View.OnClickListener {

    public String showWords;
    public SoundPool soundPool;
    private MyHandler myHandler;
    private TextView tvShowWords;
    public int soundId_iat, soundId_tts;
    private boolean backToWindow = false;
    private int statue = MData.STATE_SPECK;
    private AuthoritionDialog authoritionDialog;
    private SpecialGiftSurfaceView specialGiftSurfaceView;
    private int speckImgs[] = {R.drawable.bobao1, R.drawable.bobao2, R.drawable.bobao3};
    private int listenerImgs[] = {R.drawable.lingting1, R.drawable.lingting2, R.drawable.lingting3};

    @Override
    public int getLayoutId() {
        return R.layout.activity_faqrobot_actvity;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //handler
        myHandler = new MyHandler(FaqrobotActvity.this);

        // 设置右滑动返回
        Slidr.attach(this);

        //控件
        tvShowWords = (TextView) findViewById(R.id.tv_webkack);

        //声音
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId_iat = soundPool.load(FaqrobotActvity.this, R.raw.shibie, 1);
        soundId_tts = soundPool.load(FaqrobotActvity.this, R.raw.bobao, 1);


        //初始化播报动画
        specialGiftSurfaceView = (SpecialGiftSurfaceView) findViewById(R.id.animalsurfaceview);
        specialGiftSurfaceView.setOnClickListener(this);

        //根据是否授权播报不同话语-开启播报动画和声音
        initYzs();
        insertSpeckAnimal(true);
        startAnimal();
        hasAuthoritioned();
    }

    @Override
    public void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_faqrobotact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    private void setToolbarTitle(String toolbarTitle) {
        getSupportActionBar().setTitle(toolbarTitle);
    }

    @Override
    public void onClick(View v) {
        if (statue == MData.STATE_SPECK) {
            YzsTtsManager.getInstance(this).stopSpeck();
            statue = MData.STATE_LISTENERING;
            insertListenerAnimal(true);
            YzsIatManager.getInstance(this).reStartIat();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (backToWindow) {
            backToWindow = false;
            statue = MData.STATE_LISTENERING;
            insertListenerAnimal(true);
            YzsIatManager.getInstance(this).reStartIat();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        backToWindow = true;
        YzsIatManager.getInstance(this).stopListenering();
        YzsTtsManager.getInstance(this).stopSpeck();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAnimal();
    }

    //----------------------------------------------------聊天相关------------------------------------------------------
    private void initYzs() {
        YzsTtsManager.getInstance(this).setIatCallback(new YzsTtsInterface() {
            @Override
            public void speckEnd() {
                switch (statue) {
                    case MData.STATE_SPECK:
                        if ((boolean) SPUtil.get(FaqrobotActvity.this, "hasauthoritioned", false)) {
                            statue = MData.STATE_LISTENERING;
                            insertListenerAnimal(true);
                            YzsIatManager.getInstance(FaqrobotActvity.this).reStartIat();
                            return;
                        }
                        ShowAuthoritionDialog();
                        break;
                }
            }

            @Override
            public void speckErrorAndStartIat() {
                statue = MData.STATE_LISTENERING;
                insertListenerAnimal(true);
                YzsIatManager.getInstance(FaqrobotActvity.this).reStartIat();
            }
        });

        YzsIatManager.getInstance(this).setIatCallback(new YzsIatInterface() {
            @Override
            public void recogWord(String word) {
                getRobotAnswer(word);
            }

            @Override
            public void volumeChange(int volume) {

            }

            @Override
            public void personSpeckAndResetTime() {

            }
        });
        YzsIatManager.getInstance(FaqrobotActvity.this).initRecognizer();
    }

    private void getRobotAnswer(String content) {
        long timestamp = System.currentTimeMillis();
        String token = AuthoritionUtils.encryptDesBase62(timestamp, AuthoritionUtils.getUniqueDeviceCode());
        HttpListener httpListener = new HttpListener<GetRobotResultV4>() {
            @Override
            public void onError(String errorMsg) {
                ToastUtils.show("聊天接口错误");
                insertListenerAnimal(true);
                YzsIatManager.getInstance(FaqrobotActvity.this).reStartIat();
            }

            @Override
            public void onSuccess(GetRobotResultV4 getRobotResultV4) {
                setTypeAnswer(getRobotResultV4);
            }
        };
        FaqRobotLoader faqRobotLoader = new FaqRobotLoader(MData.HOSTNAME);
        faqRobotLoader.robotChat("bf9a7ef5-09d1-4489-92ff-4f143af48687", MData.QUESTION, token, timestamp
                , MData.CLIENDID, MData.ZHANGDIAN, MData.SOURCEID + "", null, content, false, (RxBaseActivity) FaqrobotActvity.this, httpListener);
    }

    private void setTypeAnswer(GetRobotResultV4 getRobotResultV4) {
        RobotReplyV4 robotReplyV4 = getRobotResultV4.getData().getRobotReply().get(0);
        String msgType = robotReplyV4.getMsgtype();
        switch (msgType) {
            case MData.Type.TXT:
                String WebBackString = robotReplyV4.getText().getContent();
                if (!ObjectUtils.isNull(WebBackString)) {
                    statue = MData.STATE_SPECK;
                    insertSpeckAnimal(true);
                    showWords = WebBackString;
                    myHandler.sendEmptyMessage(MData.HANDLER_MESSAGE_WHAT);
                    YzsTtsManager.getInstance(this).initSpeckWord(showWords);
                } else {
                    statue = MData.STATE_SPECK;
                    insertSpeckAnimal(true);
                    showWords = getString(R.string.webhasnoinfoback);
                    myHandler.sendEmptyMessage(MData.HANDLER_MESSAGE_WHAT);
                    YzsTtsManager.getInstance(this).initSpeckWord(showWords);
                }
                break;
            default:
                statue = MData.STATE_SPECK;
                insertSpeckAnimal(true);
                showWords = getString(R.string.nosolvetype_words);
                myHandler.sendEmptyMessage(MData.HANDLER_MESSAGE_WHAT);
                YzsTtsManager.getInstance(this).initSpeckWord(showWords);
                break;
        }
    }

    private static class MyHandler extends Handler {
        private final WeakReference<FaqrobotActvity> mActivityReference;

        MyHandler(FaqrobotActvity activity) {
            this.mActivityReference = new WeakReference<FaqrobotActvity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FaqrobotActvity activity = (FaqrobotActvity) mActivityReference.get();
            if (activity != null && msg.what == MData.HANDLER_MESSAGE_WHAT) {
                activity.tvShowContents();
            }
        }
    }

    private void tvShowContents() {
        tvShowWords.setText(showWords);
    }


    //----------------------------------------------------授权相关------------------------------------------------------
    private void ShowAuthoritionDialog() {
        authoritionDialog = new AuthoritionDialog(this,
                R.style.CustomHoloLight,
                () -> {
                    authoritionDialog.dismiss();
                    SPUtil.put(this, "hasauthoritioned", true);
                    insertListenerAnimal(true);
                    YzsIatManager.getInstance(this).reStartIat();
                });
        authoritionDialog.show();
    }

    private void hasAuthoritioned() {
        boolean hasauthoritioned = (boolean) SPUtil.get(this, "hasauthoritioned", false);
        setToolbarTitle((hasauthoritioned == true) ? "已授权" : "未授权");
        showWords = ((hasauthoritioned == true) ?
                getString(R.string.hasauthoritionedword) :
                getString(R.string.nohasauthoritionedword));
        myHandler.sendEmptyMessage(MData.HANDLER_MESSAGE_WHAT);
        YzsTtsManager.getInstance(this).init(showWords);
    }


    //----------------------------------------------------动画和声音------------------------------------------------------
    private void insertSpeckAnimal(boolean needVoice) {
        specialGiftSurfaceView.setBitmapResoursID(speckImgs);
        if (needVoice) {
            soundPool.play(soundId_tts, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

    private void insertListenerAnimal(boolean needVoice) {
        specialGiftSurfaceView.setBitmapResoursID(listenerImgs);
        if (needVoice) {
            soundPool.play(soundId_iat, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }

    private void startAnimal() {
        //设置监听事件
        specialGiftSurfaceView.setOnFrameFinisedListener(new SpecialGiftSurfaceView.OnFrameFinishedListener() {
            @Override
            public void onStop() {
            }

            @Override
            public void onStart() {
            }
        });
        //设置单张图片展示时长
        specialGiftSurfaceView.setGapTime(150);
        specialGiftSurfaceView.start();
    }

    private void stopAnimal() {
        specialGiftSurfaceView.stop();
    }
}
