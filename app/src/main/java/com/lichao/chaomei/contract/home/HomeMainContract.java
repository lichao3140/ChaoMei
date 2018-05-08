package com.lichao.chaomei.contract.home;

import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.IBaseFragment;
import com.lichao.chaomei.base.IBaseModel;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 22:18
 * Email: lichao3140@gmail.com
 * Version: v1.0  主页Contract
 */
public interface HomeMainContract {
    //主页接口
    abstract class HomeMainPresenter extends BasePresenter<IHomeMainModel, IHomeMainView> {
        public abstract void getTabList();
    }

    interface IHomeMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IHomeMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }
}
