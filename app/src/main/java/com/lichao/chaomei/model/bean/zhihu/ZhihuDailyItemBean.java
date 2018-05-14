package com.lichao.chaomei.model.bean.zhihu;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 11:52
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class ZhihuDailyItemBean {
    @SerializedName("images")
    private String[] images;
    @SerializedName("type")
    private int type;
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    private String date;
    public boolean hasFadedIn = false;

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ZhihuDailyItemBean{" +
                "images=" + Arrays.toString(images) +
                ", type=" + type +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", hasFadedIn=" + hasFadedIn +
                '}';
    }
}
