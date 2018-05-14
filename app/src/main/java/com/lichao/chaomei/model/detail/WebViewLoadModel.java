package com.lichao.chaomei.model.detail;

import android.support.annotation.NonNull;

import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.detail.WebViewLoadConaract;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 20:44
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WebViewLoadModel extends BaseModel implements
        WebViewLoadConaract.IWebViewLoadModel {

    @NonNull
    public static WebViewLoadModel newInstance() {
        return new WebViewLoadModel();
    }
}
