package com.lichao.chaomei.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lichao.chaomei.R;
import com.lichao.chaomei.model.bean.wangyi.WangyiNewsItemBean;
import com.lichao.chaomei.utils.DBConfig;
import com.lichao.chaomei.utils.DBUtils;
import com.lichao.chaomei.utils.ItemState;
import com.lichao.chaomei.utils.SpUtils;

import java.util.List;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 15:52
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WangyiAdapter extends BaseCompatAdapter<WangyiNewsItemBean, BaseViewHolder> {

    public WangyiAdapter(@LayoutRes int layoutResId, @Nullable List<WangyiNewsItemBean> data) {
        super(layoutResId, data);
    }

    public WangyiAdapter(@Nullable List<WangyiNewsItemBean> data) {
        super(data);
    }

    public WangyiAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WangyiNewsItemBean item) {
        if (DBUtils.getDB(mContext).isRead(DBConfig.TABLE_WANGYI, item.getDocid(), ItemState.STATE_IS_READ)) {
            helper.setTextColor(R.id.tv_item_title, Color.GRAY);
        } else {
            if (SpUtils.getNightModel(mContext)) {
                helper.setTextColor(R.id.tv_item_title, Color.WHITE);
            } else {
                helper.setTextColor(R.id.tv_item_title, Color.BLACK);
            }
        }
        helper.setText(R.id.tv_item_title, item.getTitle());
        helper.setText(R.id.tv_item_who, item.getSource());
        helper.setText(R.id.tv_item_time, item.getPtime());
        Glide.with(mContext).load(item.getImgsrc()).into((ImageView) helper.getView(R.id.iv_item_image));
    }
}
