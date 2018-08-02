package com.zhsy.androidutils.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhsy on 2018/8/1.
 * 广播工具类
 **/
public class BroadcastUtils {
    private Context mContext;
    private static BroadcastUtils instance;
    private Map<String, BroadcastReceiver> receiverMap;

    private BroadcastUtils(Context context)
    {
        this.mContext = context;
        this.receiverMap = new HashMap();
    }

    public static BroadcastUtils getInstance(Context context)
    {
        if (instance == null) {
            synchronized (BroadcastUtils.class) {
                if (instance == null) {
                    instance = new BroadcastUtils(context);
                }
            }
        }
        return instance;
    }

    public void addAction(String action, BroadcastReceiver receiver)
    {
        try
        {
            IntentFilter filter = new IntentFilter();
            filter.addAction(action);
            this.mContext.registerReceiver(receiver, filter);
            this.receiverMap.put(action, receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAction(String[] actions, BroadcastReceiver receiver)
    {
        try
        {
            IntentFilter filter = new IntentFilter();
            for (String action : actions) {
                filter.addAction(action);
            }
            this.mContext.registerReceiver(receiver, filter);
            for (String action : actions) {
                this.receiverMap.put(action, receiver);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendBroadcast(String action)
    {
        sendBroadcast(action, "");
    }

    public void sendBroadcast(String action, Object obj)
    {
        try
        {
            Intent intent = new Intent();
            intent.setAction(action);
            intent.putExtra("result", obj.toString());
            this.mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy(String[] actions)
    {
        try
        {
            if (this.receiverMap != null)
                for (String action : actions) {
                    BroadcastReceiver receiver = (BroadcastReceiver)this.receiverMap.get(action);
                    if (receiver != null)
                        this.mContext.unregisterReceiver(receiver);
                }
        }
        catch (IllegalArgumentException e)
        {
            Log.d("Broadcastmanager", e.toString());
        }
    }
}
