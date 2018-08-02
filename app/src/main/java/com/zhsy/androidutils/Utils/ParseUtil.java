package com.zhsy.androidutils.Utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by zhsy on 2018/8/1.
 * Json解析工具类
 **/
public class ParseUtil {
    //根据key值解析，只支持json中的一级字段
    public static String parseByKey(String response,String key) {
        String value = "";
        try {
            JSONObject json = new JSONObject(response);
            value = json.getString(key);
        } catch (JSONException var) {
            var.printStackTrace();
        }
        return value;
    }
    public static String jsonUtils(Context context, String fileName)
    {
        StringBuilder stringBuilder = null;
        try {
            String line;
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(fileName), "GB2312");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);

            stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
        } catch (UnsupportedEncodingException e) {
            stringBuilder = null;
            e.printStackTrace();
        } catch (IOException e) {
            stringBuilder = null;
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
