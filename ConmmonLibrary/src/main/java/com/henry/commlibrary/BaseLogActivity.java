package com.henry.commlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

/**
 * Date: 2016/10/13. 13:48
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: Activity打印日志基类
 */
public class BaseLogActivity extends AppCompatActivity {

    protected String TAG = "BaseActivity";
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 获取当前类名
        TAG = getClass().getName();
        Log.i(TAG, "onCreate... savedInstanceState = " + savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy...");
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyLongPress...");
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult... resultCode = " + resultCode + ", data = " + data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
