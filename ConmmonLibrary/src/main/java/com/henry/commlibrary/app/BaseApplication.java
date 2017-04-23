package com.henry.commlibrary.app;

import android.app.Application;

import com.henry.commlibrary.utils.CrashHandler;

/**
 * Date: 17-4-23 下午5:16
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //此处为应用设置异常处理，然后程序才能捕获未处理异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}
