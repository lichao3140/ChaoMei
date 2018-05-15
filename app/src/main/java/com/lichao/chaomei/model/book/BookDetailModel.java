package com.lichao.chaomei.model.book;

import android.support.annotation.NonNull;
import com.lichao.chaomei.api.DoubanApi;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.book.BookDeatilContract;
import com.lichao.chaomei.helper.RetrofitCreateHelper;
import com.lichao.chaomei.helper.RxHelper;
import com.lichao.chaomei.model.bean.douban.book.BookDetailBean;
import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 17:47
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class BookDetailModel extends BaseModel implements BookDeatilContract.IBookDetailModel {

    @NonNull
    public static BookDetailModel newInstance() {
        return new BookDetailModel();
    }

    @Override
    public Observable<BookDetailBean> getBookDetail(String id) {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getBookDetail(id)
                .compose(RxHelper.<BookDetailBean>rxSchedulerHelper());
    }
}