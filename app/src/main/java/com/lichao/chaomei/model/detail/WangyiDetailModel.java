package com.lichao.chaomei.model.detail;

import android.support.annotation.NonNull;

import com.lichao.chaomei.api.WangyiApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.detail.WangyiDetailContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 9:19
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WangyiDetailModel extends BaseModel implements WangyiDetailContract
        .IWangyiDetailModel {

    @NonNull
    public static WangyiDetailModel newInstance() {
        return new WangyiDetailModel();
    }

    @Override
    public Observable<ResponseBody> getNewsDetail(String id) {
        return RetrofitCreateHelper.createApi(WangyiApi.class, WangyiApi.HOST).getNewsDetail(id)
                .compose(RxHelper.<ResponseBody>rxSchedulerHelper());
    }
}
