package com.lichao.chaomei.contract.personal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.lichao.chaomei.base.BasePresenter;
import com.lichao.chaomei.base.IBaseFragment;
import com.lichao.chaomei.base.IBaseModel;
import java.io.File;

/**
 * Created by ChaoLi on 2018/5/15 0015 - 18:16
 * Email: lichao3140@gmail.com
 * Version: v1.0
 */
public interface PersonalContract {

    abstract class PersonalUpperPresenter extends BasePresenter<IPersonalUpperModel, IPersonalUpperView> {
        /**
         * 头像点击
         */
        public abstract void btnHeadClicked();

        /**
         * 相机按钮点击
         */
        public abstract void btnCameraClicked();

        /**
         * 相册按钮点击
         */
        public abstract void btnPhotoClicked();

        /**
         * 取消按钮点击
         */
        public abstract void btnCancelClicked();

        /**
         * 外部存储权限申请返回
         */
        public abstract void onRequestPermissionsResult(int requestCode, @NonNull String[]
                permissions, @NonNull int[] grantResults);

        /**
         * Activity返回
         *
         * @param requestCode
         * @param resultCode
         * @param intent
         */
        public abstract void onActivityResult(int requestCode, int resultCode, Intent intent);
    }

    interface IPersonalUpperModel extends IBaseModel {
    }

    interface IPersonalUpperView extends IBaseFragment {
        /**
         * 初始化PopupView
         */
        void initPopupView();

        /**
         * 显示头像
         *
         * @param bitmap 头像bitmap
         */
        void showHead(Bitmap bitmap);

        /**
         * 显示PopupView
         */
        void showPopupView();

        /**
         * 隐藏PopupView
         */
        void dismissPopupView();

        /**
         * 返回popupView是否显示
         *
         * @return popupView是否显示
         */
        boolean popupIsShowing();

        /**
         * 前往设置头像界面
         *
         * @param uri uri
         */
        void gotoHeadSettingActivity(Uri uri);

        /**
         * 前往系统图库界面
         *
         * @param requestCode requestCode
         */
        void gotoSystemPhoto(int requestCode);

        /**
         * 前往系统相机界面
         *
         * @param tempFile    相片存储文件
         * @param requestCode requestCode
         */
        void gotoSystemCamera(File tempFile, int requestCode);

        /**
         * 获取当前fragment所在的Activity
         *
         * @return Activity
         */
        Activity getActivity();
    }
}
