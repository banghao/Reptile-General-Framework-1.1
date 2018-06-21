package com.android.cbh.pwd.mvp.ui.view;

/**
 * Created by cbh on 2018/6/2
 */
public interface CreateLockAView extends com.android.cbh.pwd.mvp.ui.view.BaseView {

    void initLockPatternView();

    void lockDisplayError();

    void setResults(int isSuccess);

    void clearPattern();
}
