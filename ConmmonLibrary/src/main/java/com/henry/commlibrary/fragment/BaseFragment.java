package com.henry.commlibrary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Map;

/**
 * Date: 2017/6/5. 14:57
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:Fragment基类
 */
public class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    protected View mContentView;
    protected Map map;

    protected String token;
    protected String userId;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate...");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        Log.i(TAG, "onCreateView...");
        // 避免多次从xml中加载布局文件
        if (mContentView == null) {
            doCreateView(savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume...");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart...");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop...");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause...");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach...");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView...");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged...hidden>> " + hidden);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        Log.i(TAG, "setUserVisibleHint... isVisibleToUser>>" + isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy...");
    }

    /**
     * 执行onCreateView方法的内容
     *
     * @param layoutResID
     */
    protected void setContentView(@LayoutRes int layoutResID) {
        mContentView = LayoutInflater.from(getActivity()).inflate(layoutResID, null);
    }

    protected void doCreateView(Bundle savedInstanceState) {
    }

    ;

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) mContentView.findViewById(id);
    }


    /**
     * @param resId
     */
    protected void showToast(int resId) {
        try {
            Toast.makeText(getActivity().getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param str
     */
    protected void showToast(String str) {
        try {
            Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
