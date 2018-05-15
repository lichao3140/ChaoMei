package com.lichao.chaomei.contract.gankio.tabs;

import com.lichao.chaomei.model.bean.gankio.GankIoWelfareItemBean;
import com.lichao.chaomei.model.bean.gankio.GankIoWelfareListBean;
import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 13:30
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface GankIoWelfareContract {

    abstract class GankIoWelfarePresenter extends BaseTabsContract
            .BaseTabsPresenter<IGankIoWelfareModel, IGankIoWelfareView, GankIoWelfareItemBean> {

    }

    interface IGankIoWelfareModel extends BaseTabsContract.IBaseTabsModel {
        /**
         * 获取福利list
         *
         * @param pre_page 每页条数
         * @param page     当前页
         * @return Observable
         */
        Observable<GankIoWelfareListBean> getWelfareList(int pre_page, int page);
    }

    interface IGankIoWelfareView extends BaseTabsContract.IBaseTabsView<GankIoWelfareItemBean> {

    }
}
