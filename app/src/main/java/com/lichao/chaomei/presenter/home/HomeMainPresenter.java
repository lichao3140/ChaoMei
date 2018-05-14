package com.lichao.chaomei.presenter.home;

import android.support.annotation.NonNull;

import com.lichao.chaomei.contract.home.HomeMainContract;
import com.lichao.chaomei.model.home.HomeMainModel;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 11:18
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class HomeMainPresenter extends HomeMainContract.HomeMainPresenter {

    @NonNull
    public static HomeMainPresenter newInstance() {
        return new HomeMainPresenter();
    }

    @Override
    public void getTabList() {
        if (mIView == null || mIModel == null)
            return;

        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    public HomeMainContract.IHomeMainModel getModel() {
        return HomeMainModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
