package com.henry.commlibrary.view.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henry.commlibrary.view.recycleview.interfac.OnRecyclerItemClickListener;
import com.henry.commlibrary.view.recycleview.interfac.OnRecyclerLongItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Date: 2017/5/31. 16:50
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:RecyclerView.Adapter基类
 */
public class BaseRecyclerAdapter<Holder extends RecyclerHolder, T> extends RecyclerView.Adapter<Holder> implements
        View.OnClickListener, View.OnLongClickListener {

    /**
     * 选中位置
     */
    protected int selectedPostion = 0;

    /**
     * adapter 数据集
     */
    protected List<T> datas;
    /**
     * Context
     */
    protected Context context;
    /**
     * 用于解析布局
     */
    protected LayoutInflater inflater;
    /**
     * Item点击事件
     */
    private OnRecyclerItemClickListener mOnItemClickListener = null;
    /**
     * Item 长按事件
     */
    private OnRecyclerLongItemClickListener onRecyclerLongItemClickListener = null;

    /**
     * 构造方法
     *
     * @param context
     */
    public BaseRecyclerAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    /**
     * 构造方法
     *
     * @param context
     * @param datas
     */
    public BaseRecyclerAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 创建新View，被LayoutManager所调用
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 将数据与界面进行绑定的操作
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    public List<T> getDatas() {
        return datas;
    }

    /**
     * 获取数据的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    /**
     * 刷新列表
     *
     * @param datas 新的数据
     */
    public void refresh(List<T> datas) {
        this.datas.clear();
        this.datas = datas;
        notifyDataSetChanged();
    }

    /**
     * 获取列表某个位置的数据
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return datas.get(position);
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    /**
     * 在列表末尾添加数据
     *
     * @param data
     */
    public void append(T data) {
        this.datas.add(data);
        notifyItemInserted(this.datas.size() - 1);
    }

    /**
     * 在列表指定位置添加数据
     *
     * @param position
     * @param data
     */
    public void append(int position, T data) {
        this.datas.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 添加一组数据
     *
     * @param datalist
     */
    public void append(List<T> datalist) {
        int oldSize = this.datas.size();
        this.datas.addAll(datalist);
        notifyItemRangeInserted(oldSize, datalist.size());
    }

    /**
     * 移除一组数据
     *
     * @param item
     * @return
     */
    public T remove(T item) {
        this.datas.remove(item);
        notifyItemRemoved(this.datas.size() + 1);
        return item;
    }

    /**
     * 移除某个位置的数据
     *
     * @param position
     * @return
     */
    public T remove(int position) {
        T item = this.datas.get(position);
        this.datas.remove(position);
        notifyItemRemoved(position);
        return item;
    }

    /**
     * 清空列表
     */
    public void removeAll() {
        this.datas.clear();
        notifyDataSetChanged();
    }

    /**
     * 初始化Item点击事件，在子adapter中调用
     *
     * @param view
     */
    public void initItemClickListener(View view) {
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //使用getTag方法获取数据
        if (mOnItemClickListener != null)
            mOnItemClickListener.onRecyclerClick(v, datas, (Integer) v.getTag());
    }

    /**
     * 设置点击监听事件，调用initItemClickListener后有效
     *
     * @param listener
     */
    public void addOnItemClickListener(OnRecyclerItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
     * 设置长按监听事件，调用initItemClickListener后有效
     *
     * @param longItemClickListener
     */
    public void addOnLongItemClickListener(OnRecyclerLongItemClickListener longItemClickListener) {
        this.onRecyclerLongItemClickListener = longItemClickListener;
    }

    @Override
    public boolean onLongClick(View v) {
        //使用getTag方法获取数据
        if (onRecyclerLongItemClickListener != null)
            onRecyclerLongItemClickListener.onRecyclerLongClick(v, datas, (Integer) v.getTag());
        return false;
    }
}
