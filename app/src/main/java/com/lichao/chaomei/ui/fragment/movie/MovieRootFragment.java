package com.lichao.chaomei.ui.fragment.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lichao.chaomei.R;
import com.lichao.chaomei.base.fragment.BaseCompatFragment;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:12
 * Email: lichao3140@gmail.com
 * Version: v1.0 电影
 */
public class MovieRootFragment extends BaseCompatFragment {

    public static MovieRootFragment newInstance() {
        Bundle args = new Bundle();
        MovieRootFragment fragment = new MovieRootFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }
}
