package com.henry.commlibrary.http;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Date: 2017/7/6. 10:37
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:异步请求
 */
public class ComAsycnRequest extends ComRequest {

    //加载对话框是否加载
    protected boolean isShow = true;
    //加载时，显示的字
    protected String msg;
    private HttpAsyncTask task;

    public ComAsycnRequest(Activity activity) {
        super(activity);
    }

    /**
     * 构造方法
     *
     * @param activity
     * @param loadingMsg 加载信息
     * @param isShow     是否显示加载对话框，默认显示
     */
    public ComAsycnRequest(Activity activity, String loadingMsg, boolean isShow) {
        super(activity);
        this.isShow = isShow;
        this.msg = loadingMsg;
    }

    @Override
    public void start(Map<String, String> map) {
        task = new HttpAsyncTask();
        task.execute(map);
    }

    @Override
    public void start(Map<String, String> map, File[] files) {
        super.start(map, files);
        task = new HttpAsyncTask();
        task.execute(map, files);
    }

    /**
     * @param map
     * @return
     * @throws IOException
     */
    protected String getResponse(Map<String, String> map) throws IOException {
        return null;
    }

    /**
     * @param map
     * @param files
     * @return
     * @throws IOException
     */
    protected String getResponse(Map<String, String> map, File[] files) throws IOException {
        return null;
    }

    /**
     * http请求结果
     *
     * @param s
     */
    protected void onResult(String s) {

    }

    /**
     * 取消请求任务
     */
    public void cancel() {
        if (task != null && task.getStatus() == AsyncTask.Status.RUNNING) {
            task.cancel(true);
        }
    }

    class HttpAsyncTask extends AsyncTask<Object, Object, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (isShow) {
                showLoadingDialog(msg);
            }
        }

        @Override
        protected String doInBackground(Object... params) {
            String result = null;
            try {
                if (params != null && params.length == 1)
                    result = getResponse((Map<String, String>) params[0]);
                else if (params != null && params.length == 2)
                    result = getResponse((Map<String, String>) params[0], ((File[]) params[1]));
            } catch (IOException e) {
                e.printStackTrace();
                dismissLoadingDialog();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dismissLoadingDialog();
            if (!TextUtils.isEmpty(s)) {
                onResult(s);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            dismissLoadingDialog();
        }
    }
}
