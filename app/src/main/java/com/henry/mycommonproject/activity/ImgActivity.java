package com.henry.mycommonproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.henry.commlibrary.view.SelectImgPopupWindow;
import com.henry.mycommonproject.R;

import java.io.IOException;

public class ImgActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_IMG = 10;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        imageView = (ImageView) findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ImgActivity.this, SelectImgPopupWindow.class), REQUEST_CODE_IMG);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            //照片
            case REQUEST_CODE_IMG:
                Bitmap imgBitmap;

                //取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意

                Uri imgUri = data.getData();  //选择图片有uri
                //返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
                if (imgUri != null) {
                    try {
                        imgBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                        if (imgBitmap != null) {
                            imageView.setImageBitmap(imgBitmap);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                } else {
                    Bundle extras = data.getExtras(); //拍照没有uri
                    if (extras != null) {
                        //这里是有些拍照后的图片是直接存放到Bundle中的所以我们可以从这里面获取Bitmap图片
                        imgBitmap = extras.getParcelable("data");
                        if (imgBitmap != null) {
                            imageView.setImageBitmap(imgBitmap);
                        }
                    }
                }

                break;
            default:
                break;
        }
    }
}
