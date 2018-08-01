package com.zhsy.androidutils.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by zhsy on 2018/8/1.
 * 文件存储工具类
 **/
public class FileUtils {
    private static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");

    public static void downFile(String filename, String url, Context context)
            throws Exception {
        File dest = new File("/sdcard/data/" + filename + ".html");
        InputStream is;
        FileOutputStream fos = new FileOutputStream(dest);
        URL wangyi = new URL(url);
        is = wangyi.openStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int length;
        byte[] bytes = new byte[1024 * 20];
        while ((length = bis.read(bytes, 0, bytes.length)) != -1) {
            fos.write(bytes, 0, length);
        }
        bos.close();
        fos.close();
        bis.close();
        is.close();
    }
    public static int copyFile(String oldPath,String newPath,String name){
        int result=-1;
        int byteRead=0;
        File file=new File(oldPath);
        if(file.exists()&&!file.isDirectory()){
            try {
                InputStream stream=new FileInputStream(file);
                File newFile=new File(newPath);
                if(!newFile.exists()){
                    newFile.mkdirs();
                }
                FileOutputStream fos= new FileOutputStream(newPath+File.separator+name);
                byte[] buffer=new byte[1024];
                while ((byteRead=stream.read(buffer))!=-1) {
                    fos.write(buffer,0,byteRead);
                }
                fos.flush();
                fos.close();
                stream.close();
                result=0;
            } catch (IOException e) {
                e.printStackTrace();
                result=-1;
            }
        }
        return result;
    }
    public static File getRootDirFile(Context context, String path, String name) {
        String pathString = "";
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            pathString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + path;
        } else {
            pathString = context.getFilesDir().getAbsolutePath()
                    + File.separator + path;
        }
        File file = new File(pathString);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    "The logcat folder path is not a directory: " + pathString);
        }
        String PATH_LOGCAT = pathString.endsWith("/") ? pathString : pathString
                + "/";
        File folder = new File(PATH_LOGCAT, name
                + simpleDateFormat1.format(new Date()) + ".txt");
        return folder;
    }

    public static void downFileF(String downloadUrl) throws Exception {
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;

        URL wangyi = new URL(downloadUrl);
        inputStream = wangyi.openStream();
        inputStreamReader = new InputStreamReader(inputStream, "gb2312");
        bufferedReader = new BufferedReader(inputStreamReader);
        String s;
        File dest = new File("/sdcard/data/wangyi.html");
        fileOutputStream = new FileOutputStream(dest);
        outputStreamWriter = new OutputStreamWriter(fileOutputStream, "gb2312");
        while ((s = bufferedReader.readLine()) != null) {
            outputStreamWriter.write(s);
        }

        outputStreamWriter.close();
        fileOutputStream.close();
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
    }

    /**
     *
     *
     * @param context
     * @param saveInfo
     */
    public static void doSaveLogInfo(Context context, final String saveInfo) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput("data", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            try {
                writer.write(saveInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e2) {
            }
        }
    }

    public static void doSaveLogInfoSD(Context context, final String saveInfo) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        File folder = getRootDirFile(context, "HP-Logcat", "logcat-");
        try {
            out = new FileOutputStream(folder, true);// ???
            writer = new BufferedWriter(new OutputStreamWriter(out));
            try {
                writer.write(saveInfo + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e2) {
            }
        }
    }

    /**
     * @param context
     * @param bitmap
     * @param path
     */
    public static void saveBitMap(Context context, Bitmap bitmap, String path) {
        String pathString = getFilesPath(context, "download");
        File files = new File(pathString);
        if (!files.exists()) {
            files.mkdirs();
        }

        String PATH_LOGCAT = pathString.endsWith("/") ? pathString : pathString
                + "/";
        File folder = new File(PATH_LOGCAT, path);
        if (folder.exists()) {
            return;
        }
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(folder));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param filesName ???
     */
    public void makeDirs(String filesName) {
        File file = new File(filesName);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     *
     * @param context
     * @param filesName
     * @return
     */
    public static String getFilesPath(Context context,String filesName) {
        String pathString = "";
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            pathString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + filesName;
        } else {
            pathString = context.getFilesDir().getAbsolutePath()
                    + File.separator + filesName;
        }
        return pathString;
    }

    public boolean fileExists(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * @param filePath
     * @return
     */
    public static Bitmap getBitMap(String filePath) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(filePath);
            try {
                InputStream inputStream = url.openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
