package com.lichao.chaomei.model.movie;

import android.support.annotation.NonNull;
import com.lichao.chaomei.api.DoubanApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.movie.MovieMainContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.douban.movie.HotMovieBean;
import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 16:04
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class MovieMainModel extends BaseModel implements MovieMainContract.IMovieMainModel {

    @NonNull
    public static MovieMainModel newInstance() {
        return new MovieMainModel();
    }

    @Override
    public Observable<HotMovieBean> getHotMovieList() {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getHotMovie()
                .compose(RxHelper.<HotMovieBean>rxSchedulerHelper());
    }
}