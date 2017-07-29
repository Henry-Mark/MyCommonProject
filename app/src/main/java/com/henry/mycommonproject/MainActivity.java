package com.henry.mycommonproject;

import android.os.Bundle;
import android.view.View;

import com.henry.commlibrary.activity.BaseLogActivity;
import com.henry.mycommonproject.httpTest.LoginRequeset;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseLogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testHttp();
            }
        });
    }


    private void testHttp() {
        LoginRequeset requeset = new LoginRequeset(this,"加载中...",false);
        Map map = new HashMap();
        map.put("phoneNumber", "12345678910");
        map.put("password", "111111");
        map.put("serial", "12345678910");
        requeset.start(map);
    }
}
