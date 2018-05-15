package com.lichao.chaomei.contract.book;

import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.IBaseActivity;
import com.lichao.chaomei.base.IBaseModel;
import com.lichao.chaomei.model.bean.douban.book.BookDetailBean;
import com.lichao.chaomei.model.bean.douban.book.BookItemBean;
import io.reactivex.Observable;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 17:43
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface BookDeatilContract {

    abstract class BookDetailPresenter extends BasePresenter<IBookDetailModel, IBookDetailView> {
        /**
         * 加载书籍详情
         *
         * @param id 书籍id
         */
        public abstract void loadBookDetail(String id);

        /**
         * header点击事件
         *
         * @param bean bean
         */
        public abstract void onHeaderClick(BookItemBean bean);
    }

    interface IBookDetailModel extends IBaseModel {
        /**
         * 获取书籍详情
         *
         * @param id 书籍id
         * @return 书籍详情
         */
        Observable<BookDetailBean> getBookDetail(String id);
    }

    interface IBookDetailView extends IBaseActivity {
        /**
         * 显示书籍详情
         *
         * @param bean 书籍详情bean
         */
        void showBookDetail(BookDetailBean bean);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
