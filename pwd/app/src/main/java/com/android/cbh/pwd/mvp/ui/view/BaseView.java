package com.android.cbh.pwd.mvp.ui.view;

/**
 * Created by cbh on 2018/6/2
 */
public interface BaseView {

    void readyGoThenKill(Class clazz);
    void kill();
    void showSnackBar(String msg);
}
