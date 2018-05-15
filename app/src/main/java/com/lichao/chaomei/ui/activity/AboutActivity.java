package com.lichao.chaomei.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.lichao.chaomei.R;
import com.lichao.chaomei.base.activity.BaseCompatActivity;
import com.lichao.chaomei.utils.AppUtils;
import com.lichao.chaomei.utils.ResourcesUtils;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 19:29
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class AboutActivity extends BaseCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitleBar(toolbar, "关于");
        toolbar.setTitleTextColor(ResourcesUtils.getColor(R.color.md_white));
        Logger.e("tvVersionCode = " + tvVersionCode);
        tvVersionCode.setText(AppUtils.getAppVersionName(this));
    }

    @OnClick(R.id.cv_author)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cv_author:
                Intent intent = new Intent();
                intent.setData(Uri.parse("https://github.com/Horrarndoo"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent); //启动浏览器
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }
}

