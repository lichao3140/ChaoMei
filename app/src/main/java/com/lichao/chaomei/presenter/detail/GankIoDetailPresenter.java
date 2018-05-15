package com.lichao.chaomei.presenter.detail;

import android.support.annotation.NonNull;
import com.lichao.chaomei.contract.detail.GankIoDetailContract;
import com.lichao.chaomei.model.detail.GankIoDetailModel;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 13:52
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class GankIoDetailPresenter extends GankIoDetailContract.GankIoDetailPresenter{
    @NonNull
    public static GankIoDetailPresenter newInstance() {
        return new GankIoDetailPresenter();
    }

    @Override
    public void loadGankIoDetail(String url) {
        if (mIView == null)
            return;

        try {
            mIView.showGankIoDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public GankIoDetailContract.IGankIoDetailModel getModel() {
        return GankIoDetailModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}

