package com.peakmain.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.peakmain.basemodule.BaseActivity;
import com.peakmain.basemodule.MessageEvent;
import com.peakmain.homemodule.HomeFragment;
import com.peakmain.minemodule.MineFragment;
import com.peakmain.othermodule.OtherFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Fragment[] mFragments;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    private HomeFragment mHomeFragment;
    private MineFragment mMineFragment;
    private OtherFragment mOtherFragment;

    private Button mBtnHome;
    private Button mBtnMine;
    private Button mBtnOther;


    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        mBtnHome = findViewById(R.id.btn_home);
        mBtnMine = findViewById(R.id.btn_mine);
        mBtnOther = findViewById(R.id.btn_other);

        mHomeFragment = new HomeFragment();
        mMineFragment = new MineFragment();
        mOtherFragment = new OtherFragment();
        mFragmentManager = getSupportFragmentManager();

        mFragments = new Fragment[]{mHomeFragment, mMineFragment, mOtherFragment};
        selectTab(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
       switch (event.getPosition()){//判断哪个页面
           case 0:
               break;
           case 1:
               Toast.makeText(activity,event.getMsg(),Toast.LENGTH_SHORT).show();
               break;
           case 2:
               break;
       }
    }
    @Override
    protected void initListener() {
        mBtnHome.setOnClickListener(this);
        mBtnMine.setOnClickListener(this);
        mBtnOther.setOnClickListener(this);
    }

    private void selectTab(int tab) {
        mTransaction = mFragmentManager.beginTransaction();
        hideAllFragment(mTransaction);
        if (mFragments[tab].isAdded()) {
            mTransaction.show(mFragments[tab]);
        } else {
            mTransaction.add(R.id.frame_layout, mFragments[tab]);
            mTransaction.show(mFragments[tab]);
        }
        mTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        for (Fragment fragment : mFragments) {
            transaction.hide(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
              selectTab(0);
                break;
            case R.id.btn_mine:
                selectTab(1);
                break;
            case R.id.btn_other:
                selectTab(2);
                break;
        }
    }
}
