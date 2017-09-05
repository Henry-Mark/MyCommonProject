package com.henry.commlibrary.fragment.dialog.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.util.List;

/**
 * author : Henry
 * time :  2017/8/1 17:44
 * email : heneymark@gmail.com
 * description :对话框列表父类
 */

@SuppressLint("ValidFragment")
public abstract class BaseListDialogFragment<T> extends DialogFragment {
    protected String title;
    protected List<T> list;
    protected View view;
    protected String[] items;
    OnItemClickListener listener;

    /**
     * 构造方法
     *
     * @param view  指定的View
     * @param list  列表
     * @param title 对话框标题
     */
    public BaseListDialogFragment(View view, List<T> list, String title) {
        this.title = title;
        this.list = list;
        this.view = view;
        items = getArrayFromList(list);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onClicked(view, list.get(which));
                        }
                    }
                }).create();
        return dialog;
    }

    /**
     * 添加选中监听
     *
     * @param listener
     */
    public void addClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    /**
     * 从列表中取得数组，用于展示
     *
     * @param list
     * @return
     */
    protected abstract String[] getArrayFromList(List<T> list);

    /**
     * 点击监听事件
     *
     * @param <T>
     */
    public interface OnItemClickListener<T> {
        void onClicked(View view, T t);
    }

}
