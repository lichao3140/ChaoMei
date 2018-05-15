package com.lichao.chaomei.presenter.book;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.lichao.chaomei.constant.BundleKeyConstant;
import com.lichao.chaomei.contract.book.BookDeatilContract;
import com.lichao.chaomei.model.bean.douban.book.BookDetailBean;
import com.lichao.chaomei.model.bean.douban.book.BookItemBean;
import com.lichao.chaomei.model.book.BookDetailModel;
import com.lichao.chaomei.ui.activity.detail.WebViewLoadActivity;
import io.reactivex.functions.Consumer;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 17:51
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class BookDetailPresenter extends BookDeatilContract.BookDetailPresenter {

    @NonNull
    public static BookDetailPresenter newInstance() {
        return new BookDetailPresenter();
    }

    @Override
    public void loadBookDetail(String id) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.getBookDetail(id).subscribe(new Consumer<BookDetailBean>() {
            @Override
            public void accept(BookDetailBean bean) throws Exception {
                if (mIView == null)
                    return;

                mIView.showBookDetail(bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    return;
                mIView.showToast("Network error.");
                mIView.showNetworkError();
            }
        }));
    }

    @Override
    public void onHeaderClick(BookItemBean bean) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE, bean.getTitle());
        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL, bean.getAlt());
        mIView.startNewActivity(WebViewLoadActivity.class, bundle);
    }

    @Override
    public BookDeatilContract.IBookDetailModel getModel() {
        return BookDetailModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
