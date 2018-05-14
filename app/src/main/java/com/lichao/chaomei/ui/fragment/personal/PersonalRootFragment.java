package com.lichao.chaomei.ui.fragment.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lichao.chaomei.R;
import com.lichao.chaomei.base.fragment.BaseCompatFragment;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:08
 * Email: lichao3140@gmail.com
 * Version: v1.0  个人
 */
public class PersonalRootFragment extends BaseCompatFragment {

    public static PersonalRootFragment newInstance() {
        Bundle args = new Bundle();
        PersonalRootFragment fragment = new PersonalRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }
}
