package com.lichao.chaomei.model.book.tabs;

import android.support.annotation.NonNull;
import com.lichao.chaomei.api.DoubanApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.book.tabs.BookCustomContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.douban.book.BookListBean;
import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 17:50
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class BookCustomModel extends BaseModel implements BookCustomContract.IBookCustomModel {
    @NonNull
    public static BookCustomModel newInstance() {
        return new BookCustomModel();
    }

    @Override
    public Observable<BookListBean> getBookListWithTag(String tag, int start, int count) {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getBookListWithTag
                (tag, start, count).compose(RxHelper.<BookListBean>rxSchedulerHelper());
    }
}
