package com.peakmain.homemodule;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.transformer.TransitionEffect;
import com.allure.lbanners.utils.ScreenUtils;
import com.peakmain.minemodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/31
 * mail:2726449200@qq.com
 * describe：
 */
public class HomeHeaderLayout extends LinearLayout {
    private View mView;
    private LMBanners mBanners;
    private LocalAdpater mAdpater;

    public HomeHeaderLayout(Context context) {
        super(context);
        init(context);
    }

    public HomeHeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public HomeHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mView = inflate(context, R.layout.home_header, this);
        mBanners = findViewById(R.id.banners);
        mBanners.setLayoutParams(new
                LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(context, 200)));
        mBanners.setHoriZontalTransitionEffect(TransitionEffect.Alpha);
        mAdpater = new LocalAdpater(context);
        mBanners.setAdapter(mAdpater, getBnnerList());
    }

    public List<Integer> getBnnerList() {
        List<Integer> list=new ArrayList<>();
        list.add(R.drawable.image1);
        list.add(R.drawable.image2);
        list.add(R.drawable.image3);
        list.add(R.drawable.image4);
        list.add(R.drawable.image1);
        return list;
    }
}
