package com.zhsy.androidutils.Utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NetUtils {
    private NetUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity=null;
        if(context!=null){
            connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }
    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    /**
     * ping 网络请求
     * */
    public static String ping(String string) {
        String result="";
        if(TextUtils.isEmpty(string)){
            return result=" failed : 网址为null";
        }
        Process process;
        try {
            // -c ping 的次数  ;  -w 以秒为单位 指定超时间隔
            process=Runtime.getRuntime().exec("ping -c 3 -w 8 "+string);
            if(process==null){
                return result=" failed : process = null";
            }
            int status=process.waitFor();
            InputStream stream=process.getInputStream();
            BufferedReader buReader=new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer=new StringBuffer();
            String line="";
            while ((line=buReader.readLine())!=null) {
                buffer.append(line);
            }
            if(status==0){
                result="success : "+buffer.toString();
            }else{
                result="failed : "+buffer.toString();
            }
        } catch (Exception e) {
            result="failed  e = "+e.toString();
        }
        return result;
    }
}
