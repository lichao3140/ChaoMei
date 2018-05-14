package com.lichao.chaomei.contract.detail;

import com.lichao.chaomei.presenter.detail.BaseWebViewLoadPresenter;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 19:59
 * Email: lichao3140@gmail.com
 * Version: v1.0 webview加载更多详情，传入url显示webview
 */
public interface WebViewLoadConaract {

    abstract class WebViewLoadPresenter extends BaseWebViewLoadPresenter<IWebViewLoadModel,
                IWebViewLoadView> {
        /**
         * 加载url
         *
         * @param url url
         */
        public abstract void loadUrl(String url);
    }

    interface IWebViewLoadModel extends BaseWebViewLoadContract.IBaseWebViewLoadModel {
    }

    interface IWebViewLoadView extends BaseWebViewLoadContract.IBaseWebViewLoadView {
        /**
         * 显示url详情
         *
         * @param url url
         */
        void showUrlDetail(String url);
    }
}
