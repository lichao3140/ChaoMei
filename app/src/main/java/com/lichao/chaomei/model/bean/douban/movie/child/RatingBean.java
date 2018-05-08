package com.lichao.chaomei.model.bean.douban.movie.child;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:40
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class RatingBean implements Serializable {
    /**
     * max : 10
     * average : 6.9
     * stars : 35
     * min : 0
     */
    @SerializedName("max")
    private int max;
    @SerializedName("average")
    private double average;
    @SerializedName("stars")
    private String stars;
    @SerializedName("min")
    private int min;

    public int getMax() {
        return max;
    }

    public double getAverage() {
        return average;
    }

    public String getStars() {
        return stars;
    }

    public int getMin() {
        return min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "RatingBean{" +
                "max=" + max +
                ", average=" + average +
                ", stars='" + stars + '\'' +
                ", min=" + min +
                '}';
    }
}