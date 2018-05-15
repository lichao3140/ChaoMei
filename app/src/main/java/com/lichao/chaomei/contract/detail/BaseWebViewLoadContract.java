package com.lichao.chaomei.contract.detail;

import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.IBaseActivity;
import com.lichao.chaomei.base.IBaseModel;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 18:43
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface BaseWebViewLoadContract {

    abstract class BaseWebViewLoadPresenter<M extends IBaseWebViewLoadModel, V extends
            IBaseWebViewLoadView> extends BasePresenter<M, V> {

        /**
         * 保存图片点击
         *
         * @param activity {@link FragmentActivity}
         * @param imgUrl   imgUrl
         */
        public abstract void saveImageClicked(FragmentActivity activity, String imgUrl);

        /**
         * 跳转图片详情按钮点击
         *
         * @param imgUrl imgUrl
         */
        public abstract void gotoImageBrowseClicked(String imgUrl);

        /**
         * 图片长按事件
         *
         * @param hitTestResult {@link WebView.HitTestResult}
         */
        public abstract void imageLongClicked(WebView.HitTestResult hitTestResult);

    }

    interface IBaseWebViewLoadModel extends IBaseModel {

    }

    interface IBaseWebViewLoadView extends IBaseActivity {
        /**
         * 显示网络错误
         */
        void showNetworkError();

        /**
         * 显示popupWindow
         */
        void showPopupWindow();

        /**
         * 隐藏popupWindow
         */
        void dismissPopupWindow();

        /**
         * 返回popup显示状态
         *
         * @return popup显示状态
         */
        boolean popupWindowIsShowing();

        /**
         * 跳转到图片详情页
         *
         * @param imgUrl 图片url
         */
        void gotoImageBrowse(String imgUrl);
    }
}