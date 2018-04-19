package org.faqrobot.textrecyclerview.ui.act;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.ui.baseact.RxBaseActivity;
import org.faqrobot.textrecyclerview.utils.ToastUtils;
import org.faqrobot.textrecyclerview.view.SpecialGiftSurfaceView;

import java.util.Arrays;
import java.util.List;

public class FaqrobotActvity extends RxBaseActivity implements SpecialGiftSurfaceView.OnFrameAnimationListener {

    public SoundPool soundPool;
    public int soundId_iat, soundId_tts;
    private SpecialGiftSurfaceView specialGiftSurfaceView;
    private List speckImgs = Arrays.asList(R.drawable.bobao1, R.drawable.bobao2, R.drawable.bobao3);
    private List listenerImgs = Arrays.asList(R.drawable.lingting1, R.drawable.lingting2, R.drawable.lingting3);

    @Override
    public int getLayoutId() {
        return R.layout.activity_faqrobot_actvity;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //surfaceview
        specialGiftSurfaceView = (SpecialGiftSurfaceView) findViewById(R.id.animalsurfaceview);
        specialGiftSurfaceView.setListener(this);

        //声音
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId_iat = soundPool.load(FaqrobotActvity.this, R.raw.shibie, 1);
        soundId_tts = soundPool.load(FaqrobotActvity.this, R.raw.bobao, 1);

        //初始化播报动画
        startSpeckAnimal();
    }

    @Override
    public void initToolBar() {

    }


    private void startListenerAnimal() {
        specialGiftSurfaceView.startAnimation(listenerImgs);
    }

    private void startSpeckAnimal() {
        specialGiftSurfaceView.startAnimation(speckImgs);
    }

    private void stopAnimal() {
        specialGiftSurfaceView.stopAnimation();
    }

    private void playSpeckRaw(){
        soundPool.play(soundId_tts, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    private void playListenerRaw(){
        soundPool.play(soundId_iat, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    @Override
    public void onFrameAnimationStart() {
        ToastUtils.show("start");
    }

    @Override
    public void onFrameAnimationFinished() {
        ToastUtils.show("stop");
    }
}
