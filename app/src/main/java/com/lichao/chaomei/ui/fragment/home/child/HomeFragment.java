package com.lichao.chaomei.ui.fragment.home.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.fragment.BaseMVPCompatFragment;
import com.lichao.chaomei.contract.home.HomeMainContract;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:16
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class HomeFragment extends BaseMVPCompatFragment<HomeMainContract.HomeMainPresenter>
        implements HomeMainContract.IHomeMainView {

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showTabList(String[] tabs) {

    }
}
