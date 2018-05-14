package com.lichao.chaomei.model.bean.wangyi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 14:45
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WangyiNewsListBean {

    @SerializedName("T1348647909107")
    ArrayList<WangyiNewsItemBean> newsList;

    public ArrayList<WangyiNewsItemBean> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<WangyiNewsItemBean> newsList) {
        this.newsList = newsList;
    }
}
