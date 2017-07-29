package com.henry.commlibrary.http;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Date: 2017/6/12. 10:27
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class OkHttpManager {
    public static final String TAG = "OkHttpManger";
    private static OkHttpClient okHttpClient;
    private static OkHttpManager manager;

    private OkHttpManager() {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpManager getInstance() {
        synchronized (OkHttpManager.class) {
            if (manager == null) {
                return new OkHttpManager();
            }
        }
        return manager;
    }

    /**
     * Get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public Response get(String url) throws IOException {
        //通过Builder辅助类构建一个Request对象
        Request request = new Request.Builder().get().url(url).build();
        //通过同步执行获取一个Response对象
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 提交单个键值对的post请求方法
     *
     * @param url
     * @param key
     * @param value
     * @return
     * @throws IOException
     */
    public Response post(String url, String key, String value) throws IOException {
        //提交键值对需要用到FormBody,因为FormBody是继承RequestBody的,所以拥有RequestBody的一切属性
        FormBody formBody = new FormBody.Builder()
                //添加键值对
                .add(key, value)
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
        return okHttpClient.newCall(request).execute();
    }

    /**
     * 提交多个键值对的post请求方法
     *
     * @param url
     * @param map
     * @return
     * @throws IOException
     */
    public Response post(String url, Map<String, String> map) throws IOException {
        FormBody.Builder build = new FormBody.Builder();
        if (map != null) {
            //增强for循环遍历
            for (Map.Entry<String, String> entry : map.entrySet()) {
                build.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody formBody = build.build();

        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
        return okHttpClient.newCall(request).execute();
    }

    /**
     * 上传单个文件
     *
     * @param url
     * @param file
     * @return
     * @throws IOException
     */
    public Response uploadfile(String url, File file) throws IOException {
        // //提交键值对需要用到MultipartBody,因为MultipartBody是继承RequestBody的,
        // 所以拥有RequestBody的一切属性,类似于javaEE中的表单提交
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        RequestBody fileBody = RequestBody.create(
                MediaType.parse(getMediaType(file.getName())), file);
        //这里的uploadfile是文件上传的标识,用于服务器识别文件
        builder.addFormDataPart("uploadfile", file.getName(), fileBody);
        MultipartBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return okHttpClient.newCall(request).execute();
    }

    /**
     * 上传多个文件
     *
     * @param url
     * @param files
     * @return
     * @throws IOException
     */
    public Response uploadfiles(String url, File[] files) throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < files.length; i++) {
            RequestBody fileBody = RequestBody.create(MediaType.parse(getMediaType(files[i].getName())), files[i]);
            builder.addFormDataPart("uploadfile", files[i].getName(), fileBody);
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        return okHttpClient.newCall(request).execute();
    }

    /**
     * 根据文件的名称判断文件的MediaType
     */
    private String getMediaType(String fileName) {
        FileNameMap map = URLConnection.getFileNameMap();
        String contentTypeFor = map.getContentTypeFor(fileName);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
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
    public Response upLoadMultiFiles(String url, File[] files, Map<String, String> map) throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加文件
        if (files.length != 0) {
            for (int i = 0; i < files.length; i++) {
                RequestBody fileBody = RequestBody.create(
                        MediaType.parse(getMediaType(files[i].getName())), files[i]);
                builder.addFormDataPart("uploadfile", files[i].getName(), fileBody);
            }
        }
        //添加参数
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        return okHttpClient.newCall(request).execute();
    }
}
