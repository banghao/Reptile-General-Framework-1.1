package com.android.cbh.pwd.mvp.model.bean;


/**
 * Created by cbh on 2018/6/2
 */
public class IndexBean {

    private String toolBarTitle;

    public IndexBean(String title) {
        this.toolBarTitle = title;
    }

    public void setToolBarTitle(String title) {
        this.toolBarTitle = title;
    }

    public String getToolBarTitle() {
        return this.toolBarTitle;
    }
}
