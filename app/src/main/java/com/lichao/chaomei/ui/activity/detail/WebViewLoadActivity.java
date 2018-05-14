package com.lichao.chaomei.ui.activity.detail;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.constant.BundleKeyConstant;
import com.lichao.chaomei.contract.detail.WebViewLoadConaract;
import com.lichao.chaomei.presenter.detail.WebViewLoadPresenter;
import com.lichao.chaomei.utils.DisplayUtils;
import com.lichao.chaomei.utils.StatusBarUtils;

/**
 * Webview加载Url详情页
 */
public class WebViewLoadActivity extends BaseWebViewLoadActivity<WebViewLoadConaract
        .WebViewLoadPresenter> implements WebViewLoadConaract.IWebViewLoadView {

    private String mTitle, mUrl;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) appBar.getChildAt(0).getLayoutParams();
        // 控件的高强制设成56dp+状态栏高度
        params.height = DisplayUtils.dp2px(56) + StatusBarUtils.getStatusBarHeight(mContext);
    }

    @Override
    public void showUrlDetail(String url) {
        flNetView.setVisibility(View.GONE);
        nswvDetailContent.loadUrl(url);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadUrl(mUrl);
    }

    @Override
    protected String getToolbarTitle() {
        return mTitle;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return WebViewLoadPresenter.newInstance();
    }
}
