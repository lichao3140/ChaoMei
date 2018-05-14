package com.lichao.chaomei.ui.fragment.home.child.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lichao.chaomei.R;
import com.lichao.chaomei.adapter.WeixinAdapter;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.fragment.BaseRecycleFragment;
import com.lichao.chaomei.contract.home.tabs.WeixinContract;
import com.lichao.chaomei.model.bean.weixin.WeixinChoiceItemBean;
import com.lichao.chaomei.presenter.home.tabs.WeixinPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 16:27
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WeixinFragment extends BaseRecycleFragment<WeixinContract.WeixinPresenter>
        implements WeixinContract.IWeixinView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_weixin)
    RecyclerView rvWexin;

    private WeixinAdapter mWeixinAdapter;

    public static WeixinFragment newInstance() {
        Bundle args = new Bundle();
        WeixinFragment fragment = new WeixinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_weixin;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        mWeixinAdapter = new WeixinAdapter(R.layout.item_recycle_home);
        rvWexin.setAdapter(mWeixinAdapter);
        rvWexin.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLatestList();//第一次显示时请求最新的list
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return WeixinPresenter.newInstance();
    }

    @Override
    public void updateContentList(List<WeixinChoiceItemBean> list) {
        //        Logger.e(list.toString());
        if (mWeixinAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mWeixinAdapter.addData(list);
        }
    }

    @Override
    public void itemNotifyChanged(int position) {
        mWeixinAdapter.notifyItemChanged(position);
    }

    @Override
    public void showNetworkError() {
        mWeixinAdapter.setEmptyView(errorView);
    }

    @Override
    public void showLoadMoreError() {
        mWeixinAdapter.loadMoreFail();
    }

    @Override
    public void showNoMoreData() {
        mWeixinAdapter.loadMoreEnd();
    }

    @Override
    public void onLoadMoreRequested() {
        mWeixinAdapter.loadMoreComplete();
        mPresenter.loadMoreList();
    }

    private void initRecycleView(List<WeixinChoiceItemBean> list) {
        mWeixinAdapter = new WeixinAdapter(R.layout.item_recycle_home, list);
        mWeixinAdapter.setOnLoadMoreListener(this, rvWexin);
        mWeixinAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onItemClick(position, (WeixinChoiceItemBean) adapter.getItem(position));
            }
        });
        rvWexin.setAdapter(mWeixinAdapter);
    }

    @Override
    protected void onErrorViewClick(View view) {
        mPresenter.loadLatestList();
    }

    @Override
    protected void showLoading() {
        mWeixinAdapter.setEmptyView(loadingView);
    }
}
