package com.lichao.chaomei.model.detail;

import android.support.annotation.NonNull;

import com.lichao.chaomei.api.ZhihuApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.detail.ZhihuDetailContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.zhihu.ZhihuDailyDetailBean;

import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 8:44
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class ZhihuDetailModel extends BaseModel implements ZhihuDetailContract.IZhihuDetailModel {

    @NonNull
    public static ZhihuDetailModel newInstance() {
        return new ZhihuDetailModel();
    }

    @Override
    public Observable<ZhihuDailyDetailBean> getDailyDetail(String id) {
        return RetrofitCreateHelper.createApi(ZhihuApi.class, ZhihuApi.HOST).getZhihuDailyDetail
                (id).compose(RxHelper.<ZhihuDailyDetailBean>rxSchedulerHelper());
    }
}
