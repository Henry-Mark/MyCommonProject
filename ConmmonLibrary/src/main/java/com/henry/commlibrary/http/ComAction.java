package com.henry.commlibrary.http;

import android.app.Activity;

/**
 * Date: 2017/7/6. 9:31
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:网络请求父类
 */
public class ComAction {

    protected String TAG = "Action";
    protected Activity activity;

    public ComAction(Activity activity) {
        this.activity = activity;
        TAG = getClass().getSimpleName();
    }
}
