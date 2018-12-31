package com.peakmain.minemodule;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.github.mzule.activityrouter.router.Routers;
import com.peakmain.basemodule.BaseFragment;

/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/30
 * mail:2726449200@qq.com
 * describe：
 */
public class MineFragment extends BaseFragment {

    private TextView mTextView;

    @Override
    protected void initNotLazyData() {
        mTextView = rootView.findViewById(R.id.text_login);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goClassForName("com.peakmain.loginmodule.LoginActivity");
                //第二种方式:activityRouter跳转
               // Routers.open(activity, "loginmodule://login");//单纯跳转
                //带参数的跳转并赋值
                Routers.open(activity, "loginmodule://login?username=peakmain&userpassword=123456");

                //第三种方式跳转eventbus
            }
        });
    }

    //第一种方式intent跳转
    private void goClassForName(String packageName) {
        try {
            Class aClass = Class.forName(packageName);
            Intent intent = new Intent(activity, aClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initPresenter() {

    }
}
