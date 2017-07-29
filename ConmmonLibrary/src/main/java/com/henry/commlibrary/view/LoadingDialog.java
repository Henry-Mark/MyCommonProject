package com.henry.commlibrary.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.henry.commlibrary.R;


/**
 * Date: 2017/6/2. 13:59
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:loading对话框
 */
@SuppressLint("ValidFragment")
public class LoadingDialog extends DialogFragment {

    private ImageView img;
    private TextView text;
    private String string;

    public LoadingDialog() {
        this(null);
    }


    public LoadingDialog(String string) {
        this.string = string;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_loading, null);
        img = (ImageView) view.findViewById(R.id.loading);
        text = (TextView) view.findViewById(R.id.text);
        if (string != null) {
            text.setText(string);
        }
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_loading);
        img.setAnimation(animation);
        img.startAnimation(animation);
        builder.setView(view);
        Dialog dialog = builder.create();
        return dialog;
    }

}
