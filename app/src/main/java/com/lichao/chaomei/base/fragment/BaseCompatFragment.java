package com.lichao.chaomei.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lichao.chaomei.MyApplication;
import com.lichao.chaomei.utils.AppUtils;
import com.lichao.chaomei.widgets.WaitProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ChaoLi on 2018/5/8 0008
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public abstract class BaseCompatFragment extends SupportFragment {

    protected String TAG;
    protected Context mContext;
    protected Activity mActivity;
    protected MyApplication myApplication;
    protected WaitProgressDialog waitProgressDialog;
    private Unbinder binder;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            return inflater.inflate(getLayoutId(), container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getClass().getSimpleName();
        binder = ButterKnife.bind(this, view);
        getBundle(getArguments());
        initData();
        initUI(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null)
            binder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @LayoutRes
    public abstract int getLayoutId();

    /**
     * 得到View
     * @return
     */
    public View getLayoutView() {
        return null;
    }

    /**
     * 得到Activity传进来的值
     * @param bundle
     */
    public void getBundle(Bundle bundle) {

    }

    /**
     * 初始化UI
     */
    public abstract void initUI(View view, @Nullable Bundle savedInstanceState);

    /**
     * 在监听器之前把数据准备好
     */
    public void initData() {
        waitProgressDialog = new WaitProgressDialog(mActivity);
        mContext = AppUtils.getContext();
        myApplication = (MyApplication)  mActivity.getApplication();
    }

    /**
     * 处理回退事件
     * @return true 事件已消费 <p>
     *     false 事件向上传递
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            //如果当前存在fragment>1，当前fragment出栈
            pop();
        } else {
            //已经退栈到root fragment，交由Activity处理回退事件
            return false;
        }
        return true;
    }

    /**
     * 显示提示框
     * @param msg
     */
    protected void showProgressDialog(String msg) {
        if (waitProgressDialog.isShowing()) {
            waitProgressDialog.dismiss();
        }
        waitProgressDialog.setMessage(msg);
        waitProgressDialog.show();
    }

    /**
     * 隐藏提示框
     */
    protected void hideProgressDialog() {
        if (waitProgressDialog != null) {
            waitProgressDialog.dismiss();
        }
    }
}
