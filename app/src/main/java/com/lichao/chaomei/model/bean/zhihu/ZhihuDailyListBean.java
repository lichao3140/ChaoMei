package com.lichao.chaomei.model.bean.zhihu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 11:53
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class ZhihuDailyListBean {
    @SerializedName("date")
    private String date;
    @SerializedName("top_stories")
    private ArrayList<ZhihuDailyItemBean> mZhihuDailyItems;
    @SerializedName("stories")
    private ArrayList<ZhihuDailyItemBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ZhihuDailyItemBean> getZhihuDailyItems() {
        return mZhihuDailyItems;
    }

    public void setZhihuDailyItems(ArrayList<ZhihuDailyItemBean> zhihuDailyItems) {
        this.mZhihuDailyItems = zhihuDailyItems;
    }

    public ArrayList<ZhihuDailyItemBean> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ZhihuDailyItemBean> stories) {
        this.stories = stories;
    }

    @Override
    public String toString() {
        return "ZhihuDailyListBean{" +
                "date='" + date + '\'' +
                ", mZhihuDailyItems=" + mZhihuDailyItems +
                ", stories=" + stories +
                '}';
    }
}
