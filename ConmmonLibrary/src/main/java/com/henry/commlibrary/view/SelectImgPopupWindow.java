package com.henry.commlibrary.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.henry.commlibrary.R;
import com.henry.commlibrary.utils.ToastUtils;


/**
 * author : Henry
 * time :  2017/7/13 15:49
 * email : heneymark@gmail.com
 * description :自定义底部图片选择控件
 */

public class SelectImgPopupWindow extends Activity implements View.OnClickListener {

    private LinearLayout mLayout;
    private Button mTakePhoto, mPickPhoto, mCancel;
    private Intent intent;

    private int FLAG_TAKE_PHOTO = 1;
    private int FLAG_PICK_PHOTO = 2;

    private String IMG_TYPE = "image/*";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_img_selected);
        bindView();
        bindEvent();
        bindData();
    }

    protected void bindView() {
        mTakePhoto = (Button) findViewById(R.id.pop_bt_take_photo);
        mPickPhoto = (Button) findViewById(R.id.pop_bt_pick_photo);
        mCancel = (Button) findViewById(R.id.pop_bt_cancel);
        mLayout = (LinearLayout) findViewById(R.id.pop_layout);
    }

    protected void bindEvent() {
        mPickPhoto.setOnClickListener(this);
        mTakePhoto.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mLayout.setOnClickListener(this);
    }

    protected void bindData() {
        intent = getIntent();

    }

    /**
     * 拍照
     */
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, FLAG_TAKE_PHOTO);
    }

    /**
     * 选择图片
     */
    private void pickPhoto() {
        Intent intent = new Intent();
        intent.setType(IMG_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, FLAG_PICK_PHOTO);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.pop_bt_cancel) {
            finish();

        } else if (i == R.id.pop_bt_pick_photo) {
            pickPhoto();

        } else if (i == R.id.pop_bt_take_photo) {
            takePhoto();

        } else if (i == R.id.pop_layout) {
            ToastUtils.show(getApplicationContext(), "提示：点击窗口外部关闭窗口！", Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 选择完, 拍照,或者选择图片后调用的方法
        if (resultCode != RESULT_OK) {
            return;
        }
        //选择完或者拍完照后会在这里处理，然后我们继续使用setResult返回Intent以便可以传递数据和调用
        if (data.getExtras() != null)
            intent.putExtras(data.getExtras());   //拍照得到的图片
        if (data.getData() != null)
            intent.setData(data.getData());   //选择图片得到的数据, 里面有uri
        setResult(RESULT_OK, intent);     // 返回到下面的Activity
        finish();
    }
}
