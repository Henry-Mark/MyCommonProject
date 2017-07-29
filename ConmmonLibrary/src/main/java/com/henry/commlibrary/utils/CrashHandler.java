package com.henry.commlibrary.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Date: 17-4-23 下午1:32
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 捕获运行时异常，并给用户良好的提示
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = CrashHandler.class.getSimpleName();
    private static final boolean DEBUG = true;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() +
            "/Android/data/liandisys.com.cn.intelligentlogistics/log/";

    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".txt";

    // 单例模式
    private static CrashHandler instance;

    // 程序Context对象
    private Context context;
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler defalutHandler;


    /**
     * 获取CrashHandler实例
     *
     * @return
     */
    public static CrashHandler getInstance() {
        if (instance == null) {
            synchronized (CrashHandler.class) {
                if (instance == null) {
                    instance = new CrashHandler();
                }
            }
        }
        return instance;
    }

    /**
     * 异常处理初始化
     *
     * @param context
     */
    public void init(Context context) {
        this.context = context;
        // 获取系统默认的UncaughtException处理器
        defalutHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     *
     * @param t 出现的未捕获异常的线程
     * @param e 未捕获异常，有了它，我们就可以得到异常信息
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        try {
            dumpExceptionToSDCard(e);
            uploadExceptionToServer();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        e.printStackTrace();


//
//        // 自定义错误处理
//        boolean res = handleException(e);
        if (defalutHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            defalutHandler.uncaughtException(t, e);
        } else {
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 导入异常信息到ＳＤ卡
     *
     * @param e
     */
    @TargetApi(Build.VERSION_CODES.N)
    private void dumpExceptionToSDCard(Throwable e) throws IOException {
        //如果ＳＤ卡不存在或无法使用，则无法把异常信息写入ＳＤ卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "sdcard unmounted,skip dump exception");
                return;
            }
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            e.printStackTrace(pw);
            pw.println();
            pw.println();
            pw.flush();
            pw.close();

        } catch (Exception ex) {

            Log.e(TAG, "dump crash info failed");
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageArchiveInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        if (pi != null) {
            pw.print("App Version: ");
            pw.print(pi.versionName);
            pw.print("_");
            pw.println(pi.versionCode);
        }

        //Android 版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        //手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);
        //手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);
        //ＣＰＵ架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }

    /**
     * 将异常发送到服务器
     */
    private void uploadExceptionToServer() {

    }

//    private boolean handleException(final Throwable ex) {
//        if (ex == null) {
//            return false;
//        }
//
//        new Thread() {
//
//            @Override
//            public void run() {
//                Looper.prepare();
//
//                ex.printStackTrace();
//                String err = "[" + ex.getMessage() + "]";
//                Toast.makeText(context, "程序出现异常." + err, Toast.LENGTH_LONG)
//                        .show();
//
//                Looper.loop();
//            }
//
//        }.start();
//
//        // 收集设备参数信息 \日志信息
//        String errInfo = collectDeviceInfo(context, ex);
//        // 保存日志文件
//        saveCrashInfo2File(errInfo);
//        return true;
//    }
//
//
//    public interface OnBeforeHandleExceptionListener {
//
//        void onBeforeHandleException();
//    }
}
