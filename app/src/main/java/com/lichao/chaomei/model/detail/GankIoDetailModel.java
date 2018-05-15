package com.lichao.chaomei.model.detail;

import android.support.annotation.NonNull;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.detail.GankIoDetailContract;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 13:51
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class GankIoDetailModel extends BaseModel implements GankIoDetailContract
        .IGankIoDetailModel {

    @NonNull
    public static GankIoDetailModel newInstance() {
        return new GankIoDetailModel();
    }
}