package com.lichao.chaomei.model.bean.douban.movie.child;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:40
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class ImagesBean implements Serializable {
    /**
     * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2378133884.jpg
     * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2378133884.jpg
     * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2378133884.jpg
     */
    @SerializedName("small")
    private String small;
    @SerializedName("large")
    private String large;
    @SerializedName("medium")
    private String medium;

    public String getSmall() {
        return small;
    }

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public String toString() {
        return "ImagesBean{" +
                "small='" + small + '\'' +
                ", large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                '}';
    }
}
