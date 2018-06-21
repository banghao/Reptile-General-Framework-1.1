package com.android.cbh.pwd.mvp.model.bean;

public class AboutDB {
    private String version;

    public AboutDB(String version) {
        this.version = version;
    }

    public String getVersion() {
        return "V"+this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
