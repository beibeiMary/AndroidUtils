package com.zhsy.androidutils.Utils;

import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static final String Md5Encode(String data)
    {
        MessageDigest md5;
        if (TextUtils.isEmpty(data))
            return "";
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return data;
        }
        try {
            byte[] bytes = md5.digest(data.getBytes("utf8"));
            StringBuilder ret = new StringBuilder(bytes.length << 1);
            for (int i = 0; i < bytes.length; ++i) {
                ret.append(Character.forDigit(bytes[i] >> 4 & 0xF, 16));
                ret.append(Character.forDigit(bytes[i] & 0xF, 16));
            }
            return ret.toString();
        } catch (UnsupportedEncodingException e) {
        }
        return data;
    }


    public static final String Md5FileEncode(String fileName)
    {
        FileInputStream fis = null;
        byte[] buf = new byte[4096];
        MessageDigest md;
        boolean fileIsNull = true;
        try {
            fis = new FileInputStream(fileName);
            int len = 0;
            md = MessageDigest.getInstance("MD5");
            len = fis.read(buf);
            if (len > 0) {
                fileIsNull = false;
                while (len > 0) {
                    md.update(buf, 0, len);
                    len = fis.read(buf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        finally{
            if(fis !=null){
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
        if (fileIsNull) {
            return "";
        } else {
            byte[] result = md.digest();
            return bufferToHex(result);
        }
    }

    private static String bufferToHex(byte[] bytes)
    {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[((bt & 0xF0) >> 4)];
        char c1 = hexDigits[(bt & 0xF)];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; ++l) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    public static String getMD5Str(String str)
    {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; ++i) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}
