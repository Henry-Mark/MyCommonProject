package com.henry.mycommonproject;

import android.annotation.SuppressLint;
import android.view.View;

import com.henry.commlibrary.fragment.base.BaseListDialogFragment;

import java.util.List;

/**
 * author : Henry
 * time :  2017/8/1 18:49
 * email : heneymark@gmail.com
 * description :
 */

@SuppressLint("ValidFragment")
public class ListDialogFragment extends BaseListDialogFragment<String> {
    public ListDialogFragment(View view, List list, String title) {
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
