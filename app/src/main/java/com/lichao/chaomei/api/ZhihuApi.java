package com.lichao.chaomei.api;

import com.lichao.chaomei.model.bean.zhihu.ZhihuDailyDetailBean;
import com.lichao.chaomei.model.bean.zhihu.ZhihuDailyListBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 12:34
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface ZhihuApi {
    public final String HOST = "http://news-at.zhihu.com";

    @GET("/api/4/news/latest")
    Observable<ZhihuDailyListBean> getLastDailyList();

    @GET("/api/4/news/before/{date}")
    Observable<ZhihuDailyListBean> getDailyListWithDate(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhihuDailyDetailBean> getZhihuDailyDetail(@Path("id") String id);
}
