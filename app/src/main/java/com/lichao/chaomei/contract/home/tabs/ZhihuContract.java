package com.lichao.chaomei.contract.home.tabs;

import com.lichao.chaomei.model.bean.zhihu.ZhihuDailyItemBean;
import com.lichao.chaomei.model.bean.zhihu.ZhihuDailyListBean;

import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 11:39
 * Email: lichao3140@gmail.com
 * Version: v1.0 知乎头条接口
 */
public interface ZhihuContract {

    abstract class ZhihuPresenter extends BaseTabsContract.BaseTabsPresenter<IZhihuModel,
            IZhihuView, ZhihuDailyItemBean> {
    }

    interface IZhihuModel extends BaseTabsContract.IBaseTabsModel {
        /**
         * 根据日期获取日报list --> 20170910
         *
         * @param date 日期
         * @return Observable
         */
        Observable<ZhihuDailyListBean> getDailyList(String date);

        /**
         * 获取日报list
         *
         * @return Observable
         */
        Observable<ZhihuDailyListBean> getDailyList();
    }

    interface IZhihuView extends BaseTabsContract.IBaseTabsView<ZhihuDailyItemBean> {

    }
}
