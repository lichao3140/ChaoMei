package com.lichao.chaomei.ui.activity.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.bumptech.glide.Glide;
import com.lichao.chaomei.R;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.constant.BundleKeyConstant;
import com.lichao.chaomei.contract.detail.WangyiDetailContract;
import com.lichao.chaomei.model.bean.wangyi.WangyiNewsDetailBean;
import com.lichao.chaomei.presenter.detail.WangyiDetailPresenter;
import com.lichao.chaomei.utils.HtmlUtils;
import com.lichao.chaomei.utils.ResourcesUtils;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 9:14
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WangyiDailyDetailActivity extends BaseWebViewLoadActivity<WangyiDetailContract
        .WangyiDetailPresenter> implements WangyiDetailContract.IWangyiDetailView {

    private String mTitle, mUrl, mId, mImageUrl, mCopyright;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mId = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_ID);
            mUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_URL);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_TITLE);
            mImageUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_IMAGE_URL);
            mCopyright = bundle.getString(BundleKeyConstant.ARG_KEY_WANGYI_DETAIL_COPYRIGHT);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvDetailTitle.setText(mTitle);
        tvDetailcopyright.setText(mCopyright);
        Glide.with(mContext).load(mImageUrl).crossFade().into(ivDetail);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadNewsDetailWithUrl(mUrl);
        //mPresenter.loadNewsDetailWithId(mId);
    }

    @Override
    protected String getToolbarTitle() {
        return ResourcesUtils.getString(R.string.wangyi_detail_title);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return WangyiDetailPresenter.newInstance();
    }

    @Override
    public void showNewsDetail(WangyiNewsDetailBean bean) {
        flNetView.setVisibility(View.GONE);
        //tvDetailTitle.setText(bean.getTitle());
        nswvDetailContent.loadData(bean.getBody(), HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING);
    }

    @Override
    public void showNewsDetail(String url) {
        flNetView.setVisibility(View.GONE);
        nswvDetailContent.loadUrl(url);
    }
}
