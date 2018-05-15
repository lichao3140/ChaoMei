package com.lichao.chaomei.presenter.movie;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.lichao.chaomei.contract.movie.MovieMainContract;
import com.lichao.chaomei.helper.Cache;
import com.lichao.chaomei.model.bean.douban.movie.HotMovieBean;
import com.lichao.chaomei.model.bean.douban.movie.child.SubjectsBean;
import com.lichao.chaomei.model.movie.MovieMainModel;
import com.lichao.chaomei.ui.fragment.movie.child.top.TopMoiveFragment;

import io.reactivex.functions.Consumer;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 16:20
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class MovieMainPresenter extends MovieMainContract.MovieMainPresenter {

    @NonNull
    public static MovieMainPresenter newInstance() {
        return new MovieMainPresenter();
    }

    @Override
    public void loadHotMovieList() {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getHotMovieList().subscribe(new Consumer<HotMovieBean>() {
            @Override
            public void accept(HotMovieBean hotMovieBean) throws Exception {
                if (mIView == null)
                    return;

                mIView.updateContentList(hotMovieBean.getSubjects());
                Cache.saveHotMovieCache(hotMovieBean.getSubjects());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    if (mIView.isVisiable())
                        mIView.showToast("Network error.");

                    if (Cache.getHotMovieCache().size() == 0) {//没有缓存缓存，显示网络错误界面
                        mIView.showNetworkError();
                    } else {
                        mIView.updateContentList(Cache.getHotMovieCache());//加载缓存
                    }
                }
            }
        }));
    }

    @Override
    public void onItemClick(int position, SubjectsBean item, ImageView imageView) {
//        Logger.e("position " + position + " is clicked.");
        //MovieDetailActivity.start(mIView.getBindActivity(), item, imageView);
    }

    @Override
    public void onHeaderClick() {
        mIView.startNewFragment(TopMoiveFragment.newInstance());
    }

    @Override
    public MovieMainContract.IMovieMainModel getModel() {
        return MovieMainModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
