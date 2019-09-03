package com.zucc.smartfurniture.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by fujiayi on 2017/5/19.
 */

public class FileUtil {

    public static boolean makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            return file.mkdirs();
        } else {
            return true;
        }
    }

    public static String getContentFromAssetsFile(AssetManager assets, String source) {
        InputStream is = null;
        FileOutputStream fos = null;
        String result = "";
        try {
            is = assets.open(source);
            int lenght = is.available();
            byte[] buffer = new byte[lenght];
            is.read(buffer);
            result = new String(buffer, "utf8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean copyFromAssets(AssetManager assets, String source, String dest,
                                         boolean isCover) throws IOException {
        File file = new File(dest);
        boolean isCopyed = false;
        if (isCover || (!isCover && !file.exists())) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = assets.open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
                isCopyed = true;
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                    }
                }
            }

        }
        return isCopyed;
    }
    public static void write(Context context, Object obj, String fileName) {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream    oout = new ObjectOutputStream(bout);
            oout.writeObject(obj);
            oout.flush();
            oout.close();
            bout.close();
            byte[] b = bout.toByteArray();
            File file = new File(context.getFilesDir(), fileName);
            FileOutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 从本地文件读取
     * @param context
     * @param fileName
     * @return
     */
    public static Object read(Context context, String fileName) {
        // 拿出持久化数据
        Object obj = null;
        try {
            File              file = new File(context.getFilesDir(), fileName);
            FileInputStream   in   = new FileInputStream(file);
            ObjectInputStream oin  = new ObjectInputStream(in);
            obj = oin.readObject();
            in.close();
            oin.close();
        } catch (Exception e) {
        }
        return obj;
    }
}
