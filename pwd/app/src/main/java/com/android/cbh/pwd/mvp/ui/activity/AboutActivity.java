package com.android.cbh.pwd.mvp.ui.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.cbh.pwd.R;
import com.android.cbh.pwd.databinding.ActivityAboutBinding;
import com.android.cbh.pwd.mvp.model.evenbus.EventCenter;
import com.android.cbh.pwd.mvp.presenter.impl.AboutAImpl;
import com.android.cbh.pwd.mvp.ui.activity.base.BaseSwipeBackActivity;
import com.android.cbh.pwd.mvp.ui.view.AboutAView;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutActivity extends BaseSwipeBackActivity implements AboutAView {
//    DataBinding可以代替findViewById，让代码更简洁
    @Bind(R.id.common_toolbar) Toolbar mToolBar;
    private AboutAImpl mAboutImpl;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutBinding mDataBinding = (ActivityAboutBinding) super.mDataBinding;
        mAboutImpl = new AboutAImpl(this, this, mDataBinding);
        mAboutImpl.onCreate(savedInstanceState);
        mAboutImpl.getIntent(getIntent());
    }

    @Override protected void onEventComing(EventCenter eventCenter) {

    }

    @Override protected int getContentView() {
        return R.layout.activity_about;
    }

    @Override protected void initToolbar() {
        initToolBar(mToolBar);
        mToolBar.setTitle("关于");
    }

    @Override protected boolean isApplyTranslucency() {
        return true;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }

    @Override protected boolean isApplyEventBus() {
        return false;
    }

    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @OnClick(R.id.codeButton) public void onClick(View view) {
        mAboutImpl.codeClick(view);
    }
    @Override protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    public void go2Activity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
