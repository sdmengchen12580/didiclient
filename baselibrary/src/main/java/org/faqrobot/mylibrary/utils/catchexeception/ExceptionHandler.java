package org.faqrobot.mylibrary.utils.catchexeception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import org.faqrobot.mylibrary.utils.file.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExceptionHandler implements UncaughtExceptionHandler {
	// 上下文
	private Context mContext;
	public boolean openUpload = true;
	private static final String LOG_FILE_DIR = "log";
	private static final String FILE_NAME = ".log";
	private static ExceptionHandler instance = null;
	private UncaughtExceptionHandler mDefaultCrashHandler;

	private ExceptionHandler(Context cxt) {
		mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
		this.mContext = cxt.getApplicationContext();
	}

	public synchronized static ExceptionHandler init(Context cxt) {
		if (instance == null) {
			instance = new ExceptionHandler(cxt);
		}
		return instance;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		try {
			saveToSDCard(ex);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mDefaultCrashHandler != null) {
				mDefaultCrashHandler.uncaughtException(thread, ex);
			} else {
				ex.printStackTrace();
			}
		}
	}

	private void saveToSDCard(Throwable ex) throws Exception {
		File file = FileUtils.getNewFile(FileUtils.getRootPath() + File.separator
				+ getDataTime("yyyyMMddHHmmss") + ".txt");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		pw.println(getDataTime("yyyy-MM-dd-HH-mm-ss"));
		savePhoneInfo(pw);
		pw.println();
		ex.printStackTrace(pw);
		pw.close();
	}

	/**
	 * 保存手机硬件信息
	 * 
	 * @description：
	 * @author ldm
	 * @date 2016-4-18 上午11:38:01
	 */
	private void savePhoneInfo(PrintWriter pw) throws NameNotFoundException {
		PackageManager pm = mContext.getPackageManager();
		PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
		pw.print("App Version: ");
		pw.print(pi.versionName);
		pw.print('_');
		pw.println(pi.versionCode);
		pw.println();

		pw.print("OS Version: ");
		pw.print(Build.VERSION.RELEASE);
		pw.print("_");
		pw.println(Build.VERSION.SDK_INT);
		pw.println();

		pw.print("Manufacturer: ");
		pw.println(Build.MANUFACTURER);
		pw.println();

		pw.print("Model: ");
		pw.println(Build.MODEL);
		pw.println();
	}

	/**
	 * 根据时间格式返回时间
	 * 
	 * @description：
	 * @author ldm
	 * @date 2016-4-18 上午11:39:30
	 */
	private String getDataTime(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}
}
