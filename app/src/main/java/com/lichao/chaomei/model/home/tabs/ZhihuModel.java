package com.lichao.chaomei.model.home.tabs;

import android.support.annotation.NonNull;

import com.lichao.chaomei.api.ZhihuApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.home.tabs.ZhihuContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.zhihu.ZhihuDailyListBean;
import com.lichao.chaomei.utils.AppUtils;
import com.lichao.chaomei.utils.DBConfig;
import com.lichao.chaomei.utils.DBUtils;
import com.lichao.chaomei.utils.ItemState;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 11:46
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class ZhihuModel extends BaseModel implements ZhihuContract.IZhihuModel {

    @NonNull
    public static ZhihuModel newInstance() {
        return new ZhihuModel();
    }

    @Override
    public Observable<ZhihuDailyListBean> getDailyList(String date) {
        return RetrofitCreateHelper.createApi(ZhihuApi.class, ZhihuApi.HOST).getDailyListWithDate
                (date).compose(RxHelper.<ZhihuDailyListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<ZhihuDailyListBean> getDailyList() {
        return RetrofitCreateHelper.createApi(ZhihuApi.class, ZhihuApi.HOST).getLastDailyList()
                .compose(RxHelper.<ZhihuDailyListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<Boolean> recordItemIsRead(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(DBUtils.getDB(AppUtils.getContext()).insertRead(DBConfig
                        .TABLE_ZHIHU, key, ItemState.STATE_IS_READ));
                emitter.onComplete();
            }
        }).compose(RxHelper.<Boolean>rxSchedulerHelper());
    }
}
