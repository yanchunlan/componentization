package com.peakmain.basemodule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class LazyFragment extends Fragment {

    protected View rootView;
    /**
     * Fragment title
     */
//    public String fragmentTitle;
    /**
     * 是否可见状态
     */
    private boolean isVisible;
    /**
     * 标志位，View已经初始化完成。
     */
    private boolean isPrepared;
    /**
     * 是否第一次加载
     */
    private boolean isFirstLoad = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            isFirstLoad = true;
//            initViews(inflater,container,savedInstanceState);
            rootView = inflater.inflate(initLayout(), container, false);
            isPrepared = true;
            initNotLazyData();//非懒加载
            lazyLoad();//懒加载
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
//        defaultLoadOrErrorOrEmpty(rootView);

        return rootView;
    }

    protected abstract void initNotLazyData();

//    protected abstract void defaultLoadOrErrorOrEmpty(View rootView);


    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     *               visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);


        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initData();
        initListener();
    }

    protected abstract void initListener();//事件监听

//    protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void initData();//懒加载网络数据

    protected abstract int initLayout();

//    public String getTitle() {
//        if (null == fragmentTitle) {
//            setDefaultFragmentTitle(null);
//        }
//        return TextUtils.isEmpty(fragmentTitle) ? "" : fragmentTitle;
//    }
//
//    public void setTitle(String title) {
//        fragmentTitle = title;
//    }
//
//    /**
//     * 设置fragment的Title直接调用 若不显示该title 可以不做处理
//     *
//     * @param title 一般用于显示在TabLayout的标题
//     */
//    protected abstract void setDefaultFragmentTitle(String title);
}