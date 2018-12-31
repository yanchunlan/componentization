package com.peakmain.othermodule;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.peakmain.basemodule.BaseActivity;

/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/31
 * mail:2726449200@qq.com
 * describe：
 */
public class OtherActivity extends BaseActivity {
    FragmentManager mFragmentManager;
    @Override
    public int initLayout() {
        return R.layout.activity_other;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mFragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,new OtherFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void initListener() {

    }
}
