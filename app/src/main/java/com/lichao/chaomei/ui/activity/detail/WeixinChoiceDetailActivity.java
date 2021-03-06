package com.lichao.chaomei.ui.activity.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import com.lichao.chaomei.R;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.constant.BundleKeyConstant;
import com.lichao.chaomei.contract.detail.WeixinDetailContract;
import com.lichao.chaomei.presenter.detail.WeixinDetailPresenter;
import com.lichao.chaomei.utils.DisplayUtils;
import com.lichao.chaomei.utils.ResourcesUtils;
import com.lichao.chaomei.utils.StatusBarUtils;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 10:04
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WeixinChoiceDetailActivity extends BaseWebViewLoadActivity<WeixinDetailContract
        .WeixinDetailPresenter> implements WeixinDetailContract.IWeixinDetailView {

    private String mTitle, mUrl, mImageUrl, mCopyright;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WEIXIN_DETAIL_URL);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_WEIXIN_DETAIL_TITLE);
            mImageUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WEIXIN_DETAIL_IMAGE_URL);
            mCopyright = bundle.getString(BundleKeyConstant.ARG_KEY_WEIXIN_DETAIL_COPYRIGHT);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        //微信精选内部已经有Title等信息，直接显示webview内容
        //tvDetailTitle.setText(mTitle);
        //tvDetailcopyright.setText(mCopyright);
        //Glide.with(mContext).load(mImageUrl).crossFade().into(ivDetail);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) appBar.getChildAt(0)
                .getLayoutParams();
        // 控件的高强制设成56dp+状态栏高度
        params.height = DisplayUtils.dp2px(56) + StatusBarUtils.getStatusBarHeight
                (mContext);
    }

    @Override
    public void showWeixinChoiceDetail(String url) {
        flNetView.setVisibility(View.GONE);
        nswvDetailContent.loadUrl(url);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadWeixinChoiceDetail(mUrl);
    }

    @Override
    protected String getToolbarTitle() {
        return ResourcesUtils.getString(R.string.weixin_detail_title);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return WeixinDetailPresenter.newInstance();
    }
}

