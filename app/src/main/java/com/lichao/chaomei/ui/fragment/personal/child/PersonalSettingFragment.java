package com.lichao.chaomei.ui.fragment.personal.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.lichao.chaomei.R;
import com.lichao.chaomei.base.fragment.BaseCompatFragment;
import butterknife.BindView;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 18:23
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class PersonalSettingFragment extends BaseCompatFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static PersonalSettingFragment newInstance() {
        Bundle args = new Bundle();
        PersonalSettingFragment fragment = new PersonalSettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_setting;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }
}

