package com.peakmain.basemodule;

import android.content.Context;


/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/31
 * mail:2726449200@qq.com
 * describe：
 */

public abstract class BasePresenter<T, E> {
    public Context mContext;
    public E mModel;
    public T mView;

    public void setViewModel(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
    }


    public void onDestroy() {
    }
}
