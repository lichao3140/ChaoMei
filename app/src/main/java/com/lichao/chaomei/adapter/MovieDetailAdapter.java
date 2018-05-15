package com.lichao.chaomei.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lichao.chaomei.R;
import com.lichao.chaomei.model.bean.douban.movie.child.PersonBean;
import com.lichao.chaomei.utils.StringUtils;

import java.util.List;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 17:23
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class MovieDetailAdapter extends BaseCompatAdapter<PersonBean, BaseViewHolder> {
    public MovieDetailAdapter(@LayoutRes int layoutResId, @Nullable List<PersonBean> data) {
        super(layoutResId, data);
    }

    public MovieDetailAdapter(@Nullable List<PersonBean> data) {
        super(data);
    }

    public MovieDetailAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonBean item) {
        helper.setText(R.id.tv_person_name, item.getName());
        helper.setText(R.id.tv_person_type, item.getType());
        try {
            //避免空指针异常
            if (StringUtils.isEmpty(item.getAvatars().getLarge()))
                return;

            Glide.with(mContext).load(item.getAvatars().getLarge()).crossFade().into((ImageView)
                    helper.getView(R.id.iv_avatar_photo));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

