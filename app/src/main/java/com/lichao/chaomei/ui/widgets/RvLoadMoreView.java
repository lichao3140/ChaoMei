package com.lichao.chaomei.ui.widgets;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.lichao.chaomei.R;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 13:28
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class RvLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.item_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
