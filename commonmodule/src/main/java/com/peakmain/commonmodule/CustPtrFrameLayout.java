package com.peakmain.commonmodule;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/31
 * mail:2726449200@qq.com
 * describe：
 */
public class CustPtrFrameLayout extends PtrClassicFrameLayout {
    public CustPtrFrameLayout(Context context) {
        super(context);
        init();
    }

    public CustPtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        disableWhenHorizontalMove(true);
        setResistance(1.7f);
        setRatioOfHeaderHeightToRefresh(1.2f);
        setDurationToClose(200);
        setDurationToCloseHeader(1000);
        setPullToRefresh(false);
        setKeepHeaderWhenRefresh(true);
    }
}
