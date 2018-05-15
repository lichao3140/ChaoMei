package com.lichao.chaomei.presenter.detail;

import android.support.annotation.NonNull;
import com.lichao.chaomei.contract.detail.WeixinDetailContract;
import com.lichao.chaomei.model.detail.WeixinDetailModel;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 10:00
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WeixinDetailPresenter extends WeixinDetailContract.WeixinDetailPresenter {

    @NonNull
    public static WeixinDetailPresenter newInstance() {
        return new WeixinDetailPresenter();
    }

    @Override
    public void loadWeixinChoiceDetail(String url) {
        if (mIView == null)
            return;

        try {
            mIView.showWeixinChoiceDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public WeixinDetailContract.IWeixinDetailModel getModel() {
        return WeixinDetailModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}

