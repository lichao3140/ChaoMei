package com.lichao.chaomei.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lichao.chaomei.R;
import com.lichao.chaomei.model.bean.douban.movie.child.SubjectsBean;
import java.util.List;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 16:27
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class TopMovieAdapter extends BaseCompatAdapter<SubjectsBean, BaseViewHolder> {

    public TopMovieAdapter(@LayoutRes int layoutResId, @Nullable List<SubjectsBean> data) {
        super(layoutResId, data);
    }

    public TopMovieAdapter(@Nullable List<SubjectsBean> data) {
        super(data);
    }

    public TopMovieAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectsBean item) {
        helper.setText(R.id.tv_top_moive_name, item.getTitle());
        helper.setText(R.id.tv_top_moive_rate, String.valueOf(item.getRating().getAverage()));
        Glide.with(mContext).load(item.getImages().getLarge()).crossFade(300).placeholder(R
                .mipmap.img_default_movie).into((ImageView) helper.getView(R.id
                .iv_top_moive_photo));
    }
}

