package com.henry.commlibrary.view.recycleview.interfac;

import android.view.View;

import java.util.List;

/**
 * Date: 2017/6/1. 9:36
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:RecyclerView 长按事件的接口
 */
public interface OnRecyclerLongItemClickListener {
    /**
     * Item点击事件
     *
     * @param v        被点击的Item
     * @param datas    列表数据
     * @param position 点击的位置
     */
    void onRecyclerLongClick(View v, List datas, int position);
}
