package com.lichao.chaomei.api;

import com.lichao.chaomei.model.bean.wangyi.WangyiNewsListBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 14:39
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface WangyiApi {
    public final String HOST = "http://c.m.163.com";

    @GET("/nc/article/headline/T1348647909107/{id}-20.html")
    Observable<WangyiNewsListBean> getNewsList(@Path("id") int id);

    @GET("/nc/article/{id}/full.html")
    Observable<ResponseBody> getNewsDetail(@Path("id") String id);
}
