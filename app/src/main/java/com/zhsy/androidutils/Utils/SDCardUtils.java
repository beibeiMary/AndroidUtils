package com.zhsy.androidutils.Utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by zhsy on 2018/8/1.
 * 内存卡工具类
 **/
public class SDCardUtils {
    private SDCardUtils()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable()
    {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }
    /**
     * 获取缓存目录路径
     *
     * @return
     */
    public static File getCahcePath(Context context)   {
        return  Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED) ? context
                .getExternalCacheDir() : context .getCacheDir();
    }
    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize()
    {
        if (isSDCardEnable())  {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath)
    {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath()))
        {
            filePath = getSDCardPath();
        } else
        {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath()
    {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    public static String getImageCachePath()
    {
        String path = getCacheDir() + "image/";
        mkdirs(path);
        return path;
    }

    public static String getThmbnailsCachePath() {
        return getCacheDir() + "thumbnails/";
    }

    public static String getCacheDir()
    {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/Android/data/com.hipad.ssoauth/cache/";
        mkdirs(path);
        return path;
    }

    public static synchronized void mkdirs(String dir) {
        File filedir = new File(dir);
        if ((filedir != null) && (!(filedir.exists())))
            filedir.mkdirs();
    }

    public static boolean isExsit()
    {
        String state = Environment.getExternalStorageState();

        return ("mounted".equals(state));
    }

    public static String getExternalStoragePublicDirectory(String type)
    {
        if (!(isExsit())) {
            return "";
        }

        return Environment.getExternalStoragePublicDirectory(type).getPath();
    }

    public static String getDCIMThumbPath(String path)
    {
        return getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/.thumbnails/" + MD5.Md5Encode(path) + ".png";
    }

    public static String getExternalStoragePath()
    {
        if (isExsit()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "/";
    }

}
