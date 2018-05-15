package com.lichao.chaomei.model.movie;

import android.support.annotation.NonNull;
import com.lichao.chaomei.api.DoubanApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.movie.MovieDetailContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.douban.movie.MovieDetailBean;
import com.lichao.chaomei.model.bean.douban.movie.child.PersonBean;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 16:17
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class MovieDetailModel extends BaseModel implements MovieDetailContract.IMovieDetailModel {

    @NonNull
    public static MovieDetailModel newInstance() {
        return new MovieDetailModel();
    }

    @Override
    public Observable<MovieDetailBean> getMovieDetail(String id) {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getMovieDetail(id)
                .compose(RxHelper.<MovieDetailBean>rxSchedulerHelper())
                .map(new Function<MovieDetailBean, MovieDetailBean>() {
                    @Override
                    public MovieDetailBean apply(MovieDetailBean bean) throws Exception {
                        //返回的数据没有person type，根据数组类型指定
                        for (PersonBean bean1 : bean.getCasts()) {
                            bean1.setType("主演");
                        }
                        for (PersonBean bean2 : bean.getDirectors()) {
                            bean2.setType("导演");
                        }
                        return bean;
                    }
                });
    }
}
