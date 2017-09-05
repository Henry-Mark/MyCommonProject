package com.henry.mycommonproject;

import android.app.Activity;
import android.widget.TextView;

import com.henry.commlibrary.view.recycleview.BasePopupWindow;

/**
 * author : Henry
 * time :  2017/9/5 17:04
 * email : heneymark@gmail.com
 * description :
 */

public class TestPopupWindow extends BasePopupWindow {

    TextView mText1, mText2;

    public TestPopupWindow(Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayout() {
        return R.layout.popup_test;
    }

    @Override
    protected int getPopupTopView() {
        return R.id.layout;
    }

    @Override
    protected void bindView() {
        mText1 = getViewById(R.id.text1);
        mText2 = getViewById(R.id.text2);
    }

    @Override
    protected void dealEvent() {
        mText2.setText("My Test PopupWindow");
    }
}
