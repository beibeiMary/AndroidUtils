package com.zhsy.androidutils.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpTools {
    private static final String ISINAPP = "isInApp";
    public static final String ISOPENLOG = "isopenLog";
    /** sharepreference 文件名称*/
    public final static String SPFILE = "spfile";

    public static void spSaveInApp(Context context, Boolean date) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SPFILE, Context.MODE_PRIVATE).edit();
        editor.putBoolean(ISINAPP, date);
        editor.commit();
    }

    public static boolean spGetInApp(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SPFILE, Context.MODE_PRIVATE);
        return sp.getBoolean(ISINAPP, false);
    }

    public static void spSaveBoolean(Context context, String key, boolean tag) {
        SharedPreferences.Editor editor = context.getSharedPreferences(
                SPFILE, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, tag);
        editor.commit();
    }
    public static boolean spGetBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SPFILE,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
}
