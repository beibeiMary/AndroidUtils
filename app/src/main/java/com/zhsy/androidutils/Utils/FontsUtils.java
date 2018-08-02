package com.zhsy.androidutils.Utils;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class FontsUtils {
    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName)
    {
        Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName, Typeface newTypeface) {
        try {
            Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
