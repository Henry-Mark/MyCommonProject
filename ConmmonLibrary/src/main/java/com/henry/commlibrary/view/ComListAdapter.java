package com.henry.commlibrary.view;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Henry
 * time :  2017/9/11 14:30
 * email : heneymark@gmail.com
 * description :通用的ListView的baseAdapter适配器
 */

public abstract class ComListAdapter<T> extends BaseAdapter {

    //列表数据
    private ArrayList<T> mData;
    //布局
    private int mLayoutRes;

    public ComListAdapter() {

    }

    /**
     * 构造方法
     *
     * @param mData      实体列表
     * @param mLayoutRes 布局
     */
    public ComListAdapter(ArrayList<T> mData, int mLayoutRes) {
        this.mData = mData;
        this.mLayoutRes = mLayoutRes;
    }


    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.bind(parent.getContext(), convertView, parent, mLayoutRes
                , position);
        bindView(holder, (T) getItem(position));
        return holder.getItemView();
    }

    public abstract void bindView(ViewHolder holder, T t);

    /**
     * 添加一个元素
     *
     * @param data
     */
    public void add(T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }


    /**
     * 往特定位置，添加一个元素
     *
     * @param position
     * @param data
     */
    public void add(int position, T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }

    /**
     * 删除一个元素
     *
     * @param data
     */
    public void remove(T data) {
        if (mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除指定位置元素
     *
     * @param position
     */
    public void remove(int position) {
        if (mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    /**
     * 获取列表
     *
     * @return
     */
    public List<T> getDatas() {
        return mData;
    }

    /**
     * 清楚列表
     */
    public void clear() {
        if (mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 刷新列表
     *
     * @param list
     */
    public void refresh(ArrayList<T> list) {
        if (mData != null) {
            mData.clear();
        }
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        //存储ListView 的 item中的View
        private SparseArray<View> mViews;
        //存放convertView
        private View item;
        //游标
        private int position;
        //Context上下文
        private Context context;


        /**
         * 构造方法，完成相关初始化
         *
         * @param context   上下文
         * @param parent    父控件
         * @param layoutRes 布局
         */
        private ViewHolder(Context context, ViewGroup parent, int layoutRes) {
            mViews = new SparseArray<>();
            this.context = context;
            View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
            convertView.setTag(this);
            item = convertView;
        }


        /**
         * 绑定ViewHolder与item
         *
         * @param context     上下文
         * @param convertView
         * @param parent
         * @param layoutRes
         * @param position
         * @return
         */
        public static ViewHolder bind(Context context, View convertView, ViewGroup parent,
                                      int layoutRes, int position) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder(context, parent, layoutRes);
            } else {
                holder = (ViewHolder) convertView.getTag();
                holder.item = convertView;
            }
            holder.position = position;
            return holder;
        }

        /**
         * 根据id，获取相关的控件
         *
         * @param id
         * @param <T>
         * @return
         */
        public <T extends View> T getView(int id) {
            T t = (T) mViews.get(id);
            if (t == null) {
                t = (T) item.findViewById(id);
                mViews.put(id, t);
            }
            return t;
        }


        /**
         * 获取当前条目
         */
        public View getItemView() {
            return item;
        }

        /**
         * 获取条目位置
         */
        public int getItemPosition() {
            return position;
        }


        /**
         * 设置文字
         *
         * @param id
         * @param text
         * @return
         */
        public ViewHolder setText(int id, CharSequence text) {
            View view = getView(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(text == null ? "" : text);
            }
            return this;
        }


        /**
         * 设置图片
         *
         * @param id
         * @param drawableRes
         * @return
         */
        public ViewHolder setImageResource(int id, int drawableRes) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(drawableRes);
            }
            return this;
        }


        /**
         * 设置点击监听
         *
         * @param id
         * @param listener
         * @return
         */
        public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
            getView(id).setOnClickListener(listener);
            return this;
        }

        /**
         * 设置可见
         *
         * @param id
         * @param visible
         * @return
         */
        public ViewHolder setVisibility(int id, int visible) {
            getView(id).setVisibility(visible);
            return this;
        }

        /**
         * 设置Enable
         *
         * @param id
         * @param able
         * @return
         */
        public ViewHolder setEnable(int id, boolean able) {
            getView(id).setEnabled(able);
            return this;
        }

        /**
         * 设置RadioGroup选择监听
         *
         * @param id
         * @param listener
         * @return
         */
        public ViewHolder setOnCheckedChangeListener(int id, RadioGroup.OnCheckedChangeListener
                listener) {
            View view = getView(id);
            if (view instanceof RadioGroup) {
                ((RadioGroup) view).setOnCheckedChangeListener(listener);
            }
            return this;
        }

        /**
         * 设置标签
         *
         * @param id  控件id
         * @param obj
         * @return
         */
        public ViewHolder setTag(int id, Object obj) {
            getView(id).setTag(obj);
            return this;
        }

    }
}
