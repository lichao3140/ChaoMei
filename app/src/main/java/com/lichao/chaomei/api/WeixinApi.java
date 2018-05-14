package com.lichao.chaomei.api;

import com.lichao.chaomei.model.bean.weixin.WeixinChoiceListBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 15:58
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface WeixinApi {
    public static final String HOST = "http://v.juhe.cn";

    @GET("/weixin/query")
    public abstract Observable<WeixinChoiceListBean> getWeixinChoiceList(@Query("pno") int page, @Query("ps") int
            ps, @Query("dtype") String dttype, @Query("key") String key);
}
