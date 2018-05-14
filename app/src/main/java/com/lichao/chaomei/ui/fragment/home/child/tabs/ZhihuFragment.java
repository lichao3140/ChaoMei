package com.lichao.chaomei.ui.fragment.home.child.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lichao.chaomei.R;
import com.lichao.chaomei.adapter.ZhihuAdapter;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.fragment.BaseRecycleFragment;
import com.lichao.chaomei.contract.home.tabs.ZhihuContract;
import com.lichao.chaomei.model.bean.zhihu.ZhihuDailyItemBean;
import com.lichao.chaomei.presenter.home.tabs.ZhihuPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 11:37
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class ZhihuFragment extends BaseRecycleFragment<ZhihuContract.ZhihuPresenter> implements
        ZhihuContract.IZhihuView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_zhihu)
    RecyclerView rvZhihu;
    Unbinder unbinder;

    private ZhihuAdapter mZhihuAdapter;

    public static ZhihuFragment newInstance() {
        Bundle args = new Bundle();
        ZhihuFragment fragment = new ZhihuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_zhihu;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        mZhihuAdapter = new ZhihuAdapter(R.layout.item_recycle_home);
        rvZhihu.setAdapter(mZhihuAdapter);
        rvZhihu.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLatestList();//第一次显示时请求最新的list
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ZhihuPresenter.newInstance();
    }

    @Override
    public void updateContentList(List<ZhihuDailyItemBean> list) {
        //        Logger.e(list.toString());
        if (mZhihuAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mZhihuAdapter.addData(list);
        }
    }

    @Override
    public void itemNotifyChanged(int position) {
        mZhihuAdapter.notifyItemChanged(position);
    }

    @Override
    public void showNetworkError() {
        //Logger.e("Network error.");
        mZhihuAdapter.setEmptyView(errorView);
    }

    @Override
    public void showLoadMoreError() {
        //Logger.e("load more error.");
        mZhihuAdapter.loadMoreFail();
    }

    @Override
    public void showNoMoreData() {
        mZhihuAdapter.loadMoreEnd(false);
    }

    @Override
    public void onLoadMoreRequested() {
        mZhihuAdapter.loadMoreComplete();
        mPresenter.loadMoreList();
    }

    private void initRecycleView(List<ZhihuDailyItemBean> list) {
        mZhihuAdapter = new ZhihuAdapter(R.layout.item_recycle_home, list);
        mZhihuAdapter.setOnLoadMoreListener(this, rvZhihu);
        mZhihuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onItemClick(position, (ZhihuDailyItemBean) adapter.getItem(position));
            }
        });
        rvZhihu.setAdapter(mZhihuAdapter);
    }

    @Override
    protected void onErrorViewClick(View view) {
        mPresenter.loadLatestList();
    }

    @Override
    protected void showLoading() {
        mZhihuAdapter.setEmptyView(loadingView);
    }
}
