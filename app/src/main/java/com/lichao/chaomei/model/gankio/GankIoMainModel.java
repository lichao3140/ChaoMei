package com.lichao.chaomei.model.gankio;

import android.support.annotation.NonNull;

import com.lichao.chaomei.contract.gankio.GankIoMainContract;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 10:34
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class GankIoMainModel implements GankIoMainContract.IGankIoMainModel {

    @NonNull
    public static GankIoMainModel newInstance() {
        return new GankIoMainModel();
    }

    @Override
    public String[] getTabs() {
        return new String[]{"每日推荐", "干货定制", "福利"};
    }
}