package com.lichao.chaomei.ui.fragment.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lichao.chaomei.R;
import com.lichao.chaomei.base.fragment.BaseCompatFragment;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:10
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class BookRootFragment extends BaseCompatFragment {

    public static BookRootFragment newInstance() {
        Bundle args = new Bundle();
        BookRootFragment fragment = new BookRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_book;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }
}
