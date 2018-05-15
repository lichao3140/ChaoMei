package com.lichao.chaomei.contract.detail;

import com.lichao.chaomei.model.bean.wangyi.WangyiNewsDetailBean;
import com.lichao.chaomei.presenter.detail.BaseWebViewLoadPresenter;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 9:17
 * Email: lichao3140@gmail.com
 * Version: v1.0  网易新闻详情页接口
 */
public interface WangyiDetailContract {

    abstract class WangyiDetailPresenter extends BaseWebViewLoadPresenter<IWangyiDetailModel,
                IWangyiDetailView> {
        /**
         * 加载新闻详情
         *
         * @param url url
         */
        public abstract void loadNewsDetailWithUrl(String url);

        /**
         * 加载新闻详情
         * <p>
         * 排版显示不好看，直接使用loadNewsDetailWithUrl
         *
         * @param id id
         */
        public abstract void loadNewsDetailWithId(String id);
    }

    interface IWangyiDetailModel extends BaseWebViewLoadContract.IBaseWebViewLoadModel {
        /**
         * 获取日报详情
         *
         * @param id 新闻id
         * @return ResponseBody
         */
        Observable<ResponseBody> getNewsDetail(String id);
    }

    interface IWangyiDetailView extends BaseWebViewLoadContract.IBaseWebViewLoadView {
        /**
         * 显示新闻详细内容
         *
         * @param bean bean
         */
        void showNewsDetail(WangyiNewsDetailBean bean);

        /**
         * 显示新闻详细内容
         *
         * @param url url
         */
        void showNewsDetail(String url);
    }
}
