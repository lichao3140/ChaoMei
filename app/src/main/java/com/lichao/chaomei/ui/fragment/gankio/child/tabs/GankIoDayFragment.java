package com.lichao.chaomei.ui.fragment.gankio.child.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lichao.chaomei.R;
import com.lichao.chaomei.adapter.GankIoDayAdapter;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.fragment.BaseRecycleFragment;
import com.lichao.chaomei.contract.gankio.tabs.GankIoDayContract;
import com.lichao.chaomei.model.bean.gankio.GankIoDayItemBean;
import com.lichao.chaomei.presenter.gankio.tabs.GankIoDayPresenter;
import com.lichao.chaomei.rxbus.RxBus;
import java.util.List;
import butterknife.BindView;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 11:52
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class GankIoDayFragment extends BaseRecycleFragment<GankIoDayContract
        .GankIoDayPresenter> implements GankIoDayContract.IGankIoDayView {

    @BindView(R.id.rv_gankio_day)
    RecyclerView rvGankIoDay;

    GankIoDayAdapter mGankIoDayAdapter;

    public static GankIoDayFragment newInstance() {
        Bundle args = new Bundle();
        GankIoDayFragment fragment = new GankIoDayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        super.initData();
        RxBus.get().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gank_io_day;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        mGankIoDayAdapter = new GankIoDayAdapter(null);
        rvGankIoDay.setAdapter(mGankIoDayAdapter);
        rvGankIoDay.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLatestList();//第一次显示时请求最新的list
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return GankIoDayPresenter.newInstance();
    }

    @Override
    public void updateContentList(List<GankIoDayItemBean> list) {
        // Logger.e(list.toString());
        if (mGankIoDayAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mGankIoDayAdapter.addData(list);
        }
    }

    @Override
    public void itemNotifyChanged(int position, GankIoDayItemBean bean) {
        mGankIoDayAdapter.refeshItem(position, bean);
    }

    @Override
    public void itemNotifyChanged(int position) {
    }

    @Override
    public void showNetworkError() {
        mGankIoDayAdapter.setEmptyView(errorView);
    }

    @Override
    public void showLoadMoreError() {
    }

    @Override
    public void showNoMoreData() {
    }

    private void initRecycleView(List<GankIoDayItemBean> list) {
        mGankIoDayAdapter = new GankIoDayAdapter(list);
        mGankIoDayAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onItemClick(position, (GankIoDayItemBean) adapter.getItem(position));
            }
        });

        mGankIoDayAdapter.setOnItemChildClickListener(new BaseQuickAdapter
                .OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_more:
                        mPresenter.onMoreClick(position, (GankIoDayItemBean) adapter.getItem
                                (position));
                        break;
                    case R.id.ll_refesh:
                        mPresenter.onRefeshClick(position, (GankIoDayItemBean) adapter.getItem
                                (position));
                        break;
                }
            }
        });
        rvGankIoDay.setAdapter(mGankIoDayAdapter);
    }

    @Override
    protected void onErrorViewClick(View view) {
        mPresenter.loadLatestList();
    }

    @Override
    protected void showLoading() {
        mGankIoDayAdapter.setEmptyView(loadingView);
    }

}

