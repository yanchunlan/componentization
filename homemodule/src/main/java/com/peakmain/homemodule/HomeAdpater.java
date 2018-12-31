package com.peakmain.homemodule;

import android.content.Context;
import android.widget.BaseAdapter;

import com.allure.lmrecycleadapter.BaseCleanRecycleAdapter;
import com.allure.lmrecycleadapter.viewholder.BaseViewHolderHelper;
import com.bumptech.glide.Glide;
import com.peakmain.minemodule.R;

import java.util.List;

/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/31
 * mail:2726449200@qq.com
 * describe：
 */
public class HomeAdpater extends BaseCleanRecycleAdapter<HomeBean> {

    public HomeAdpater(Context context, int layoutResId, List<HomeBean> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, HomeBean item, int position) {
        Glide.with(mContext).load(item.getResource()).into(helper.getImageView(R.id.image));
    }
}