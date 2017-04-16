package com.henry.commlibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Date: 16-9-24 下午9:51
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: Toast统一管理类
 */
public class ToastUtils {

    public static boolean isShow = true;

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param resId
     */
    public static void showShort(Context context, int resId) {
        if (isShow)
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param resId
     */
    public static void showLong(Context context, int resId) {
        if (isShow)
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param resId
     * @param duration 单位ｍｓ
     */
    public static void show(Context context, int resId, int duration) {
        if (isShow)
            Toast.makeText(context, resId, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration 单位ｍｓ
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }
}
