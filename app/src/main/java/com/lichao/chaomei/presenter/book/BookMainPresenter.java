package com.lichao.chaomei.presenter.book;

import android.support.annotation.NonNull;
import com.lichao.chaomei.contract.book.BookMainContract;
import com.lichao.chaomei.model.book.BookMainModel;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 17:52
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class BookMainPresenter extends BookMainContract.BookMainPresenter {
    @NonNull
    public static BookMainPresenter newInstance() {
        return new BookMainPresenter();
    }

    @Override
    public void getTabList() {
        if (mIView == null || mIModel == null)
            return;

        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    public BookMainContract.IBookMainModel getModel() {
        return BookMainModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}

