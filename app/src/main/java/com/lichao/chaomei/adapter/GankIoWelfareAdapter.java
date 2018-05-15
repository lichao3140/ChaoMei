package com.lichao.chaomei.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lichao.chaomei.R;
import com.lichao.chaomei.model.bean.gankio.GankIoWelfareItemBean;
import java.util.List;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 13:34
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class GankIoWelfareAdapter extends BaseCompatAdapter<GankIoWelfareItemBean, BaseViewHolder> {

    public GankIoWelfareAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public GankIoWelfareAdapter(@LayoutRes int layoutResId, @Nullable List<GankIoWelfareItemBean> data) {
        super(layoutResId, data);
    }

    public GankIoWelfareAdapter(@Nullable List<GankIoWelfareItemBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoWelfareItemBean item) {
        Glide.with(mContext)
                .load(item.getUrl())
                .crossFade(500)
                .placeholder(R.mipmap.img_default_meizi)
                .into((ImageView) helper.getView(R.id.iv_item_image));
    }
}
