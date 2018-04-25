package org.faqrobot.mylibrary.utils.mbitmp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 候帅 on 2017/10/19.
 */

public class BitmapUtlis {

    //压缩大小
    public static Bitmap plusSize(Bitmap mbitmap) {
        mbitmap = Bitmap.createScaledBitmap(mbitmap, 200, 200, true);
        Log.e("wechat", "压缩后图片的大小" + (mbitmap.getByteCount() / 1024) + "KB宽度为"
                + mbitmap.getWidth() + "高度为" + mbitmap.getHeight());
        return mbitmap;
    }

    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
    public static File compressImage(Context context,Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
//循环判断如果压缩后图片是否大于500kb,大于继续压缩——直到最后一次文件的大小小于500kb
        while (baos.toByteArray().length / 1024 > 2048) {
            //如果还大于500kb则——重置baos即清空baos
            baos.reset();
            //每次都减少10
            options -= 10;
            //继续压缩options，把压缩后的数据存放到baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            //将小于500kb时候的文件大小给length值
            long length = baos.toByteArray().length;
        }
//指定出bitmap文件的路径
        File file=new File(getDiskCacheDir(context),"temp.png");
        try {
            //利用文件输出流，指定路径下创建文件
            FileOutputStream fos = new FileOutputStream(file);
            try {
                //真正的资源还是还baos下。baos转成bytearray——边写边刷新
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //回收bitmap图片
        recycleBitmap(bitmap);
        return file;
    }

    //释放
    public static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps==null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }

    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    public static String getImagePath(Context context, String filename) {
        String path;
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = context.getFilesDir().getAbsolutePath();
        } else {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/msc/";
        }
        if (!path.endsWith("/")) {
            path += "/";
        }
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        path += filename + ".jpg";
        return path;
    }

    /**
     * bitmap至本地
     */
    public static String saveBitmapToFile(Context context, Bitmap bmp, String fileName) {
        String file_path = getImagePath(context, fileName);
        File file = new File(file_path);
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file_path;
    }


    /**旋转角度*/
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片bitmap——returnBm
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            Log.d("tag", "rotate bitmap exception:" + e);
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }


}
