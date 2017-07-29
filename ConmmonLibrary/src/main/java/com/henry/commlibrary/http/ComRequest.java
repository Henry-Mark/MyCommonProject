package com.henry.commlibrary.http;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.henry.commlibrary.view.LoadingDialog;

import java.io.File;
import java.util.Map;


/**
 * Date: 2017/6/12. 15:05
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:请求的通用类
 */
public abstract class ComRequest {

    protected Thread thread;

    protected Gson gson = new Gson();

    protected IComResult result = null;
    protected Activity activity;

    LoadingDialog loadingDialog = null;

    public ComRequest(Activity activity) {
        this.activity = activity;
    }

    public void addResultListener(IComResult result) {
        this.result = result;
    }


    public abstract void start(Map<String, String> map);

    public void start(Map<String, String> map, File[] files) {
    }

    ;

    /**
     * 取消线程
     */
    public void cancel() {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
    }


    /**
     * 显示loading对话框
     */
    protected void showLoadingDialog() {
        showLoadingDialog(null);
    }


    /**
     * 加载对话框，显示的字，如（loading...）
     *
     * @param string
     */
    protected void showLoadingDialog(String string) {
        loadingDialog = new LoadingDialog(string);
        loadingDialog.show(((AppCompatActivity) activity).getSupportFragmentManager(), "loading");
    }


    /***
     * 取消Loading对话框
     */
    protected void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.getDialog() != null && loadingDialog.getDialog().isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
