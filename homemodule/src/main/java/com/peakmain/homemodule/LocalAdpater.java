package com.peakmain.homemodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.adapter.LBaseAdapter;
import com.bumptech.glide.Glide;
import com.peakmain.minemodule.R;

/**
 * author ：Peakmain
 * version : 1.0
 * createTime：2018/12/31
 * mail:2726449200@qq.com
 * describe：
 */
public class LocalAdpater implements LBaseAdapter<Integer> {
    private Context mContext;

    public LocalAdpater(Context context) {
        mContext = context;
    }

    @Override
    public View getView(final LMBanners lBanners, final Context context, int position, Integer data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_banner_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        Glide.with(mContext).load(data).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }


}
