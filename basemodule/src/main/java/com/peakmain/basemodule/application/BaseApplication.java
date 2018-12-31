package com.peakmain.basemodule.application;

import android.app.Application;

/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/31
 * mail:2726449200@qq.com
 * describe：
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


}
