package com.android.cbh.pwd.mvp.ui.view;

public interface IndexAView {

    void initDrawerToggle();

    void initXViewPager();

    void readyGoForResult(Class clazz);

    void go2Setting();

    void showSnackBar(String msg);

    void kill();
}
