package com.lichao.chaomei.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import com.lichao.chaomei.R;
import com.lichao.chaomei.base.activity.BaseCompatActivity;
import com.lichao.chaomei.rxbus.RxBus;
import com.lichao.chaomei.ui.fragment.home.child.HomeFragment;
import com.lichao.chaomei.utils.ToastUtils;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ChaoLi on 2018/5/8 0008
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class MainActivity extends BaseCompatActivity implements HomeFragment.OnOpenDrawerLayoutListener {

    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.bviv_bar)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nv_menu)
    NavigationView nvMenu;
    @BindView(R.id.dl_root)
    DrawerLayout dlRoot;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected void initData() {
        super.initData();
        RxBus.get().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {

        } else {

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onBackPressedSupport() {

        if (dlRoot.isDrawerOpen(GravityCompat.START)) {
            dlRoot.closeDrawer(GravityCompat.START);
            return;
        }

        if (getFragmentManager().getBackStackEntryCount() > 1) {
            //如果当前存在fragment>1，当前fragment出栈
            pop();
        } else {
            //如果已经到root fragment了，2秒内点击2次退出
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                setIsTransAnim(false);
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                ToastUtils.showToast(R.string.press_again);
            }
        }
    }

    public native String stringFromJNI();
}
