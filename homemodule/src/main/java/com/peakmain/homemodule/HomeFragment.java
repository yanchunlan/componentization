package com.peakmain.homemodule;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.allure.lmrecycleadapter.headerfooterwrapper.HeaderAndFooterWrapper;
import com.peakmain.basemodule.BaseFragment;
import com.peakmain.commonmodule.CustPtrFrameLayout;
import com.peakmain.minemodule.R;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/30
 * mail:2726449200@qq.com
 * describe：
 */
public class HomeFragment extends BaseFragment {
    private CustPtrFrameLayout mCustPtrFrameLayout;
    private RecyclerView mRecyclerView;
    private HomeAdpater mHomeAdpater;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
     private HomeHeaderLayout mHomeHeaderLayout;
    @Override
    protected void initNotLazyData() {
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mCustPtrFrameLayout = rootView.findViewById(R.id.refrsh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeAdpater = new HomeAdpater(activity, R.layout.home_recycle_item, getHomeList());
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mHomeAdpater);
        mHomeHeaderLayout=new HomeHeaderLayout(activity);
        mHeaderAndFooterWrapper.addHeaderView(mHomeHeaderLayout);
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
        mCustPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCustPtrFrameLayout.refreshComplete();
                        Toast.makeText(activity, "下拉刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter() {

    }

    public List<HomeBean> getHomeList() {
        List<HomeBean> homeBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HomeBean homeBean = new HomeBean();
            switch (i) {
                case 0:
                    homeBean.setResource(R.drawable.image1);
                    break;
                case 1:
                    homeBean.setResource(R.drawable.image2);
                    break;
                case 2:
                    homeBean.setResource(R.drawable.image3);
                    break;
                case 3:
                    homeBean.setResource(R.drawable.image4);
                    break;
                case 4:
                    homeBean.setResource(R.drawable.image1);
                    break;
            }
            homeBeans.add(homeBean);
        }
        return homeBeans;
    }
}
