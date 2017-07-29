package com.henry.mycommonproject.httpTest;

import android.app.Activity;

import com.henry.commlibrary.http.ComAsycnRequest;

import java.io.IOException;
import java.util.Map;

/**
 * author : Henry
 * time :  2017/7/29 15:48
 * email : heneymark@gmail.com
 * description :
 */

public class LoginRequeset extends ComAsycnRequest {


    public LoginRequeset(Activity activity, String loadingMsg, boolean isShow) {
        super(activity, loadingMsg, isShow);
    }

    @Override
    protected String getResponse(Map<String, String> map) throws IOException {
        return new Action(activity).login(map);
    }
}
