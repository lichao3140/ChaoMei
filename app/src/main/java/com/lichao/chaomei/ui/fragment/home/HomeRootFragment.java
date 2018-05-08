package com.lichao.chaomei.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lichao.chaomei.R;
import com.lichao.chaomei.base.fragment.BaseCompatFragment;
import com.lichao.chaomei.ui.fragment.home.child.HomeFragment;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:06
 * Email: lichao3140@gmail.com
 * Version: v1.0  首页
 */
public class HomeRootFragment extends BaseCompatFragment {

    public static HomeRootFragment newInstance() {
        Bundle args = new Bundle();
        HomeRootFragment fragment = new HomeRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
    }
}
