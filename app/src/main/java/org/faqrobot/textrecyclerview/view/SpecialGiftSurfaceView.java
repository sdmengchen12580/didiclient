package org.faqrobot.textrecyclerview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孟晨 on 2018/4/16.
 */

public class SpecialGiftSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static final String TAG = "Surface";
    private static final long INTERVAL_TIME = 66;//最大间隔时间，每帧时间为最大时间减去加载图片消耗的时间。
    private SurfaceHolder mHolder;
    private boolean isDrawing = false;
    private boolean isSurfaceCreated = false;
    private List<String> mFilePathListRGB = new ArrayList<>();
    private List<String> mFilePathListAlpha = new ArrayList<>();
    private HandlerThread handlerThread = new HandlerThread("surfaceview");
    private RectF mRectF;
    private OnFrameAnimationListener mListener;
    private Handler mWorkHandler;

    public SpecialGiftSurfaceView(Context context) {
        super(context);
        init();
    }

    public SpecialGiftSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        //设置SurfaceView透明
        setZOrderOnTop(true);
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        handlerThread.start();
    }

    /**
     * 开始帧动画
     *
     * @param pathListRGB   彩色图片路径
     * @param pathListAlpha 透明图片路径
     */
    public void startAnimation(List<String> pathListRGB, List<String> pathListAlpha) {
        long delay = 0;
        if (pathListRGB == null || pathListRGB.size() == 0) {
            return;
        }
        setVisibility(VISIBLE);
        mFilePathListRGB.clear();
        mFilePathListRGB.addAll(pathListRGB);
        mFilePathListAlpha.clear();
        if (pathListAlpha != null && mFilePathListAlpha.size() > 0) {
            mFilePathListAlpha.addAll(pathListAlpha);
        }
        mWorkHandler = new Handler(handlerThread.getLooper());
        if (!isSurfaceCreated) {
            Log.d(TAG, "SurfaceView is not created.wait 1000");
            delay = 1000;
        }
        setLayerType(LAYER_TYPE_HARDWARE, null);
        mWorkHandler.postDelayed(this, delay);
    }

    public void startAnimation(List<String> pathListRGB) {
        startAnimation(pathListRGB, null);
    }

    /**
     * 停止动画
     */
    public void stopAnimation() {
        mFilePathListRGB.clear();
        mFilePathListAlpha.clear();
        setVisibility(INVISIBLE);
        setLayerType(LAYER_TYPE_NONE, null);
        isDrawing = false;
        if (mWorkHandler != null) {
            mWorkHandler.removeCallbacks(this);
        }
    }

    public void setListener(OnFrameAnimationListener listener) {
        mListener = listener;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isSurfaceCreated = true;
        isDrawing = true;
        Log.d(TAG, "surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopAnimation();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            stopAnimation();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        SpecialGiftSurfaceView.this.post(new Runnable() {
            @Override
            public void run() {
                notifyStart();
            }
        });
        for (int i = 0; i < mFilePathListRGB.size(); i++) {
            if (isDrawing) {
                try {
                    long temp = System.currentTimeMillis();
                    if (mFilePathListAlpha != null && mFilePathListAlpha.size() > 0) {
                        draw(mFilePathListRGB.get(i), mFilePathListAlpha.get(i));
                    } else {
                        draw(mFilePathListRGB.get(i));
                    }
                    //间隔幅度越小，CPU占比越大。所以应该合理设置。
                    long ll = System.currentTimeMillis() - temp;
                    Log.d(TAG, "id :" + i + "   temp :" + ll);
                    Thread.sleep(Math.max(0, (INTERVAL_TIME - ll)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        SpecialGiftSurfaceView.this.post(new Runnable() {
            @Override
            public void run() {
                stopAnimation();
                notifyFinished();
            }
        });
    }

    private void draw(String path) {
        Canvas canvas = mHolder.lockCanvas();
        if (canvas != null) {
            Bitmap diskBitmap = getDiskBitmap(path);
            if (diskBitmap != null) {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

                mRectF = new RectF(SpecialGiftSurfaceView.this.getLeft(),
                        SpecialGiftSurfaceView.this.getTop(),
                        SpecialGiftSurfaceView.this.getWidth(),
                        SpecialGiftSurfaceView.this.getHeight());
                canvas.drawBitmap(diskBitmap, null, mRectF, null);
            }
            mHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void draw(String pathRGB, String pathAlpha) {
        Canvas canvas = mHolder.lockCanvas();
        if (canvas != null) {
            int saveCount = canvas.getSaveCount();
            Paint l = new Paint();
            l.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            Paint m = new Paint();
            m.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                    0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                    0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                    0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                    1.0f, 0.0f, 0.0f, 0.0f, 0.0f})));
            Bitmap decodeFile = getDiskBitmap(pathRGB);
            Bitmap decodeFile2 = getDiskBitmap(pathAlpha);
            if (!(decodeFile == null || decodeFile2 == null)) {
                Bitmap r = Bitmap.createBitmap(decodeFile.getWidth(), decodeFile.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(r);
                RectF rectF = new RectF(SpecialGiftSurfaceView.this.getLeft(),
                        SpecialGiftSurfaceView.this.getTop(),
                        SpecialGiftSurfaceView.this.getWidth(),
                        SpecialGiftSurfaceView.this.getHeight());

                c.drawBitmap(decodeFile2, 0.0f, 0.0f, m);
                c.drawBitmap(decodeFile, 0.0f, 0.0f, l);

                canvas.drawBitmap(r, null, rectF, null);
                canvas.restoreToCount(saveCount);
            }
            mHolder.unlockCanvasAndPost(canvas);
        }
    }

    private Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void notifyStart() {
        if (mListener != null) {
            mListener.onFrameAnimationStart();
        }
    }

    private void notifyFinished() {
        if (mListener != null) {
            mListener.onFrameAnimationFinished();
        }
    }

    public interface OnFrameAnimationListener {

        void onFrameAnimationStart();

        void onFrameAnimationFinished();

    }
}
