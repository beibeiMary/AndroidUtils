package com.zhsy.androidutils.Utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil {
    private static Toast toast;

    /**
     * showToast 底部显示（默认）
     *
     * @param msg 需要显示的参数
     */
    public static void showToast(final Context context,final String msg) {
        if ("main".equals(Thread.currentThread().getName())) {
            createToast(context,msg);
        } else {
            ActivityUtil.getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    createToast(context,msg);
                }
            });
        }
    }

    /**
     * createToast
     *
     * @param msg 接收参数
     */
    private static void createToast(Context context,String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
        messageTextView.setTextSize(15);
        toast.show();
    }

    /**
     * showCenterToast 居中显示
     *
     * @param msg 需要显示的参数
     */
    public static void showCenterToast(final Context context,final String msg) {
        if ("main".equals(Thread.currentThread().getName())) {
            createCenterToast(context,msg);
        } else {
            ActivityUtil.getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    createCenterToast(context,msg);
                }
            });
        }
    }

    /**
     * createCenterToast
     *
     * @param msg 接收参数
     */
    private static void createCenterToast(Context context,String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
        toast.setGravity(Gravity.CENTER, 0, 0);
        messageTextView.setTextSize(15);
        toast.show();
    }

    /**
     * 取消Toast
     * onDestroy时调用，或者onPause
     * 当前页面finish之后在下一个页面不会显示
     */
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
