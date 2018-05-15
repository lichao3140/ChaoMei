package com.lichao.chaomei.model.bean.rxbus;

import android.net.Uri;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 18:38
 * Email: lichao3140@gmail.com
 * Version: v1.0 RxBus传递头像uri bean
 */
public class RxEventHeadBean {
    private Uri uri;

    public RxEventHeadBean(Uri uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "RxEventHeadBean{" +
                "uri=" + uri +
                '}';
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}

