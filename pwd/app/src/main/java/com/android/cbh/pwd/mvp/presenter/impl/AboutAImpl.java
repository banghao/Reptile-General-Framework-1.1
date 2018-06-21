package com.android.cbh.pwd.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.android.cbh.pwd.databinding.ActivityAboutBinding;
import com.android.cbh.pwd.mvp.model.bean.AboutDB;
import com.android.cbh.pwd.mvp.presenter.ActivityPresenter;
import com.android.cbh.pwd.mvp.ui.activity.AboutActivity;
import com.android.cbh.pwd.mvp.ui.activity.WebViewActivity;
import com.android.cbh.pwd.mvp.ui.view.AboutAView;

/**
 * Created by cbh on 2018/6/2.
 * aboutimpl 关于实现 这是 接口 和 实现类的方式
 ContextImpl 就是接口的实现类，Context是一个抽象类。
 dao 与数据库的操作，增删改查等方法
 model 一般都是javabean对象，例如与数据库的某个表相关联。
 service 供外部调用，等于对dao，model等进行了包装。
 impl 定义的接口
 util 通常都是工具类，如字符串处理、日期处理等
 */
public class AboutAImpl implements ActivityPresenter {

    private final Context mContext;
    private final ActivityAboutBinding mDataBinding;
    private final AboutAView mAboutAView;

    public AboutAImpl(Context context, AboutAView view, ActivityAboutBinding dataBinding) {
        mContext = context;
        mAboutAView = view;
        mDataBinding = dataBinding;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mDataBinding.setAboutInfo(new AboutDB(getVersion()));
    }

    @Override
    public void getIntent(Intent intent) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    public String getVersion() {
        try {
             PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
             return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();
         }
        return "1.0.0";
    }

    public void codeClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("URL", "https://github.com/banghao/Reptile-General-Framework-1.1");
        mAboutAView.go2Activity(WebViewActivity.class, bundle);
    }
}
