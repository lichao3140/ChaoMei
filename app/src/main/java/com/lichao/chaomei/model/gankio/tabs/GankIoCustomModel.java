package com.lichao.chaomei.model.gankio.tabs;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.lichao.chaomei.api.GankioApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.gankio.tabs.GankIoCustomContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.gankio.GankIoCustomItemBean;
import com.lichao.chaomei.model.bean.gankio.GankIoCustomListBean;
import com.lichao.chaomei.utils.AppUtils;
import com.lichao.chaomei.utils.DBConfig;
import com.lichao.chaomei.utils.DBUtils;
import com.lichao.chaomei.utils.ItemState;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 13:14
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class GankIoCustomModel extends BaseModel implements GankIoCustomContract
        .IGankIoCustomModel {

    @NonNull
    public static GankIoCustomModel newInstance() {
        return new GankIoCustomModel();
    }

    @Override
    public Observable<GankIoCustomListBean> getCustomGankIoList(String type, int prePage, int
            page) {
        return RetrofitCreateHelper.createApi(GankioApi.class, GankioApi.HOST)
                .getGankIoCustomList(type, prePage, page)
                .map(new Function<GankIoCustomListBean, GankIoCustomListBean>() {
                    @Override
                    public GankIoCustomListBean apply(GankIoCustomListBean gankIoCustomListBean)
                            throws Exception {
                        for (GankIoCustomItemBean bean : gankIoCustomListBean.getResults()) {
                            if (bean.getType().equals("福利")) {
                                bean.itemType = GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE;
                            } else if (bean.getImages() != null) {
                                if (bean.getImages().size() > 0 && !TextUtils.isEmpty(bean
                                        .getImages().get(0)))
                                    bean.itemType = GankIoCustomItemBean
                                            .GANK_IO_DAY_ITEM_CUSTOM_NORMAL;
                            } else {
                                bean.itemType = GankIoCustomItemBean
                                        .GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE;
                            }
                        }
                        return gankIoCustomListBean;
                    }
                })
                .compose(RxHelper.<GankIoCustomListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<Boolean> recordItemIsRead(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(DBUtils.getDB(AppUtils.getContext()).insertRead(DBConfig
                        .TABLE_GANKIO_CUSTOM, key, ItemState.STATE_IS_READ));
                emitter.onComplete();
            }
        }).compose(RxHelper.<Boolean>rxSchedulerHelper());
    }
}
