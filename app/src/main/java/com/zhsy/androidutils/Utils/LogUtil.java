package com.zhsy.androidutils.Utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by zhsy on 2018/8/1.
 * Log工具类
 **/
public class LogUtil {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;
    public static int CONTROL = 0;
    /**
     * 打印日志信息
     * @param context
     * @param tag
     * @param msg
     */
    public static void I(Context context, String tag, String msg) {
        if (SpTools.spGetBoolean(context,
                SpTools.ISOPENLOG)) {
            if (INFO > CONTROL) {
                Log.i(tag,context +"---"+tag+"-----"+ msg);
            }
            FileUtils.doSaveLogInfoSD(context, context + "---"+tag+"-----"+ msg);
        }
    }
    public static void E(Context context,String tag,String msg) {
        if (SpTools.spGetBoolean(context,
                SpTools.ISOPENLOG)) {
            if (ERROR > CONTROL) {
                Log.e(tag,context + "---"+tag+"-----"+ msg);
            }
            FileUtils.doSaveLogInfoSD(context, context + "---"+tag+"-----"+ msg);
        }
    }
    public static void D(Context context,String tag,String msg) {
        if (SpTools.spGetBoolean(context,
                SpTools.ISOPENLOG)) {
            if (DEBUG > CONTROL) {
                Log.d(tag,context + "---" + msg);
            }
            Log.d(tag," ISOPENLOG = " + SpTools.spGetBoolean(context,
                    SpTools.ISOPENLOG));
            FileUtils.doSaveLogInfoSD(context, context + "---"+tag+"-----" + msg);
        }
    }
}
