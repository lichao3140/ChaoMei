package com.lichao.chaomei.model.book;

import android.support.annotation.NonNull;
import com.lichao.chaomei.base.BaseModel;
import com.lichao.chaomei.contract.book.BookMainContract;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 17:48
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class BookMainModel extends BaseModel implements BookMainContract.IBookMainModel {

    @NonNull
    public static BookMainModel newInstance() {
        return new BookMainModel();
    }

    @Override
    public String[] getTabs() {
        return new String[]{"文学", "文化", "生活"};
    }
}