package com.android.cbh.pwd.mvp.ui.activity.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.android.cbh.pwd.BR;
import com.android.cbh.pwd.R;
import com.android.cbh.pwd.mvp.model.evenbus.EventCenter;
import com.android.cbh.pwd.utils.ThemeUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by cbh on 18/6/15
 */
//Github上提供了一个开源的库SwipeBackLayout，推荐大家使用比较新的，有些老的，可能会出现黑屏的问题，你还要设置一些
//大屏幕时代的到来，目前大部分的APP都支持侧滑关闭Activity及切换Activity的动画效果，这些效果极大的提高了用户体验
public abstract class BaseSwipeBackActivity extends Base implements SwipeBackActivityBase {
    private SwipeBackActivityHelper mHelper;
//    里面主要进行的操作是初始化SwipeBackLayout，并且将window的背景置为透明，然后设置SwipeBackLayout的滑动监听；

    protected ViewDataBinding mDataBinding;
//    DataBindingUtil.setContextView()生成的是抽象类ViewDataBinding，
// 但不能用作定义mBinding，因为设置数据时，需要用到其实现类的方法，如setUser()。
    @Override protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, getContentView());//加载这个界面
//        通过DataBindingUtil.setContentView获取mBinding实例，然后通过setVariable为变量设置值。当调用setText时，就会刷新该View
//        BR 变量的id值列表，类似于R。
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        if (isApplyButterKnife()) ButterKnife.bind(this);
        initToolbar();
        if (isApplyEventBus()) EventBus.getDefault().register(this);
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
        if (isApplyTranslucency()) initWindow();
//        半透明Translucency
    }

    @Override public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    private void initTheme() {
        ThemeUtils.Theme currentTheme = ThemeUtils.getCurrentTheme(this);
        ThemeUtils.changeTheme(this, currentTheme);
    }

    protected void initToolBar(Toolbar toolbar) {

        if (toolbar == null) return;

        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitle(getString(com.android.cbh.pwd.R.string.app_name));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void reload(boolean anim) {
        Intent intent = getIntent();
        intent.putExtra("anim", anim);
        if (!anim) {
            mHelper.getSwipeBackLayout();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        finish();
        if (!anim) {
            overridePendingTransition(0, 0);
        }
        startActivity(intent);
    }

    /**
     *  api大于19的时候，实现沉浸式状态栏
     */
    @TargetApi(19) protected void initWindow() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getStatusBarColor());
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    protected int getStatusBarColor() {
        return getColorPrimary();
    }

    private int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void onResume() {
        MobclickAgent.onResume(this);
        super.onResume();
    }

    @Override protected void onPause() {
        MobclickAgent.onPause(this);
        super.onPause();
    }

    @Override protected void onDestroy() {
        if (isApplyButterKnife()) ButterKnife.unbind(this);
        if (isApplyEventBus()) EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onMessageEvent(EventCenter event) {
        if (event != null) {
            onEventComing(event);
        }
    }

    protected abstract void onEventComing(EventCenter eventCenter);

    protected abstract int getContentView();

    protected abstract void initToolbar();

    protected abstract boolean isApplyTranslucency();

    protected abstract boolean isApplyButterKnife();

    protected abstract boolean isApplyEventBus();
}
