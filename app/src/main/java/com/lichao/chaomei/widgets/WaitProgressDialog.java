package com.lichao.chaomei.widgets;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ChaoLi on 2018/5/8 0008 - 17:07
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public class WaitProgressDialog extends ProgressDialog {

    public WaitProgressDialog(Context context) {
        super(context, 0);
    }

    public WaitProgressDialog(Context context, int theme) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
    }
}
