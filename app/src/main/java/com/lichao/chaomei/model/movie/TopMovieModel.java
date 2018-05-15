package com.lichao.chaomei.model.movie;

import android.support.annotation.NonNull;

import com.lichao.chaomei.api.DoubanApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.movie.TopMovieContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.douban.movie.HotMovieBean;

import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 16:16
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class TopMovieModel extends BaseModel implements TopMovieContract.ITopMovieModel {

    @NonNull
    public static TopMovieModel newInstance() {
        return new TopMovieModel();
    }

    @Override
    public Observable<HotMovieBean> getTopMovieList(int start, int count) {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getMovieTop250
                (start, count).compose(RxHelper.<HotMovieBean>rxSchedulerHelper());
    }
}
