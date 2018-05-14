package com.lichao.chaomei.model.home;

import android.support.annotation.NonNull;

import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.home.HomeMainContract;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 11:21
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class HomeMainModel extends BaseModel implements HomeMainContract.IHomeMainModel {

    @NonNull
    public static HomeMainModel newInstance() {
        return new HomeMainModel();
    }

    @Override
    public String[] getTabs() {
        return new String[]{"知乎日报", "热点新闻", "微信精选"};
    }
}
