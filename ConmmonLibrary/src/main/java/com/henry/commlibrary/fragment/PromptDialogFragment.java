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
 * time :  2017/7/25 11:37
 * email : heneymark@gmail.com
 * description :提示对话框，单个按钮
 */
@SuppressLint("ValidFragment")
public class PromptDialogFragment extends DialogFragment {

    OnConformListener listener = null;
    String title;
    String msg;
    String btnName;
    int flag;

    public PromptDialogFragment(String title, String msg, String btnName, int flag) {
        this.title = title;
        this.msg = msg;
        this.btnName = btnName;
        this.flag = flag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.setPositiveButton(btnName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onConform(flag);
                }
            }
        });

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
