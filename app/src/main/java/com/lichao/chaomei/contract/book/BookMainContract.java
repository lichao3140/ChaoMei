package com.lichao.chaomei.contract.book;

import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.IBaseFragment;
import com.lichao.chaomei.base.IBaseModel;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 17:44
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface BookMainContract {
    //book主页接口
    abstract class BookMainPresenter extends BasePresenter<IBookMainModel, IBookMainView> {
        public abstract void getTabList();
    }

    interface IBookMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IBookMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }
}
