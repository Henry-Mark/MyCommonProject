package com.henry.mycommonproject;

import android.os.Bundle;
import android.view.View;

import com.henry.commlibrary.activity.BaseLogActivity;

public class MainActivity extends BaseLogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("zi ding yi yi chang:zi ji pao chu de yi chang ");
            }
        });
    }
}
