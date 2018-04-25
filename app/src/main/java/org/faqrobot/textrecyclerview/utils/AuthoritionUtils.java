package org.faqrobot.textrecyclerview.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import org.faqrobot.mylibrary.utils.other.AppUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by 孟晨 on 2018/4/20.
 */

public class AuthoritionUtils {

    public static String getUniqueDeviceCode() {
        String mAndroidID = Settings.System.getString(AppUtils.getAppContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        StringBuilder mHelper = new StringBuilder();
        String s = mHelper
                .append(mAndroidID)
                .toString();
        //进行MD5加密
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(s.getBytes(), 0, s.length());
        byte p_md5Data[] = m.digest();
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            if (b <= 0xF)
                m_szUniqueID += "0";
            m_szUniqueID += Integer.toHexString(b);
        }
        Log.e("授权client的加密设备码: ",m_szUniqueID.toUpperCase());
        return m_szUniqueID.toUpperCase();
    }


    public static String encryptDesBase62(long timestamp,String deviceCode){
        String key = deviceCode;
        int nonce = new Random().nextInt(10);
        String source = key + timestamp + nonce;
        String encryptionString = null;
        try {
            encryptionString = encryptDesBase62("iyunwenhj", source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptionString;
    }

    public static String encryptDesBase62(String encryptKey, String source) throws Exception {
        String encryptedDataStr = RobotBase62.base62Encode(getEncryptedData(encryptKey, source));
        return encryptedDataStr;
    }

    private static byte[] getEncryptedData(String encryptKey, String source) throws Exception {
        if (encryptKey != null) {
            int a = encryptKey.length() % 8;
            if (a != 0) {
                for (int i = 0; i < (8 - a); i++) {
                    encryptKey += "0";
                }
            }
        }
        byte[] rawKeyData = encryptKey.getBytes();
        byte[] needEncryptedData = source.getBytes();
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        String algorithm = "DES";
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);

        return cipher.doFinal(needEncryptedData);
    }
}
