package com.lichao.chaomei.ui.fragment.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lichao.chaomei.R;
import com.lichao.chaomei.base.fragment.BaseCompatFragment;
import com.lichao.chaomei.ui.fragment.movie.child.MovieFragment;

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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //加载子fragment
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, MovieFragment.newInstance());
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(MovieFragment.class) == null) {
                loadRootFragment(R.id.fl_container, MovieFragment.newInstance());
            }
        }
    }
}
