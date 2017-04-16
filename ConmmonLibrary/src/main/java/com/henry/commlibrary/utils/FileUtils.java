package com.henry.commlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

/**
 * Date: 2016/10/17. 16:01
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 文件辅助类
 */
public class FileUtils {
    public static boolean checkSDcard() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /**
     * 保存文件到缓存
     *
     * @param fileData
     * @param folderPath
     * @param fileName
     */
    public static void saveFileCache(byte[] fileData, String folderPath, String fileName) {
        File folder = new File(folderPath);
        folder.mkdirs();
        File file = new File(folderPath, fileName);
        ByteArrayInputStream is = new ByteArrayInputStream(fileData);
        FileOutputStream os = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
                os = new FileOutputStream(file);
                byte[] e = new byte[1024];
                boolean len = false;

                int len1;
                while (-1 != (len1 = is.read(e))) {
                    os.write(e, 0, len1);
                }

                os.flush();
            } catch (Exception var12) {
                throw new RuntimeException(FileUtils.class.getClass().getName(), var12);
            } finally {
                closeIO(new Closeable[]{is, os});
            }
        }

    }

    /**
     * 获取保存文件
     *
     * @param folderPath
     * @param fileNmae
     * @return
     */
    public static File getSaveFile(String folderPath, String fileNmae) {
        File file = new File(getSavePath(folderPath) + File.separator + fileNmae);

        try {
            file.createNewFile();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return file;
    }

    /**
     * @param folderName
     * @return
     */
    public static String getSavePath(String folderName) {
        return getSaveFolder(folderName).getAbsolutePath();
    }

    /**
     * @param folderName
     * @return
     */
    public static File getSaveFolder(String folderName) {
        File file = new File(getSDCardPath() + File.separator + folderName + File.separator);
        file.mkdirs();
        return file;
    }

    /**
     * 获取SD路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 输入流转换为byte
     *
     * @param inStream
     * @return
     */
    public static final byte[] input2byte(InputStream inStream) {
        if (inStream == null) {
            return null;
        } else {
            byte[] in2b = null;
            BufferedInputStream in = new BufferedInputStream(inStream);
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            boolean rc = false;

            try {
                int rc1;
                while ((rc1 = in.read()) != -1) {
                    swapStream.write(rc1);
                }

                in2b = swapStream.toByteArray();
            } catch (IOException var9) {
                var9.printStackTrace();
            } finally {
                closeIO(new Closeable[]{inStream, in, swapStream});
            }

            return in2b;
        }
    }

    /**
     * uri转换为文件
     *
     * @param aty
     * @param uri
     * @return
     */
    public static File uri2File(Activity aty, Uri uri) {
        String[] projection;
        if (Build.VERSION.SDK_INT < 11) {
            projection = new String[]{"_data"};
            Cursor loader1 = aty.managedQuery(uri, projection, (String) null, (String[]) null, (String) null);
            int cursor1 = loader1.getColumnIndexOrThrow("_data");
            loader1.moveToFirst();
            String column_index1 = loader1.getString(cursor1);
            return new File(column_index1);
        } else {
            projection = new String[]{"_data"};
            CursorLoader loader = new CursorLoader(aty, uri, projection, (String) null, (String[]) null, (String) null);
            Cursor cursor = loader.loadInBackground();
            int column_index = cursor.getColumnIndexOrThrow("_data");
            cursor.moveToFirst();
            return new File(cursor.getString(column_index));
        }
    }

    /**
     * 复制文件
     *
     * @param from
     * @param to
     */
    public static void copyFile(File from, File to) {
        if (from != null && from.exists()) {
            if (to != null) {
                FileInputStream is = null;
                FileOutputStream os = null;

                try {
                    is = new FileInputStream(from);
                    if (!to.exists()) {
                        to.createNewFile();
                    }

                    os = new FileOutputStream(to);
                    copyFileFast(is, os);
                } catch (Exception var8) {
                    throw new RuntimeException(FileUtils.class.getClass().getName(), var8);
                } finally {
                    closeIO(new Closeable[]{is, os});
                }

            }
        }
    }

    /**
     * @param is
     * @param os
     * @throws IOException
     */
    public static void copyFileFast(FileInputStream is, FileOutputStream os) throws IOException {
        FileChannel in = is.getChannel();
        FileChannel out = os.getChannel();
        in.transferTo(0L, in.size(), out);
    }

    /**
     * @param closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables != null && closeables.length > 0) {
            Closeable[] var4 = closeables;
            int var3 = closeables.length;

            for (int var2 = 0; var2 < var3; ++var2) {
                Closeable cb = var4[var2];

                try {
                    if (cb != null) {
                        cb.close();
                    }
                } catch (IOException var6) {
                    throw new RuntimeException(FileUtils.class.getClass().getName(), var6);
                }
            }

        }
    }

    /**
     * 图片格式转换为文件形式
     *
     * @param bitmap
     * @param filePath
     * @return
     */
    public static boolean bitmapToFile(Bitmap bitmap, String filePath) {
        boolean isSuccess = false;
        if (bitmap == null) {
            return isSuccess;
        } else {
            File file = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }

            BufferedOutputStream out = null;

            try {
                out = new BufferedOutputStream(new FileOutputStream(filePath), 8192);
                isSuccess = bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (FileNotFoundException var9) {
                var9.printStackTrace();
            } finally {
                closeIO(new Closeable[]{out});
            }

            return isSuccess;
        }
    }

    /**
     * 读取文件
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        FileInputStream is = null;

        try {
            is = new FileInputStream(filePath);
        } catch (Exception var3) {
            throw new RuntimeException(FileUtils.class.getName() + "readFile---->" + filePath + " not found");
        }

        return inputStream2String(is);
    }

    /**
     * 从Asset中读取文件
     *
     * @param context
     * @param name
     * @return
     */
    public static String readFileFromAssets(Context context, String name) {
        InputStream is = null;

        try {
            is = context.getResources().getAssets().open(name);
        } catch (Exception var4) {
            throw new RuntimeException(FileUtils.class.getName() + ".readFileFromAssets---->" + name + " not found");
        }

        return inputStream2String(is);
    }

    /**
     * 输入流-->文件
     *
     * @param is
     * @return
     */
    public static String inputStream2String(InputStream is) {
        if (is == null) {
            return null;
        } else {
            StringBuilder resultSb = null;

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                resultSb = new StringBuilder();

                String len;
                while ((len = br.readLine()) != null) {
                    resultSb.append(len);
                }
            } catch (Exception var7) {
                ;
            } finally {
                closeIO(new Closeable[]{is});
            }

            return resultSb == null ? null : resultSb.toString();
        }
    }
}
