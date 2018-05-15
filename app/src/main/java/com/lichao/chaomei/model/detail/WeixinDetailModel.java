package com.lichao.chaomei.model.detail;

import android.support.annotation.NonNull;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.detail.WeixinDetailContract;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 9:59
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WeixinDetailModel extends BaseModel implements WeixinDetailContract.IWeixinDetailModel {

    @NonNull
    public static WeixinDetailModel newInstance() {
        return new WeixinDetailModel();
    }
}