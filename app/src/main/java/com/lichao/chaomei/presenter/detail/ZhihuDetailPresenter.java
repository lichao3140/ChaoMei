package com.lichao.chaomei.presenter.detail;

import android.support.annotation.NonNull;

import com.lichao.chaomei.contract.detail.ZhihuDetailContract;
import com.lichao.chaomei.model.bean.zhihu.ZhihuDailyDetailBean;
import com.lichao.chaomei.model.detail.ZhihuDetailModel;

import io.reactivex.functions.Consumer;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 8:43
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class ZhihuDetailPresenter extends ZhihuDetailContract.ZhihuDetailPresenter {

    @NonNull
    public static ZhihuDetailPresenter newInstance() {
        return new ZhihuDetailPresenter();
    }

    @Override
    public void loadDailyDetail(String id) {
        if (mIModel == null)
            return;

        mRxManager.register(mIModel.getDailyDetail(id).subscribe(new Consumer<ZhihuDailyDetailBean>() {
            @Override
            public void accept(ZhihuDailyDetailBean zhihuDailyDetailBean) throws Exception {
                if (mIView != null)
                    mIView.showDailyDetail(zhihuDailyDetailBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    mIView.showToast("网络异常");
                    mIView.showNetworkError();
                }
            }
        }));
    }

    @Override
    public ZhihuDetailContract.IZhihuDetailModel getModel() {
        return ZhihuDetailModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
