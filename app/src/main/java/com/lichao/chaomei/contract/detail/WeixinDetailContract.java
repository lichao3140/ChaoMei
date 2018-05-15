package com.lichao.chaomei.contract.detail;

import com.lichao.chaomei.presenter.detail.BaseWebViewLoadPresenter;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 9:58
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface WeixinDetailContract {

    abstract class WeixinDetailPresenter extends BaseWebViewLoadPresenter<IWeixinDetailModel,
                IWeixinDetailView> {
        /**
         * 加载微信精选详情
         *
         * @param url url
         */
        public abstract void loadWeixinChoiceDetail(String url);
    }

    interface IWeixinDetailModel extends BaseWebViewLoadContract.IBaseWebViewLoadModel {
    }

    interface IWeixinDetailView extends BaseWebViewLoadContract.IBaseWebViewLoadView {
        /**
         * 显示微信精选详细内容
         *
         * @param url url
         */
        void showWeixinChoiceDetail(String url);
    }
}
