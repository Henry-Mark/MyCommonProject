package com.henry.mycommonproject;

import android.os.Bundle;
import android.widget.AbsListView;

import com.henry.commlibrary.activity.BaseLogActivity;

public class MainActivity extends BaseLogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
