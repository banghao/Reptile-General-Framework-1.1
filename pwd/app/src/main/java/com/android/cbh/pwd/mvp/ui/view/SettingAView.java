package com.android.cbh.pwd.mvp.ui.view;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by cbh on 2018/6/2
 */
public interface SettingAView {

    void showChangeThemeDialog();
    void findView();
    void initState(boolean isOpen);
    void initOpenShow(boolean isOpen);
    void reCreate();
    void readyGo(Class clazz, Intent intent);
    void go2(Class clazz, Bundle bundle);
    void showSnackBar(String msg);
}
