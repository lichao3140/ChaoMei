package com.lichao.chaomei.helper.okhttp;

import com.lichao.chaomei.utils.AppUtils;

import java.io.File;

import okhttp3.Cache;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 12:22
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class HttpCache {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024;

    public static Cache getCache() {
        return new Cache(new File(AppUtils.getContext().getCacheDir().getAbsolutePath() + File
                .separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }
}
