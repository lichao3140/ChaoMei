package com.lichao.chaomei.contract.detail;

import com.lichao.chaomei.presenter.detail.BaseWebViewLoadPresenter;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 13:49
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface GankIoDetailContract {

    abstract class GankIoDetailPresenter extends BaseWebViewLoadPresenter<IGankIoDetailModel,
                IGankIoDetailView> {
        /**
         * 加载GankIo详情
         *
         * @param url url
         */
        public abstract void loadGankIoDetail(String url);
    }

    interface IGankIoDetailModel extends BaseWebViewLoadContract.IBaseWebViewLoadModel {

    }

    interface IGankIoDetailView extends BaseWebViewLoadContract.IBaseWebViewLoadView {
        /**
         * 显示GankIo详细内容
         *
         * @param url url
         */
        void showGankIoDetail(String url);
    }
}
