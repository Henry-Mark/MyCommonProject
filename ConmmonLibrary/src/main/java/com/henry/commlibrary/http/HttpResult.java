package com.henry.commlibrary.http;

import android.app.Activity;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Response;

/**
 * Date: 2017/6/12. 11:41
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:Http请求结果
 */
public class HttpResult {
    OkHttpManager okHttpManager;
    Activity activity;

    public HttpResult(Activity activity) {
        this.activity = activity;
        okHttpManager = OkHttpManager.getInstance();
    }

    /**
     * Get请求结果
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String get(String url) throws IOException {
        Response response = okHttpManager.get(url);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            showLog(response);
            return null;
        }
    }

    /**
     * 单个键值对post请求
     *
     * @param url
     * @param key
     * @param value
     * @return
     * @throws IOException
     */
    public String post(String url, String key, String value) throws IOException {
        Response response = okHttpManager.post(url, key, value);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            showLog(response);
            return null;
        }
    }

    /**
     * 多键值对post请求
     *
     * @param url
     * @param map
     * @return
     * @throws IOException
     */
    public String post(String url, Map<String, String> map) throws IOException {
        Response response = okHttpManager.post(url, map);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            showLog(response);
            return null;
        }
    }

    /**
     * 上传多个文件和参数
     *
     * @param url
     * @param files
     * @param map
     * @return
     * @throws IOException
     */
    public String upLoadMultiFiles(String url, File[] files, Map<String, String> map) throws IOException {
        Response response = okHttpManager.upLoadMultiFiles(url, files, map);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            showLog(response);
            return null;
        }
    }

    /**
     * 打印错误日志
     *
     * @param response
     */
    private void showLog(final Response response) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), "errCode: " + response.code() + "\n message: " +
                        response.message(), Toast
                        .LENGTH_SHORT).show();
            }
        });

    }

}
