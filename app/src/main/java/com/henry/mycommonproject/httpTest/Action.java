package com.henry.mycommonproject.httpTest;

import android.app.Activity;

import com.henry.commlibrary.http.ComAction;
import com.henry.commlibrary.http.HttpResult;
import com.henry.commlibrary.utils.LogUtils;

import java.io.IOException;
import java.util.Map;

/**
 * author : Henry
 * time :  2017/7/29 15:45
 * email : heneymark@gmail.com
 * description :
 */

public class Action extends ComAction {

    public Action(Activity activity) {
        super(activity);
    }


    /**
     * 登录接口
     *
     * @param map
     * @return
     * @throws IOException
     */
    public String login(Map<String, String> map) throws IOException {
        HttpResult result = new HttpResult(activity);
        String res = result.post("http://172.16.50.213:8081/ionic/login", map);
        LogUtils.d(TAG, "login result = " + res);
        return res;
    }
}
