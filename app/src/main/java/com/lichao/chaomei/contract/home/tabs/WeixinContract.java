package com.lichao.chaomei.contract.home.tabs;

import com.lichao.chaomei.model.bean.weixin.WeixinChoiceItemBean;
import com.lichao.chaomei.model.bean.weixin.WeixinChoiceListBean;

import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 16:06
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface WeixinContract {

    abstract class WeixinPresenter extends BaseTabsContract.BaseTabsPresenter<IWeixinModel,
            IWeixinView, WeixinChoiceItemBean> {
    }

    interface IWeixinModel extends BaseTabsContract.IBaseTabsModel {
        /**
         * 获取微信精选
         *
         * @param page      指定微信精选页数->空的话默认1
         * @param pageStrip 每页显示条数->空的话默认20条
         * @param dttype    返回数据的格式,xml或json，空的话->默认json
         * @param key       聚合key
         * @return Observable
         */
        Observable<WeixinChoiceListBean> getWeixinChoiceList(int page, int pageStrip, String
                dttype, String key);
    }

    interface IWeixinView extends BaseTabsContract.IBaseTabsView<WeixinChoiceItemBean> {

    }
}
