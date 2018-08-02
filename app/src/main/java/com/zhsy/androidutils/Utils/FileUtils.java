package com.zhsy.androidutils.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
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
    /**
     * 写入对象
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean saveObject(Serializable ser, String file, Context context) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(file, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 读取对象
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Serializable readObject(String file,Context context) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 根据时间间隔判断是否需要刷新服务器数据
     *
     * @param cachefile
     * @return
     * @throws IOException
     */
    public static boolean isCacheDataFailure(String cachefile,Context context) {
        boolean failure = false;
        File data = context.getFileStreamPath(cachefile);
        if (data.exists()
                && (System.currentTimeMillis() - data.lastModified()) > 60000)
            failure = true;
        else if (!data.exists())
            failure = true;
        return failure;
    }




    private static final String TAG = "FileUtil";
    private static final String HEADPHOTO_PATH = "/data/com.zhsy.ssoAuth/";
    public static final String HEADPHOTO_NAME_TEMP = "user_photo_temp.jpg";
    public static final String HEADPHOTO_NAME_RAW = "user_photo_raw.jpg";
    private static final String WALLPAPER = "wallpaper.jpg";
    private static String ALBUM_PATH = Environment.getExternalStorageDirectory() + "/com.zhsy.ssoauth/";
    public static final String HEADPHOTO_NAME_LOGIN = "login.txt";
    public static final String APP_ERROR_LOG_NAME = "errlog.txt";

    public static String getCropPath(String path)
    {
        String storageState = Environment.getExternalStorageState();
        if ("removed".equals(storageState)) {
            return null;
        }
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/com.zhsy.ssoAuth/" + "cache" + File.separator;
        String s = MD5.Md5Encode(path) + ".jpg";
        return dirPath + s;
    }

    public static String getHeadPhotoDir()
    {
        String storageState = Environment.getExternalStorageState();
        if ("removed".equals(storageState)) {
            return null;
        }
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/com.zhsy.ssoAuth/";
        SDCardUtils.mkdirs(path);
        return path;
    }

    public static File getHeadPhotoFileTemp()
    {
        File file = new File(getHeadPhotoDir() + "user_photo_temp.jpg");
        return file;
    }

    public static File getHeadPhotoFileRaw()
    {
        File file = new File(getHeadPhotoDir() + "user_photo_raw.jpg");
        return file;
    }

    public static File getWallPaperFile()
    {
        File file = new File(getHeadPhotoDir() + "wallpaper.jpg");
        return file;
    }

    public static void saveCutBitmapForCache(Context context, Bitmap bitmap) {
        File file = new File(getHeadPhotoDir() + "user_photo_raw.jpg");
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int readPictureDegree(String path)
    {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt("Orientation", 1);
            switch (orientation) {
                case 6:
                    degree = 90;
                    break;
                case 3:
                    degree = 180;
                    break;
                case 8:
                    degree = 270;
                case 4:
                case 5:
                case 7: } } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap rotaingImageView(int angle, Bitmap bitmap)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);

        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    public static boolean deleteFile(String filePath)
    {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        if (!(file.exists())) {
            Log.w("FileUtil", "the file is not exist while delete the file");
            return false;
        }

        return deleteDir(file);
    }

    private static boolean deleteDir(File dir)
    {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null)
            {
                for (int i = 0; i < children.length; ++i) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!(success)) {
                        return false;
                    }
                }
            }
        }

        if ((!(dir.canRead())) || (!(dir.canWrite()))) {
            Log.w("FileUtil", "has no permission to can or write while delete the file");
            return false;
        }

        return dir.delete();
    }

    public static Bitmap getBitmapFromFile(String url)
    {
        Bitmap bitmap = null;
        String fileName = MD5.getMD5Str(url);
        if (fileName == null)
            return null;
        String filePath = ALBUM_PATH + "/" + fileName;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    public static void deleteTempAndRaw()
    {
        deleteFile(getHeadPhotoDir() + "user_photo_temp.jpg");
        deleteFile(getHeadPhotoDir() + "user_photo_raw.jpg");
    }

    public static void deleteTempLogin() {
        deleteFile(getHeadPhotoDir() + "login.txt");
    }

}
