package com.lichao.chaomei.ui.activity.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lichao.chaomei.R;
import com.lichao.chaomei.adapter.MovieDetailAdapter;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.activity.BaseMVPCompatActivity;
import com.lichao.chaomei.contract.movie.MovieDetailContract;
import com.lichao.chaomei.model.bean.douban.movie.MovieDetailBean;
import com.lichao.chaomei.model.bean.douban.movie.child.PersonBean;
import com.lichao.chaomei.model.bean.douban.movie.child.SubjectsBean;
import com.lichao.chaomei.presenter.movie.MovieDetailPresenter;
import com.lichao.chaomei.ui.widgets.CompatNestedScrollView;
import com.lichao.chaomei.utils.DisplayUtils;
import com.lichao.chaomei.utils.ResourcesUtils;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import static com.lichao.chaomei.constant.InternKeyConstant.INTENT_KEY_MOVIE_SUBJECTBEAN;
import static com.lichao.chaomei.utils.StatusBarUtils.getStatusBarHeight;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 17:22
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class MovieDetailActivity extends BaseMVPCompatActivity<MovieDetailContract
        .MovieDetailPresenter> implements MovieDetailContract.IMovieDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_header_bg)
    ImageView ivHeaderBg;
    @BindView(R.id.iv_movie_photo)
    ImageView ivMoviePhoto;
    @BindView(R.id.tv_movie_rating_rate)
    TextView tvMovieRatingRate;
    @BindView(R.id.tv_movie_rating_number)
    TextView tvMovieRatingNumber;
    @BindView(R.id.tv_collect_count)
    TextView tvCollectCount;
    @BindView(R.id.tv_movie_directors)
    TextView tvMovieDirectors;
    @BindView(R.id.tv_movie_casts)
    TextView tvMovieCasts;
    @BindView(R.id.tv_movie_genres)
    TextView tvMovieGenres;
    @BindView(R.id.tv_movie_date)
    TextView tvMovieDate;
    @BindView(R.id.tv_movie_city)
    TextView tvMovieCity;
    @BindView(R.id.rv_movie_detail)
    RecyclerView rvMovieDetail;
    @BindView(R.id.iv_toolbar_bg)
    ImageView ivToolbarBg;
    @BindView(R.id.nsv_scrollview)
    CompatNestedScrollView nsvScrollView;
    @BindView(R.id.tv_movie_sub_title)
    TextView tvMovieSubTitle;
    @BindView(R.id.tv_moive_summary)
    TextView tvMovieSummary;

    private SubjectsBean mSubjectBean;
    private MovieDetailAdapter mMovieDetailAdapter;
    private View errorView;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return MovieDetailPresenter.newInstance();
    }

    @Override
    protected void initData() {
        super.initData();
        if (getIntent() != null) {
            mSubjectBean = (SubjectsBean) getIntent().getSerializableExtra
                    (INTENT_KEY_MOVIE_SUBJECTBEAN);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitleBar(toolbar, mSubjectBean.getTitle());
        initHeaderView(mSubjectBean);

        mMovieDetailAdapter = new MovieDetailAdapter(R.layout
                .item_movie_detail_person);
        rvMovieDetail.setAdapter(mMovieDetailAdapter);
        rvMovieDetail.setLayoutManager(new LinearLayoutManager(this));
        rvMovieDetail.setNestedScrollingEnabled(false);
        nsvScrollView.bindAlphaView(ivToolbarBg);
        mPresenter.loadMovieDetail(mSubjectBean.getId());

        errorView = getLayoutInflater().inflate(R.layout.view_network_error, rvMovieDetail, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadMovieDetail(mSubjectBean.getId());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_moive_detail;
    }

    @OnClick(R.id.ll_movie_header)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_movie_header:
                mPresenter.onHeaderClick(mSubjectBean);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showMovieDetail(MovieDetailBean bean) {
        //        Logger.e(bean.toString());
        if (mMovieDetailAdapter.getData().size() == 0) {
            initRecycleView(bean);
            tvMovieCity.setText("制片国家/地区： " + bean.getCountriesString());
            tvMovieSubTitle.setText(bean.getAkaString());
            tvMovieSummary.setText(bean.getSummary());
        } else {
            mMovieDetailAdapter.addData(bean.getCasts());
        }
    }

    @Override
    public void showNetworkError() {
        mMovieDetailAdapter.setEmptyView(errorView);
    }

    /**
     * @param context      activity
     * @param subjectsBean bean
     * @param imageView    imageView
     */
    public static void start(Activity context, SubjectsBean subjectsBean, ImageView imageView) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(INTENT_KEY_MOVIE_SUBJECTBEAN, subjectsBean);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                (context, imageView, ResourcesUtils.getString(R.string.transition_movie_img));
        //与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    private void initRecycleView(MovieDetailBean bean) {
        List<PersonBean> list = bean.getDirectors();
        list.addAll(bean.getCasts());
        mMovieDetailAdapter = new MovieDetailAdapter(R.layout.item_movie_detail_person, list);
        mMovieDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onItemClick(position, (PersonBean) adapter.getItem(position));
            }
        });
        rvMovieDetail.setAdapter(mMovieDetailAdapter);
    }

    private void initHeaderView(SubjectsBean subjectsBean) {
        tvMovieRatingNumber.setText(String.valueOf(subjectsBean.getRating().getAverage()));
        tvCollectCount.setText(String.valueOf(subjectsBean.getCollect_count()));
        tvMovieDirectors.setText(subjectsBean.getDirectorsString());
        tvMovieCasts.setText(subjectsBean.getActorsString());
        tvMovieGenres.setText(subjectsBean.getGenresString());
        tvMovieDate.setText(subjectsBean.getYear());
        Glide.with(this).load(subjectsBean.getImages().getLarge()).asBitmap().into(ivMoviePhoto);
        DisplayUtils.displayBlurImg(this, subjectsBean.getImages().getLarge(), ivHeaderBg);
        DisplayUtils.displayBlurImg(this, subjectsBean.getImages().getLarge(), ivToolbarBg);

        int headerBgHeight;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            headerBgHeight = toolbar.getLayoutParams().height + getStatusBarHeight(this);
        }else {
            headerBgHeight = toolbar.getLayoutParams().height;
        }
        // 使背景图向上移动到图片的最低端，保留（toolbar+状态栏）的高度
        // 实际上此时ivToolbarBg高度还是330dp，只是除了toolbar外，剩下部分是透明状态
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams)
                ivToolbarBg.getLayoutParams();
        int marginTop = ivToolbarBg.getLayoutParams().height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);
    }
}

