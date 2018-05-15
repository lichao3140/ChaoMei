package com.lichao.chaomei.ui.fragment.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lichao.chaomei.R;
import com.lichao.chaomei.base.fragment.BaseCompatFragment;
import com.lichao.chaomei.ui.fragment.gankio.child.GankIoFragment;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:12
 * Email: lichao3140@gmail.com
 * Version: v1.0 干货
 */
public class GankIoRootFragment extends BaseCompatFragment {

    public static GankIoRootFragment newInstance() {
        Bundle args = new Bundle();
        GankIoRootFragment fragment = new GankIoRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gank_io;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(GankIoFragment.class) == null) {
            loadRootFragment(R.id.fl_container, GankIoFragment.newInstance());
        }
    }
}
