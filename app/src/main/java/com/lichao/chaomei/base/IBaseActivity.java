package com.lichao.chaomei.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 19:16
 * Email: lichao3140@gmail.com
 * Version: v1.0  BaseActivity接口
 */
public interface IBaseActivity extends IBaseView {
    /**
     * 跳往新的Activity
     *
     * @param clz 要跳往的Activity
     */
    void startNewActivity(@NonNull Class<?> clz);

    /**
     * 跳往新的Activity
     *
     * @param clz    要跳往的Activity
     * @param bundle 携带的bundle数据
     */
    void startNewActivity(@NonNull Class<?> clz, Bundle bundle);

    /**
     * 跳往新的Activity
     * @param clz 要跳转的Activity
     * @param bundle bundel数据
     * @param requestCode requestCode
     */
    void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode);
}
