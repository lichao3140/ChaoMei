package com.lichao.chaomei;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MyApplication extends Application {
    //这个key是自己在聚合数据申请的key，需要自己去聚合数据申请
    public static final String JU_HE_APP_KEY = "bc7c0000b8bb8b651f06054811fcfbab";

    protected static Context context;
    protected static Handler handler;
    protected static int mainThreadId;
    public static MyApplication mApp;

    public static synchronized MyApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Mob
        //MobSDK.init(this);
        mApp = this;
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 获取上下文对象
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取全局handler
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }
}
