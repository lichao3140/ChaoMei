package com.lichao.chaomei.utils;

import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;

/**
 * Created by ChaoLi on 2018/5/14 0014 - 10:30
 * Email: lichao3140@gmail.com
 * Version: v1.0 NavigationView utils
 */
public class NavigationUtils {

    public static void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView
                    .getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }
}
