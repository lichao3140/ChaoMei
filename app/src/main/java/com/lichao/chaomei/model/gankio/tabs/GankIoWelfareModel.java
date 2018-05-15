package com.lichao.chaomei.model.gankio.tabs;

import android.support.annotation.NonNull;

import com.lichao.chaomei.api.GankioApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.gankio.tabs.GankIoWelfareContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.gankio.GankIoWelfareListBean;

import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 13:31
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class GankIoWelfareModel extends BaseModel implements GankIoWelfareContract
        .IGankIoWelfareModel {

    @NonNull
    public static GankIoWelfareModel newInstance() {
        return new GankIoWelfareModel();
    }

    @Override
    public Observable<Boolean> recordItemIsRead(String key) {
        //不记录
        return null;
    }

    @Override
    public Observable<GankIoWelfareListBean> getWelfareList(int pre_page, int page) {
        return RetrofitCreateHelper.createApi(GankioApi.class, GankioApi.HOST)
                .getGankIoWelfareList(pre_page, page).compose(RxHelper
                        .<GankIoWelfareListBean>rxSchedulerHelper());
    }
}
