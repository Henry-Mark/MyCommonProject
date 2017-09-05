package com.henry.commlibrary.fragment.dialog.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Henry
 * time :  2017/8/1 19:16
 * email : heneymark@gmail.com
 * description :多选对话框父类
 */

public abstract class BaseMultiChoiceDialogFragment<T> extends DialogFragment {

    protected String title;
    protected List<T> list;
    protected View view;
    protected String[] items;
    // 设置默认选中的选项，全为false默认均未选中
    private boolean[] chooseItems;
    private List<T> listChoosed;

    private OnMultiChoiceListener listener;

    /**
     * 构造方法
     *
     * @param view  指定的View
     * @param list  列表
     * @param title 对话框标题
     */
    public BaseMultiChoiceDialogFragment(View view, List<T> list, String title) {
        this.title = title;
        this.list = list;
        this.view = view;
        items = getArrayFromList(list);
    }

    /**
     * 从列表中取得数组，用于展示
     *
     * @param list
     * @return
     */
    protected abstract String[] getArrayFromList(List<T> list);

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        chooseItems = new boolean[list.size()];
        listChoosed = new ArrayList<>();
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle(title)
                .setMultiChoiceItems(items, chooseItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            listChoosed.add(list.get(which));
                        } else {
                            listChoosed.remove(list.get(which));
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onMultiSelected(view, listChoosed);
                        }
                    }
                })
                .create();

        return dialog;
    }

    /**
     * 添加多选的监听
     *
     * @param listener
     */
    public void addMultiChoiceListener(OnMultiChoiceListener listener) {
        this.listener = listener;
    }


    /**
     * 多选接口
     *
     * @param <T>
     */
    public interface OnMultiChoiceListener<T> {
        void onMultiSelected(View view, List<T> list);
    }
}
