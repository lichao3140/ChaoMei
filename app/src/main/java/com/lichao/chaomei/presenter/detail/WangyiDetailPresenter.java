package com.lichao.chaomei.presenter.detail;

import android.support.annotation.NonNull;

import com.lichao.chaomei.contract.detail.WangyiDetailContract;
import com.lichao.chaomei.helper.JsonHelper;
import com.lichao.chaomei.model.bean.wangyi.WangyiNewsDetailBean;
import com.lichao.chaomei.model.detail.WangyiDetailModel;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 9:21
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WangyiDetailPresenter extends WangyiDetailContract.WangyiDetailPresenter {
    @NonNull
    public static WangyiDetailPresenter newInstance() {
        return new WangyiDetailPresenter();
    }

    @Override
    public void loadNewsDetailWithUrl(String url) {
        if (mIView == null)
            return;

        try {
            mIView.showNewsDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public void loadNewsDetailWithId(final String id) {
        mRxManager.register(mIModel.getNewsDetail(id).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                if (mIView == null)
                    return;
                //新闻的Json数据比较特殊，返回的json key不固定，需要手动的获取json数据，然后再用gson解析
                WangyiNewsDetailBean bean = JsonHelper.getNewsDetailBeans(responseBody.string(), id);
                mIView.showNewsDetail(bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    return;
                mIView.showNetworkError();
            }
        }));
    }

    @Override
    public WangyiDetailContract.IWangyiDetailModel getModel() {
        return WangyiDetailModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
