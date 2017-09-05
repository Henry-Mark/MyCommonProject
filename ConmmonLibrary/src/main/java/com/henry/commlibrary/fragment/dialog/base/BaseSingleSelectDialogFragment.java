package com.henry.commlibrary.fragment.dialog.base;

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
 * time :  2017/8/1 16:58
 * email : heneymark@gmail.com
 * description :单选对话框父类
 */
public abstract class BaseSingleSelectDialogFragment<T> extends DialogFragment {

    protected String title;
    protected List<T> list;
    protected View view;
    protected String[] items;
    protected OnItemSelectListener listener = null;

    /**
     * 构造方法
     *
     * @param view  指定的View
     * @param list  列表
     * @param title 对话框标题
     */
    public BaseSingleSelectDialogFragment(View view, List<T> list, String title) {
        this.title = title;
        this.list = list;
        this.view = view;
        items = getArrayFromList(list);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle(title)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onSelected(view, list.get(which));
                        }
                        dialog.dismiss();
                    }
                }).create();
        return dialog;
    }

    /**
     * 添加选中监听
     *
     * @param onItemSelectListener
     */
    public void addSelectedListener(OnItemSelectListener onItemSelectListener) {
        listener = onItemSelectListener;
    }

    /**
     * 从列表中取得数组，用于展示
     *
     * @param list
     * @return
     */
    protected abstract String[] getArrayFromList(List<T> list);

    /**
     * 选择监听
     *
     * @param <T>
     */
    public interface OnItemSelectListener<T> {
        void onSelected(View view, T t);
    }
}
