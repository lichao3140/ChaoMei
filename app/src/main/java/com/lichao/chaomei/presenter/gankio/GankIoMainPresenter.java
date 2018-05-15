package com.lichao.chaomei.presenter.gankio;

import android.support.annotation.NonNull;

import com.lichao.chaomei.contract.gankio.GankIoMainContract;
import com.lichao.chaomei.model.gankio.GankIoMainModel;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 10:25
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class GankIoMainPresenter extends GankIoMainContract.GankIoMainPresenter{

    @NonNull
    public static GankIoMainPresenter newInstance() {
        return new GankIoMainPresenter();
    }

    @Override
    public void getTabList() {
        if (mIView == null || mIModel == null)
            return;

        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    public GankIoMainContract.IGankIoMainModel getModel() {
        return GankIoMainModel.newInstance();
    }

    @Override
    public void onStart() {
        //getTabList();
    }
}
