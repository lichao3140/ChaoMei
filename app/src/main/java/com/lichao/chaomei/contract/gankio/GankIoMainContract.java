package com.lichao.chaomei.contract.gankio;

import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.IBaseFragment;
import com.lichao.chaomei.base.IBaseModel;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 10:25
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface GankIoMainContract {
    //主页接口
    abstract class GankIoMainPresenter extends BasePresenter<IGankIoMainModel, IGankIoMainView> {
        public abstract void getTabList();
    }

    interface IGankIoMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IGankIoMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }
}
