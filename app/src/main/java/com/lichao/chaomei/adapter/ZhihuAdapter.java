package com.lichao.chaomei.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lichao.chaomei.R;
import com.lichao.chaomei.model.bean.zhihu.ZhihuDailyItemBean;
import com.lichao.chaomei.utils.DBConfig;
import com.lichao.chaomei.utils.DBUtils;
import com.lichao.chaomei.utils.ItemState;
import com.lichao.chaomei.utils.SpUtils;

import java.util.List;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 12:49
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class ZhihuAdapter extends BaseCompatAdapter<ZhihuDailyItemBean, BaseViewHolder> {

    public ZhihuAdapter(int layoutResId, @Nullable List<ZhihuDailyItemBean> data) {
        super(layoutResId, data);
    }

    public ZhihuAdapter(@Nullable List<ZhihuDailyItemBean> data) {
        super(data);
    }

    public ZhihuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhihuDailyItemBean item) {
        if (DBUtils.getDB(mContext).isRead(DBConfig.TABLE_ZHIHU, item.getId(), ItemState.STATE_IS_READ)) {
            helper.setTextColor(R.id.tv_item_title, Color.GRAY);
        } else {
            if (SpUtils.getNightModel(mContext)) {
                helper.setTextColor(R.id.tv_item_title, Color.WHITE);
            } else {
                helper.setTextColor(R.id.tv_item_title, Color.BLACK);
            }
        }
        helper.setText(R.id.tv_item_title, item.getTitle());
        Glide.with(mContext).load(item.getImages()[0]).into((ImageView) helper.getView(R.id.iv_item_image));
    }
}
