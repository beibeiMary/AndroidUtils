package com.zhsy.androidutils.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by zhsy on 2018/8/1.
 * 时间工具类
 **/
public class TimeUtil {
    /**
     * 获取当前年月日
     */
    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取当前时分秒
     */
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取当前年月日时分秒
     */
    public static String getDateAndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        return sdf.format(date);
    }

    /**
     * 获取当前时间，返回Long类型
     */
    public static Long getTimeForLong() {
        return System.currentTimeMillis();
    }

    /**
     * 转换为年月日
     */
    public static String formatDate(String mDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = sdf.parse(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

    /**
     * 将时间戳转为字符串 ，格式：yyyy-MM-dd HH:mm
     */
    public static String getStrTime_ymd_hm(String cc_time) {
        String re_StrTime = "";
        if (TextUtils.isEmpty(cc_time) || "null".equals(cc_time)) {
            return re_StrTime;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));

        return re_StrTime;

    }
    /**
     * 将时间戳转为字符串 ，格式：MM-dd HH:mm
     */
    public static String getStrTime_md_hm(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }
    /**
     * 将字符串转为时间戳
     */
    public static String getthisTime() {
        String re_time = null;
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date d;
        d = new Date(currentTime);
        long l = d.getTime();
        String str = String.valueOf(l);
        re_time = str.substring(0, 10);
        return re_time;
    }
    /**
     * 将时间戳转为字符串 ，格式：yyyy.MM.dd 星期几
     */
    public static String getSection(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  EEEE");
        // 对于创建SimpleDateFormat传入的参数：EEEE代表星期，如“星期四”；MMMM代表中文月份，如“十一月”；MM代表月份，如“11”；
        // yyyy代表年份，如“2010”；dd代表天，如“25”
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }
    /**
     * 根据发布时间的不同返回不同的字符串 1.一小时以内显示分钟数 2.一小时以上12小时以内显示 小时数 3.大于12小时显示 MM-dd HH:mm
     */
    public static String getBefitTimeString(Context context, String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf;
        long lcc_time = Long.valueOf(cc_time);
        long currentTime = System.currentTimeMillis();
        long time = currentTime - lcc_time * 1000L;
        if (time >= 0) {
            if (3600000 > time) {
                // re_StrTime = (currentTime - lcc_time ) / 60000 + "分钟前";
                int releaseTime = (int) Math.ceil((time) / 60000);
                if (releaseTime == 0) {
                    re_StrTime = "刚刚";
                }else{
                    re_StrTime = releaseTime +"分钟前";
                }
                return re_StrTime;
            } else if (3600000 <= time && time < 3600000 * 12) {
                // re_StrTime = (currentTime - lcc_time ) / 3600000 + "小时前";
                re_StrTime = (int) Math.ceil((time) / 3600000) +"小时前";
                return re_StrTime;
            } else if (time >= 3600000 * 12 && time < 3600000 * 24 * 365) {
                sdf = new SimpleDateFormat("MM-dd HH:mm");
                re_StrTime = sdf.format(new Date(lcc_time * 1000L));
                return re_StrTime;
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                re_StrTime = sdf.format(new Date(lcc_time * 1000L));
                return re_StrTime;
            }
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        }
        return re_StrTime;
    }

    private static String mYear;
    private static String mMonth;
    private static String mDay;
    private static String mWay;

    @SuppressLint("WrongConstant")
    public static String StringData()
    {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(1));
        mMonth = String.valueOf(c.get(2) + 1);
        mDay = String.valueOf(c.get(5));
        mWay = String.valueOf(c.get(7));
        if ("1".equals(mWay))
            mWay = "天";
        else if ("2".equals(mWay))
            mWay = "一";
        else if ("3".equals(mWay))
            mWay = "二";
        else if ("4".equals(mWay))
            mWay = "三";
        else if ("5".equals(mWay))
            mWay = "四";
        else if ("6".equals(mWay))
            mWay = "五";
        else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mMonth + "月" + mDay + "日" + "/周" + mWay;
    }

}
