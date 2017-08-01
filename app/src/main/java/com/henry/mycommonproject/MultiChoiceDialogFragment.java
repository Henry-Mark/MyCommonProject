package com.henry.mycommonproject;

import android.annotation.SuppressLint;
import android.view.View;

import com.henry.commlibrary.fragment.base.BaseMultiChoiceDialogFragment;

import java.util.List;

/**
 * author : Henry
 * time :  2017/8/1 19:37
 * email : heneymark@gmail.com
 * description :
 */

public class MultiChoiceDialogFragment extends BaseMultiChoiceDialogFragment<String> {
    /**
     * 构造方法
     *
     * @param view  指定的View
     * @param list  列表
     * @param title 对话框标题
     */
    public MultiChoiceDialogFragment(View view, List<String> list, String title) {
        super(view, list, title);
    }

    @Override
    protected String[] getArrayFromList(List<String> list) {

        String[] str = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i);
        }
        return str;
    }
}
