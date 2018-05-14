package com.lichao.chaomei.model.home.tabs;

import android.support.annotation.NonNull;

import com.lichao.chaomei.api.WeixinApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.home.tabs.WeixinContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.weixin.WeixinChoiceListBean;
import com.lichao.chaomei.utils.AppUtils;
import com.lichao.chaomei.utils.DBConfig;
import com.lichao.chaomei.utils.DBUtils;
import com.lichao.chaomei.utils.ItemState;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 16:10
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WeixinChoiceModel extends BaseModel implements WeixinContract.IWeixinModel {

    @NonNull
    public static WeixinChoiceModel newInstance() {
        return new WeixinChoiceModel();
    }

    @Override
    public Observable<Boolean> recordItemIsRead(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(DBUtils.getDB(AppUtils.getContext()).insertRead(DBConfig
                        .TABLE_WEIXIN, key, ItemState.STATE_IS_READ));
                emitter.onComplete();
            }
        }).compose(RxHelper.<Boolean>rxSchedulerHelper());
    }

    @Override
    public Observable<WeixinChoiceListBean> getWeixinChoiceList(int page, int pageStrip, String
            dttype, String key) {
        return RetrofitCreateHelper.createApi(WeixinApi.class, WeixinApi.HOST).getWeixinChoiceList
                (page, pageStrip, dttype, key).compose(RxHelper
                .<WeixinChoiceListBean>rxSchedulerHelper());
    }
}
