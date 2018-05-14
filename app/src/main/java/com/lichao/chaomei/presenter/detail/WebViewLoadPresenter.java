package com.lichao.chaomei.presenter.detail;

import android.support.annotation.NonNull;

import com.lichao.chaomei.contract.detail.WebViewLoadConaract;
import com.lichao.chaomei.model.detail.WebViewLoadModel;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 20:42
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WebViewLoadPresenter extends WebViewLoadConaract.WebViewLoadPresenter {

    @NonNull
    public static WebViewLoadPresenter newInstance() {
        return new WebViewLoadPresenter();
    }

    @Override
    public void loadUrl(String url) {
        if (mIView == null)
            return;

        try {
            mIView.showUrlDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public WebViewLoadConaract.IWebViewLoadModel getModel() {
        return WebViewLoadModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
