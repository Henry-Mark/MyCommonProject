package com.henry.commlibrary.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * author : Henry
 * time :  2017/8/1 14:30
 * email : heneymark@gmail.com
 * description :有两个按钮的提示对话框
 */
@SuppressLint("ValidFragment")
public class PromptDialogFragment2 extends DialogFragment {

    String title;
    String msg;
    int flag;
    boolean isCancelable = true;
    OnConformListener listener = null;

    /**
     * 构造方法
     *
     * @param title
     * @param msg
     * @param flag
     */
    public PromptDialogFragment2(String title, String msg, int flag) {
        this(title, msg, flag, true);
    }

    public PromptDialogFragment2(String title, String msg, int flag, boolean isCancelable) {
        this.title = title;
        this.msg = msg;
        this.flag = flag;
        this.isCancelable = isCancelable;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onConform(flag);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        setCancelable(isCancelable);
        return builder.create();
    }

    /**
     * 添加监听
     *
     * @param listener
     */
    public void addOnListener(OnConformListener listener) {
        this.listener = listener;
    }

    public interface OnConformListener {
        void onConform(int flag);
    }
}
