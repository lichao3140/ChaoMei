package com.lichao.chaomei.helper;

import com.lichao.chaomei.model.bean.douban.movie.child.SubjectsBean;
import com.lichao.chaomei.utils.SpUtils;

import java.util.List;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 16:22
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class Cache {
    /**
     * 获取豆瓣电影hot cache
     *
     * @return 豆瓣电影hot cache
     */
    public static List<SubjectsBean> getHotMovieCache() {
        return SpUtils.getDataList("hot_movie_cache", SubjectsBean.class);
    }

    /**
     * 保存豆瓣电影hot cache
     */
    public static void saveHotMovieCache(List<SubjectsBean> list) {
        SpUtils.setDataList("hot_movie_cache", list);
    }
}
