package com.zhsy.androidutils.Utils;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.zhsy.androidutils.R;

import java.util.Stack;

/**
 * Created by zhsy on 2018/8/1.
 * activity工具类
 **/
public class ActivityUtil {
    /**
     * 开启另外一个activity
     *
     * @param activity
     * @param cls 另外的activity类
     * @param bundle 传递的bundle对象
     * @param isFinish true表示要关闭activity false表示不要关闭activity
     */
    public static void startActivity(Activity activity, Class<?> cls, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 开启另外一个activity(默认动画)
     *
     * @param activity
     * @param cls 另外的activity类
     * @param bundle 传递的bundle对象
     * @param isFinish true表示要关闭activity false表示不要关闭activity
     */
    public static void startActivityDefault(Activity activity, Class<?> cls, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }
    /**
     * 开启另外一个activity
     *
     * @param activity
     * @param cls 另外的activity类
     * @param bundle 传递的bundle对象
     * @param isFinish true表示要关闭activity false表示不要关闭activity
     */
    public static void startActivityBack(Activity activity, Class<?> cls, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
        activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    /**
     * 开启另外一个activity
     *
     * @param activity
     * @param cls 另外的activity类
     * @param bundle 传递的bundle对象
     * @param isFinish true表示要关闭activity false表示不要关闭activity
     */
    public static void startActivityForResult(Activity activity, Class<?> cls, int requestCode,
                                              Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
        if (isFinish) {
            activity.finish();
        }
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * Fragment中开启另外一个activity
     *
     * @param fragment
     * @param cls 另外的activity类
     * @param bundle 传递的bundle对象
     * @param isFinish true表示要关闭activity false表示不要关闭activity
     */
    public static void startActivityForResult(Fragment fragment, Class<?> cls, int requestCode,
                                              Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        fragment.startActivityForResult(intent, requestCode);
        if (isFinish) {
            fragment.getActivity().finish();
        }
        fragment.getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 开启另外一个activity
     *
     * @param activity
     * @param intent
     * @param bundle 传递的bundle对象
     * @param requestCode
     * @param isFinish true表示要关闭activity false表示不要关闭activity
     */
    public static void startActivityForResultIntent(Activity activity, Intent intent,
                                                    Bundle bundle, int requestCode, boolean isFinish) {
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
        if (isFinish) {
            activity.finish();
        }
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void finishtheActivity(Activity activity){
        // 退出Activity时动画
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    /**
     * 进入创建直播界面
     * @param activity
     * @param cls
     * @param bundle
     * @param isFinish
     */
    public static void startCreateLiveActivity(Activity activity, Class cls, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
        activity.overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
    }




    //Stack(栈)，后进先出
    private static Stack<Activity> activityStack = new Stack<>();
    //Activity的生命周期回调，要求API14+（Android 4.0+）
    private static final MyActivityLifecycleCallbacks instance = new MyActivityLifecycleCallbacks();

    private static class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            activityStack.remove(activity);
            activityStack.push(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            activityStack.remove(activity);
        }
    }

    public static Application.ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return instance;
    }

    /**
     * 获得当前栈顶Activity
     */
    public static Activity getCurrentActivity() {
        Activity activity = null;
        if (!activityStack.isEmpty())
            activity = activityStack.peek();
        return activity;
    }

    /**
     * 获得当前Activity名字
     */
    public static String getCurrentActivityName() {
        Activity activity = getCurrentActivity();
        String name = "";
        if (activity != null) {
            name = activity.getComponentName().getClassName();
        }
        return name;
    }

    /**
     * 关闭当前Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }
    /**
     * 关闭所有Activity
     */
    public static void closeAllActivity() {
        while (true) {
            Activity activity = getCurrentActivity();
            if (null == activity) {
                break;
            }
            finishActivity(activity);
        }
    }

    public static void closeActivityByName(String name) {
        int index = activityStack.size() - 1;
        while (true) {
            Activity activity = activityStack.get(index);
            if (null == activity) {
                break;
            }
            String activityName = activity.getComponentName().getClassName();
            if (!TextUtils.equals(name, activityName)) {
                index--;
                if (index < 0) {
                    break;
                }
                continue;
            }
            finishActivity(activity);
            break;
        }
    }

    public static Stack<Activity> getActivityStack() {
        Stack<Activity> stack = new Stack<>();
        stack.addAll(activityStack);
        return stack;
    }

}
