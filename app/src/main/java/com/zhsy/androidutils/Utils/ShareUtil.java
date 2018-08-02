package com.zhsy.androidutils.Utils;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by zhsy on 2018/8/1.
 * 调用系统分享图片和文字工具类
 **/
public class ShareUtil {
    public static void shareImage(String title, Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        ActivityUtil.getCurrentActivity().startActivity(Intent.createChooser(intent, title));
    }

    public static void shareText(String title, String text) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityUtil.getCurrentActivity().startActivity(Intent.createChooser(intent, title));
    }
}
