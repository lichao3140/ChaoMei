package com.lichao.chaomei.ui.fragment.movie.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lichao.chaomei.R;
import com.lichao.chaomei.adapter.HotMovieAdapter;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.fragment.BaseRecycleFragment;
import com.lichao.chaomei.contract.movie.MovieMainContract;
import com.lichao.chaomei.model.bean.douban.movie.child.SubjectsBean;
import com.lichao.chaomei.presenter.movie.MovieMainPresenter;
import com.lichao.chaomei.utils.ResourcesUtils;
import java.util.List;
import butterknife.BindView;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:30
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class MovieFragment extends BaseRecycleFragment<MovieMainContract.MovieMainPresenter>
        implements MovieMainContract.IMovieMainView {

    @BindView(R.id.rv_hot_movie)
    RecyclerView rvHotMovie;

    private HotMovieAdapter mHotMovieAdapter;
    private View headView;

    public static MovieFragment newInstance() {
        Bundle args = new Bundle();
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie_hot;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadHotMovieList();
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        mHotMovieAdapter = new HotMovieAdapter(R.layout.item_hot_movie);
        rvHotMovie.setAdapter(mHotMovieAdapter);
        rvHotMovie.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void updateContentList(List<SubjectsBean> list) {
        //        Logger.e(list.toString());
        if (mHotMovieAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mHotMovieAdapter.addData(list);
        }
    }

    @Override
    public void showNetworkError() {
        mHotMovieAdapter.setEmptyView(errorView);
    }

    @Override
    protected void onErrorViewClick(View view) {
        mPresenter.loadHotMovieList();
    }

    @Override
    protected void showLoading() {
        mHotMovieAdapter.setEmptyView(loadingView);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return MovieMainPresenter.newInstance();
    }

    private void initRecycleView(List<SubjectsBean> list) {
        mHotMovieAdapter = new HotMovieAdapter(R.layout.item_hot_movie, list);
        mHotMovieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //由于有headview click position需要+1 adapter.getItem返回的是数据list的position，所以不用+1
                mPresenter.onItemClick(position + 1, (SubjectsBean) adapter.getItem(position),
                        (ImageView) view.findViewById(R.id.iv_moive_photo));
            }
        });
        initHeadView();
        mHotMovieAdapter.addHeaderView(headView);
        rvHotMovie.setAdapter(mHotMovieAdapter);
    }

    private void initHeadView() {
        if (headView == null) {
            headView = ResourcesUtils.inflate(R.layout.sub_movie_top_header);
        }
        headView.findViewById(R.id.ll_movie_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onHeaderClick();
            }
        });
    }
}

