package com.lichao.chaomei.ui.fragment.gankio.child.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lichao.chaomei.R;
import com.lichao.chaomei.adapter.GankIoWelfareAdapter;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.fragment.BaseRecycleFragment;
import com.lichao.chaomei.contract.gankio.tabs.GankIoWelfareContract;
import com.lichao.chaomei.model.bean.gankio.GankIoWelfareItemBean;
import com.lichao.chaomei.presenter.gankio.tabs.GankIoWelfarePresenter;
import com.lichao.chaomei.rxbus.RxBus;
import com.lichao.chaomei.rxbus.Subscribe;
import java.util.List;
import butterknife.BindView;
import static com.lichao.chaomei.constant.RxBusCode.RX_BUS_CODE_GANKIO_WELFARE_TYPE;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 13:35
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class GankIoWelfareFragment extends BaseRecycleFragment<GankIoWelfareContract
        .GankIoWelfarePresenter> implements GankIoWelfareContract.IGankIoWelfareView,
        BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_gankio_welfare)
    RecyclerView rvGankIoWelfare;

    GankIoWelfareAdapter mGankIoWelfareAdapter;

    public static GankIoWelfareFragment newInstance() {
        Bundle args = new Bundle();
        GankIoWelfareFragment fragment = new GankIoWelfareFragment();
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
        return R.layout.fragment_gank_io_welfare;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        mGankIoWelfareAdapter = new GankIoWelfareAdapter(R.layout.item_gank_io_welfare);
        rvGankIoWelfare.setAdapter(mGankIoWelfareAdapter);
        //getItemCount()返回值<=0,要设置LinearLayoutManager，否则后面数据更新RecycleView也不执行onBindViewHolder;
        rvGankIoWelfare.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLatestList();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return GankIoWelfarePresenter.newInstance();
    }

    @Override
    public void updateContentList(List<GankIoWelfareItemBean> list) {
        if (mGankIoWelfareAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mGankIoWelfareAdapter.addData(list);
        }
    }

    @Override
    public void itemNotifyChanged(int position) {
    }

    @Override
    public void showNetworkError() {
        mGankIoWelfareAdapter.setEmptyView(errorView);
    }

    @Override
    public void showLoadMoreError() {
        mGankIoWelfareAdapter.loadMoreFail();
    }

    @Override
    public void showNoMoreData() {
        mGankIoWelfareAdapter.loadMoreEnd(false);
    }

    @Override
    public void onLoadMoreRequested() {
        //这里loadMoreComplete要放在前面，避免在Presenter.loadMoreNewsList处理中直接showNoMoreData，出现无限显示加载item
        mGankIoWelfareAdapter.loadMoreComplete();
        mPresenter.loadMoreList();
    }

    private void initRecycleView(List<GankIoWelfareItemBean> list) {
        mGankIoWelfareAdapter = new GankIoWelfareAdapter(R.layout.item_gank_io_welfare, list);
        mGankIoWelfareAdapter.setOnLoadMoreListener(this, rvGankIoWelfare);
        mGankIoWelfareAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onItemClick(position, (GankIoWelfareItemBean) adapter.getItem(position));
            }
        });
        rvGankIoWelfare.setAdapter(mGankIoWelfareAdapter);
        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        rvGankIoWelfare.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
    }

    /**
     * day页面查看更多事件触发
     */
    @Subscribe(code = RX_BUS_CODE_GANKIO_WELFARE_TYPE)
    public void rxBusEvent() {
        rvGankIoWelfare.smoothScrollToPosition(0);
    }

    @Override
    protected void onErrorViewClick(View view) {
        mPresenter.loadLatestList();
    }

    @Override
    protected void showLoading() {
        mGankIoWelfareAdapter.setEmptyView(loadingView);
    }
}

