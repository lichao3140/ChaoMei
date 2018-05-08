package com.lichao.chaomei.base;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 20:36
 * Email: lichao3140@gmail.com
 * Version: v1.0  用于管理Rxjava 注册订阅和取消订阅
 */
public class RxManager {
    /**
     * 管理订阅者
     */
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * 管理注册者
     * @param d
     */
    public void register(Disposable d) {
        mCompositeDisposable.add(d);
    }

    /**
     * 取消订阅
     */
    public void unSubscribe() {
        mCompositeDisposable.dispose();
    }
}
