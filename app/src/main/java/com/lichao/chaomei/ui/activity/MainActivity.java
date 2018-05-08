package com.lichao.chaomei.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lichao.chaomei.R;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

/**
 * Created by ChaoLi on 2018/5/8 0008
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        share();
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }

    private void share() {
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(false);
        oks.disableSSOWhenAuthorize();
        oks.setTitle("内部交流论坛-yzplayDemo");
        oks.setText("https://yzplay。youzu");
        oks.setTitleUrl("https://yzplay.youzu.com/sharePage.html?a=1&t=116609");
        Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        oks.setImageData(imageData);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams shareParams) {
                if(platform.getName().equals("SinaWeibo")) {
                    shareParams.setText("wenben  http://www.baidu.com");
                    shareParams.setUrl(null);
                }
            }
        });
        oks.show(MainActivity.this);
    }

    public native String stringFromJNI();
}
