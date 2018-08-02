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

    /**
     *boolean
     */
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
    /**
     * String
     */
    public static void setString(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(
                SPFILE, Context.MODE_PRIVATE).edit();
        editor.putString(key, value).apply();
    }

    public static String getString(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(SPFILE,
                Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * Int
     */
    public static void setInt(Context context,String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(
                SPFILE, Context.MODE_PRIVATE).edit();
       editor.putInt(key, value).apply();
    }

    public static int getInt(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(SPFILE,
                Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    /**
     * Float
     */
    public static void setFloat(Context context,String key, Float value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(
                SPFILE, Context.MODE_PRIVATE).edit();
        editor.putFloat(key, value).apply();
    }

    public static Float getFloat(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(SPFILE,
                Context.MODE_PRIVATE);
        return sp.getFloat(key, 0);
    }

    /**
     * Long
     */
    public static void setLong(Context context,String key, Long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(
                SPFILE, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value).apply();
    }

    public static Long getLong(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences(SPFILE,
                Context.MODE_PRIVATE);
        return sp.getLong(key, 0);
    }

    /**
     * Remove
     */
    public static void removeByKey(Context context,String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(
                SPFILE, Context.MODE_PRIVATE).edit();
        editor.remove(key).apply();
    }

    public static void removeAll(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(
                SPFILE, Context.MODE_PRIVATE).edit();
        editor.clear().apply();
    }
}
