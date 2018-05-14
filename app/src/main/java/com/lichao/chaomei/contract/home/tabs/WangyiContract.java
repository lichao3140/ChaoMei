package com.lichao.chaomei.contract.home.tabs;

import com.lichao.chaomei.model.bean.wangyi.WangyiNewsItemBean;
import com.lichao.chaomei.model.bean.wangyi.WangyiNewsListBean;

import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 14:51
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface WangyiContract {

    abstract class WangyiPresenter extends BaseTabsContract.BaseTabsPresenter<IWangyiModel,
            IWangyiView, WangyiNewsItemBean> {
    }

    interface IWangyiModel extends BaseTabsContract.IBaseTabsModel {
        /**
         * 获取网易新闻list
         *
         * @param id id
         * @return Observable
         */
        Observable<WangyiNewsListBean> getNewsList(int id);
    }

    interface IWangyiView extends BaseTabsContract.IBaseTabsView<WangyiNewsItemBean> {

    }

}
