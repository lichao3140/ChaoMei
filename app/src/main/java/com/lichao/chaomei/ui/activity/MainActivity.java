package com.lichao.chaomei.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import com.lichao.chaomei.R;
import com.lichao.chaomei.base.activity.BaseCompatActivity;
import com.lichao.chaomei.constant.BundleKeyConstant;
import com.lichao.chaomei.helper.BottomNavigationViewHelper;
import com.lichao.chaomei.rxbus.RxBus;
import com.lichao.chaomei.ui.activity.detail.WebViewLoadActivity;
import com.lichao.chaomei.ui.fragment.book.BookRootFragment;
import com.lichao.chaomei.ui.fragment.gankio.GankIoRootFragment;
import com.lichao.chaomei.ui.fragment.home.HomeRootFragment;
import com.lichao.chaomei.ui.fragment.home.child.HomeFragment;
import com.lichao.chaomei.ui.fragment.movie.MovieRootFragment;
import com.lichao.chaomei.ui.fragment.personal.PersonalRootFragment;
import com.lichao.chaomei.ui.widgets.MovingImageView;
import com.lichao.chaomei.ui.widgets.MovingViewAnimator;
import com.lichao.chaomei.utils.AppUtils;
import com.lichao.chaomei.utils.FileUtils;
import com.lichao.chaomei.utils.NavigationUtils;
import com.lichao.chaomei.utils.SpUtils;
import com.lichao.chaomei.utils.ToastUtils;
import java.io.File;
import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.SupportFragment;
import static com.lichao.chaomei.constant.HeadConstant.HEAD_IMAGE_NAME;

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

    private MovingImageView mivMenu;
    private CircleImageView civHead;
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
            mFragments[FIRST] = HomeRootFragment.newInstance();
            mFragments[SECOND] = GankIoRootFragment.newInstance();
            mFragments[THIRD] = MovieRootFragment.newInstance();
            mFragments[FOURTH] = BookRootFragment.newInstance();
            mFragments[FIFTH] = PersonalRootFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()
            // 自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(HomeRootFragment.class);
            mFragments[SECOND] = findFragment(GankIoRootFragment.class);
            mFragments[THIRD] = findFragment(MovieRootFragment.class);
            mFragments[FOURTH] = findFragment(BookRootFragment.class);
            mFragments[FIFTH] = findFragment(PersonalRootFragment.class);
        }

        NavigationUtils.disableNavigationViewScrollbars(nvMenu);
        mivMenu = nvMenu.getHeaderView(0).findViewById(R.id.miv_menu);
        civHead = nvMenu.getHeaderView(0).findViewById(R.id.civ_head);

        //此处实际应用中替换成服务器拉取图片
        Uri headUri = Uri.fromFile(new File(getCacheDir(), HEAD_IMAGE_NAME + ".jpg"));
        if (headUri != null) {
            String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), headUri);
            Bitmap bitmap = BitmapFactory.decodeFile(cropImagePath);
            if (bitmap != null) {
                civHead.setImageBitmap(bitmap);
            }
        }

        civHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlRoot.closeDrawer(GravityCompat.START);
                bottomNavigationView.setSelectedItemId(R.id.menu_item_personal);
            }
        });

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        showHideFragment(mFragments[FIRST]);
                        break;
                    case R.id.menu_item_gank_io:
                        showHideFragment(mFragments[SECOND]);
                        break;
                    case R.id.menu_item_movie:
                        showHideFragment(mFragments[THIRD]);
                        break;
                    case R.id.menu_item_book:
                        showHideFragment(mFragments[FOURTH]);
                        break;
                    case R.id.menu_item_personal:
                        showHideFragment(mFragments[FIFTH]);
                        break;
                }
                return true;
            }
        });

        nvMenu.getMenu().findItem(R.id.item_model).setTitle(SpUtils.getNightModel(mContext) ? "夜间模式" : "日间模式");
        nvMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.group_item_github:
                        Bundle bundle = new Bundle();
                        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE, "ChaoMei");
                        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL,
                                "https://github.com/lichao3140/ChaoMei");
                        startActivity(WebViewLoadActivity.class, bundle);
                        break;
                    case R.id.group_item_more:
                        Bundle bundle2 = new Bundle();
                        bundle2.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE, "ChaoLi");
                        bundle2.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL, "https://github.com/lichao3140");
                        startActivity(WebViewLoadActivity.class, bundle2);
                        break;
                    case R.id.group_item_qr_code:
//                        startActivity(QRCodeActivity.class);
                        break;
                    case R.id.group_item_share_project:
                        showShare();
                        break;
                    case R.id.group_item_phone:
                        sendCode("86", "15889750515");
                        break;
                    case R.id.item_model:
                        SpUtils.setNightModel(mContext, !SpUtils.getNightModel(mContext));
                        MainActivity.this.reload();
                        break;
                    case R.id.item_about:
                        startActivity(AboutActivity.class);
                        break;
                }

                item.setCheckable(false);
                dlRoot.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        dlRoot.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                mivMenu.pauseMoving();
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                if (mivMenu.getMovingState() == MovingViewAnimator.MovingState.stop) {
                    mivMenu.startMoving();
                } else if (mivMenu.getMovingState() == MovingViewAnimator.MovingState.pause) {
                    mivMenu.resumeMoving();
                }
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                mivMenu.stopMoving();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (mivMenu.getMovingState() == MovingViewAnimator.MovingState.stop) {
                    mivMenu.startMoving();
                } else if (mivMenu.getMovingState() == MovingViewAnimator.MovingState.pause) {
                    mivMenu.resumeMoving();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onOpen() {
        if (!dlRoot.isDrawerOpen(GravityCompat.START)) {
            dlRoot.openDrawer(GravityCompat.START);
        }
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

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setSilent(false);
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("ChaoMei");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("超美Github源码");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("https://github.com/lichao3140/ChaoMei");
        Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageData(imageData);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams shareParams) {
                if(platform.getName().equals("SinaWeibo")) {
                    shareParams.setText("wenben  http://www.baidu.com");
                    shareParams.setUrl(null);
                } else if(platform.getName().equals("Wechat")) {
                    shareParams.setImageUrl("https://infile.cekom.com.cn/test/fileStore/portal/ecdoc/thumbnail/ios/20173/b08cb10aa52d4004b277e6ac53363e73.jpg");
                    shareParams.setUrl("https://github.com/lichao3140/ChaoMei");
                    shareParams.setTitle("ChaoMei");
                    shareParams.setText("超美Github源码");
                }
            }
        });
        // 启动分享GUI
        oks.show(MainActivity.this);
    }

    /**
     * 请求验证码
     * @param country  国家代码
     * @param phone  手机号码
     */
    public void sendCode(String country, String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        System.out.println("--------event---"+event+"--------result*" + result);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                ToastUtils.showToast("发送成功");
                            }
                        });
                    }
                } else{
                    // TODO 处理错误的结果
                    System.out.println("-------------------------发送失败" + result+data);
                }
                // 用完回调要注销，否则会造成泄露
                SMSSDK.unregisterEventHandler(this);
            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }

    /**
     * 提交验证码
     * @param country 国家
     * @param phone 手机号码
     * @param code 验证码
     */
    public void submitCode(String country, String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                } else {
                    // TODO 处理错误的结果
                }
                // 用完回调要注销，否则会造成泄露
                SMSSDK.unregisterEventHandler(this);
            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    public native String stringFromJNI();
}
