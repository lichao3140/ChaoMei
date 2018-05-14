package com.lichao.chaomei.helper.okhttp;

import com.lichao.chaomei.utils.AppUtils;
import com.lichao.chaomei.utils.NetworkConnectionUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.lichao.chaomei.utils.HttpUtils.getUserAgent;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 11:58
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkConnectionUtils.isNetworkConnected(AppUtils.getContext())) {
            // 有网络时, 缓存1小时
            int maxAge = 60 * 60;
            request = request.newBuilder()
                    .removeHeader("User-Agent")
                    .header("User-Agent", getUserAgent())
                    .build();

            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            // 无网络时，缓存为4周
            int maxStale = 60 * 60 * 24 * 28;
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .removeHeader("User-Agent")
                    .header("User-Agent", getUserAgent())
                    .build();

            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
